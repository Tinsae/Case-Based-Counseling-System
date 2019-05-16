package tiCBR;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import tiCBR.gui.AdaptRankingDialog;
import tiCBR.gui.QueryDialog;
import tiCBR.gui.ResultDialog;
import tiCBR.gui.RetainDialog;
import tiCBR.gui.RevisionDialog;
import tiCBR.gui.SimilarityDialog;
import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.connector.DataBaseConnector;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.selection.SelectCases;
import jcolibri.util.FileIO;
import es.ucm.fdi.gaia.ontobridge.OntoBridge;
import es.ucm.fdi.gaia.ontobridge.OntologyDocument;

public class FRStart implements StandardCBRApplication {
	static QueryDialog qf;

	private static FRStart _instance = null;

	public static FRStart getInstance() {
		if (_instance == null)
			_instance = new FRStart();
		return _instance;
	}
	private FRStart() {
	}
	/** Connector object */
	Connector _connector;
	/** CaseBase object */
	CBRCaseBase _caseBase;
	
	
	SimilarityDialog similarityDialog;
	public ResultDialog resultDialog;
	AdaptRankingDialog solutionDialog;
        AdaptRankingDialog adaptedSolutionDialog;
	RevisionDialog revisionDialog;
	RetainDialog retainDialog;
	private ArrayList<CBRCase> cases;
	public void configure() throws ExecutionException {
		try {
		        // Create a data base connector
			_connector = new DataBaseConnector();
			// Init the ddbb connector with the config file
			_connector.initFromXMLfile(jcolibri.util.FileIO
					.findFile("tiCBR/databaseconfig.xml"));
			// Create a Lineal case base for in-memory organization
			_caseBase = new LinealCaseBase();

			// Obtain a reference to OntoBridge
			OntoBridge ob = jcolibri.util.OntoBridgeSingleton.getOntoBridge();
			// Configure it to work with the Pellet reasoner
			ob.initWithPelletReasoner();
			// Setup the main ontology
			OntologyDocument mainOnto = new OntologyDocument(
					"http://gaia.fdi.ucm.es/ontologies/frx.owl", FileIO
							.findFile("tiCBR/frx.owl").toExternalForm());

			// There are not subontologies
			ArrayList<OntologyDocument> subOntologies = new ArrayList<OntologyDocument>();
			// Load the ontology
			ob.loadOntology(mainOnto, subOntologies, false);
			// Create the dialogs
			resultDialog = new ResultDialog(main);
			solutionDialog = new AdaptRankingDialog(main,"tiCBR/gui/step4f.png","Solution");
                        adaptedSolutionDialog=new AdaptRankingDialog(main,"tiCBR/gui/step4.png","Adapted Solution");
			revisionDialog = new RevisionDialog(main);
			retainDialog = new RetainDialog(main);

		} catch (Exception e) {
		                        e.printStackTrace();
                    throw new ExecutionException(e);
		}
	}
	public CBRCaseBase preCycle() throws ExecutionException {
		// Load cases from connector into the case base
		_caseBase.init(_connector);
		System.out.println("connector is" + _connector);
		// Print the cases
		java.util.Collection<CBRCase> cases = _caseBase.getCases();
		System.out.println(cases.size() + " found");
		for (CBRCase c : cases)
			System.out.println(c);
		return _caseBase;
	}
	public void cycle(CBRQuery query) throws ExecutionException {
		// Obtain configuration for KNN
		// similarity configuration is based on the query
		similarityDialog = new SimilarityDialog(main, qf.getFh_sorted(),
				qf.getSh_sorted(), qf.getTh_sorted());
		similarityDialog.setVisible(true);
		NNConfig simConfig = similarityDialog.getSimilarityConfig();
		simConfig.setDescriptionSimFunction(new Average());
		// Execute NN
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(
				_caseBase.getCases(), query, simConfig);
		// Select k cases
		Collection<CBRCase> selectedcases = SelectCases.selectTopK(eval,
				similarityDialog.getK());
		// Show result
		resultDialog.showCases(eval, selectedcases);
		resultDialog.setVisible(true);

		solutionDialog.showFields(eval, selectedcases, qf.getRelMaj(),false);	
                solutionDialog.setVisible(true);
                        
                adaptedSolutionDialog.showFields(eval, selectedcases, qf.getRelMaj(),true);	
                adaptedSolutionDialog.setVisible(true);
                        
		cases = new ArrayList<CBRCase>();

		Iterator<MajorInfo> it = adaptedSolutionDialog.getMajorInfo().iterator();

		Integer counter = 1;
		Integer size = _caseBase.getCases().size();
		
	//Combine query case with recommended fields of study and create new cases
		while (it.hasNext()) {
			CBRCase cb = new CBRCase();
			Integer newId = size + counter + 1;

			StudCaseSolution sc = new StudCaseSolution();
			sc.setId("StudCase" + newId.toString());
			sc.setFieldofStudy(((MajorInfo) it.next()).getMajorTitle());
			StudCaseDescription desc = (StudCaseDescription) qf.getQuery()
					.getDescription();
			desc.setCaseId("StudCase" + newId.toString());
			cb.setDescription(desc);
			cb.setSolution(sc);
			cases.add(cb);
			counter++;
		}
		revisionDialog.showCases(cases);
		revisionDialog.setVisible(true);
		// Retain
		retainDialog.showCases(cases, _caseBase.getCases().size());
		retainDialog.setVisible(true);
		Collection<CBRCase> casesToRetain = retainDialog.getCasestoRetain();
		_caseBase.learnCases(casesToRetain);
	}
	public void postCycle() throws ExecutionException {
		MySql.close();
		_connector.close();
	}

	public static JFrame main;

	void showMainFrame() {
		main = new JFrame("Field of Study Recommender");
		main.setResizable(true);
		main.setUndecorated(true);
		JLabel label = new JLabel(new ImageIcon(
				jcolibri.util.FileIO
						.findFile("tiCBR/gui/jcolibri2.jpg")));
		main.getContentPane().add(label);
		main.pack();
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		main.setBounds((screenSize.width - main.getWidth()) / 12,
				(screenSize.height - main.getHeight()) / 12, main.getWidth(),
				main.getHeight());
		main.setVisible(true);
	}

	public static void main(String[] args) {

		FRStart recommender = getInstance();
		recommender.showMainFrame();
		try {
			recommender.configure();
			recommender.preCycle();
			qf = new QueryDialog(main);
			boolean cont = true;
			while (cont) {
				qf.setVisible(true);
				CBRQuery query = qf.getQuery();
				// retrieve suitable careers based on the top interest area of
				// the query case
				qf.checkCareers();

				//System.out.println("Query: " + query);
				recommender.cycle(query);
				int ans = javax.swing.JOptionPane.showConfirmDialog(null,
						"CBR cycle finished, query again?", "Cycle finished",
						javax.swing.JOptionPane.YES_NO_OPTION);
				cont = (ans == javax.swing.JOptionPane.YES_OPTION);
			}
			recommender.postCycle();
		} catch (Exception e) {
			org.apache.commons.logging.LogFactory.getLog(FRStart.class)
					.error(e);
			javax.swing.JOptionPane.showMessageDialog(null,
					"error!! " + e.getMessage());
			e.printStackTrace();

			System.out.println(e.getMessage());
		}
		// System.exit(0);
	}


}
