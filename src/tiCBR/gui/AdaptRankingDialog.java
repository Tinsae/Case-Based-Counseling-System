package tiCBR.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tiCBR.Career;
import tiCBR.FRStart;
import tiCBR.Major;
import tiCBR.MajorInfo;
import tiCBR.MySql;
import tiCBR.RelevantMajor;
import tiCBR.StudCaseDescription;
import tiCBR.StudCaseSolution;
import tiCBR.University;
import jcolibri.cbrcore.CBRCase;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.util.FileIO;

public class AdaptRankingDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	JLabel image;
	JLabel caseId;
	JLabel Stream;
	JLabel BackgroundInterest;
	JLabel Duration;
	JLabel EntranceResult;
	JLabel EntranceTop;
	JLabel FamilyInterest;
	JLabel PastExperience;
	JLabel Preparatory1st;
	JLabel Preparatory2nd;
	JLabel SpecialSkill;
	JLabel Realistic;
	JLabel Investigative;
	JLabel Artistic;
	JLabel Social;
	JLabel Enterprising;
	JLabel Conventional;
	JLabel FieldofStudy;
	JLabel majorTitle;
	JLabel majorBand;
	JLabel evaluation;
	JLabel noOfCases;
	JLabel totalCases;

	JList<String> matchCases;

	DefaultListModel<String> casesListModel = new DefaultListModel<String>();

	DefaultListModel<String> careersListModel = new DefaultListModel<String>();

	DefaultListModel<Object> univsListModel = new DefaultListModel<Object>();

	JLabel noOfOccup;

	JList<String> matchCareers;
	JLabel totalScore;

	JList<Object> matchUnivs;

	ArrayList<RetrievalResult> cases;
	ArrayList<MajorInfo> majorInfo;
	MajorInfo currentMajorInfo;

	int currentField;
	String message;

	private JFrame abc;
	private boolean flag;
	private final String sideImage;
	private final String title;

	public void showOneCase(CBRCase _case) {

		StudCaseDescription desc = (StudCaseDescription) _case.getDescription();

		JPanel panelTop = new JPanel();
		// panel.setLayout(new GridLayout(22, 2));
		panelTop.setLayout(new SpringLayout());

		JLabel label;

		panelTop.add(label = new JLabel("Description"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		panelTop.add(label = new JLabel());

		panelTop.add(new JLabel("Stream"));
		panelTop.add(Stream = new JLabel());
		Stream.setForeground(java.awt.Color.blue);
		Stream.setFont(label.getFont().deriveFont(Font.BOLD));

		panelTop.add(new JLabel("Duration"));
		panelTop.add(this.Duration = new JLabel());
		Duration.setForeground(java.awt.Color.blue);
		Duration.setFont(label.getFont().deriveFont(Font.BOLD));

		panelTop.add(new JLabel("EntranceResult"));
		panelTop.add(this.EntranceResult = new JLabel());
		EntranceResult.setForeground(java.awt.Color.blue);
		EntranceResult.setFont(label.getFont().deriveFont(Font.BOLD));

		panelTop.add(new JLabel("BackgroundInterest"));
		panelTop.add(BackgroundInterest = new JLabel());
		BackgroundInterest.setForeground(java.awt.Color.blue);
		BackgroundInterest.setFont(label.getFont().deriveFont(Font.BOLD));

		panelTop.add(new JLabel("FamilyInterest"));
		panelTop.add(this.FamilyInterest = new JLabel());
		FamilyInterest.setForeground(java.awt.Color.blue);
		FamilyInterest.setFont(label.getFont().deriveFont(Font.BOLD));

		panelTop.add(new JLabel("EntranceTop"));
		panelTop.add(this.EntranceTop = new JLabel());
		EntranceTop.setForeground(java.awt.Color.blue);
		EntranceTop.setFont(label.getFont().deriveFont(Font.BOLD));

		panelTop.add(new JLabel("Preparatory1st"));
		panelTop.add(Preparatory1st = new JLabel());
		Preparatory1st.setForeground(java.awt.Color.blue);
		Preparatory1st.setFont(label.getFont().deriveFont(Font.BOLD));

		panelTop.add(new JLabel("Preparatory2nd"));
		panelTop.add(Preparatory2nd = new JLabel());
		Preparatory2nd.setForeground(java.awt.Color.blue);
		Preparatory2nd.setFont(label.getFont().deriveFont(Font.BOLD));

		panelTop.add(new JLabel("SpecialSkill"));
		panelTop.add(this.SpecialSkill = new JLabel());
		SpecialSkill.setForeground(java.awt.Color.blue);
		SpecialSkill.setFont(label.getFont().deriveFont(Font.BOLD));

		panelTop.add(new JLabel("PastExperience"));
		panelTop.add(PastExperience = new JLabel());
		PastExperience.setForeground(java.awt.Color.blue);
		PastExperience.setFont(label.getFont().deriveFont(Font.BOLD));

		JLabel label2 = new JLabel("Personality");
		label2.setFont(label.getFont().deriveFont(Font.BOLD));

		panelTop.add(label2);
		panelTop.add(new JLabel(" "));

		Utils.makeCompactGrid(panelTop, 12, 2, 3, 3, 30, 10); // xPad, yPad

		JPanel panelCenter = new JPanel();

		panelCenter.setLayout(new SpringLayout());

		panelCenter.add(new JLabel("Realistic"));
		panelCenter.add(this.Realistic = new JLabel());
		Realistic.setForeground(java.awt.Color.blue);
		Realistic.setFont(label.getFont().deriveFont(Font.BOLD));

		panelCenter.add(new JLabel("Investigative"));
		panelCenter.add(Investigative = new JLabel());
		Investigative.setForeground(java.awt.Color.blue);
		Investigative.setFont(label.getFont().deriveFont(Font.BOLD));

		panelCenter.add(new JLabel("Artistic"));
		panelCenter.add(Artistic = new JLabel());
		Artistic.setForeground(java.awt.Color.blue);
		Artistic.setFont(label.getFont().deriveFont(Font.BOLD));

		panelCenter.add(new JLabel("Social"));
		panelCenter.add(Social = new JLabel());
		Social.setForeground(java.awt.Color.blue);
		Social.setFont(label.getFont().deriveFont(Font.BOLD));

		panelCenter.add(new JLabel("Enterprising"));
		panelCenter.add(Enterprising = new JLabel());
		Enterprising.setForeground(java.awt.Color.blue);
		Enterprising.setFont(label.getFont().deriveFont(Font.BOLD));

		panelCenter.add(new JLabel("Conventional"));
		panelCenter.add(Conventional = new JLabel());
		Conventional.setForeground(java.awt.Color.blue);
		Conventional.setFont(label.getFont().deriveFont(Font.BOLD));

		Utils.makeCompactGrid(panelCenter, 2, 6, 0, 0, 35, 10); // xPad, yPad

		JPanel panelBottom = new JPanel();
		panelBottom.setLayout(new SpringLayout());

		panelBottom.add(label = new JLabel("Solution"));
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		panelBottom.add(label = new JLabel());

		panelBottom.add(new JLabel("Field of Study"));
		panelBottom.add(FieldofStudy = new JLabel());
		FieldofStudy.setForeground(java.awt.Color.blue);
		FieldofStudy.setFont(label.getFont().deriveFont(Font.BOLD));

		Utils.makeCompactGrid(panelBottom, 2, 2, 6, 6, 30, 10); // xPad, yPad

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(panelTop, BorderLayout.NORTH);
		panel.add(panelCenter, BorderLayout.CENTER);
		panel.add(panelBottom, BorderLayout.SOUTH);

		JPanel extPanel = new JPanel(new BorderLayout());

		image = new JLabel();
		image.setIcon(new ImageIcon(FileIO
				.findFile("tiCBR/gui/step3_single.png")));

		extPanel.add(image, BorderLayout.WEST);
		extPanel.add(panel, BorderLayout.CENTER);

		this.Stream.setText(desc.getStream().toString());
		this.BackgroundInterest
				.setText(desc.getBackgroundInterest().toString());
		this.Duration.setText(desc.getDuration().toString());
		this.EntranceResult.setText(desc.getEntranceResult().toString());
		this.EntranceTop.setText(desc.getEntranceTop().toString());
		this.FamilyInterest.setText(desc.getFamilyInterest().toString());

		this.PastExperience.setText(desc.getPastExperience().toString());
		this.Preparatory1st.setText(desc.getPreparatory1st().toString());
		this.Preparatory2nd.setText(desc.getPreparatory2nd().toString());
		this.SpecialSkill.setText(desc.getSpecialSkill().toString());

		this.Realistic.setText(desc.getRealistic().toString());
		this.Investigative.setText(desc.getInvestigative().toString());
		this.Artistic.setText(desc.getArtistic().toString());
		this.Social.setText(desc.getSocial().toString());
		this.Enterprising.setText(desc.getEnterprising().toString());
		this.Conventional.setText(desc.getConventional().toString());

		StudCaseSolution sol = (StudCaseSolution) _case.getSolution();
		this.FieldofStudy.setText(sol.getFieldofStudy().toString());

		JOptionPane.showMessageDialog(null, extPanel, _case.getID().toString(),
				JOptionPane.CLOSED_OPTION);
	}

	public void showCareerInfo() {

		// show career info
		Career c = checkCareer(matchCareers.getSelectedValue().toString());

		JPanel infoPanel = new JPanel(new SpringLayout());
		infoPanel.add(new JLabel("ISCO Title"));
		JLabel ISCOTitle = new JLabel();
		ISCOTitle.setText(c.getISCOTitle());
		infoPanel.add(ISCOTitle);
		ISCOTitle.setForeground(java.awt.Color.blue);
		ISCOTitle.setFont(ISCOTitle.getFont().deriveFont(Font.BOLD));

		infoPanel.add(new JLabel("Description"));
		JTextArea desc = new JTextArea(20, 10);
		desc.setText(c.getDescription());
		desc.setLineWrap(true);
		desc.setWrapStyleWord(true);

		desc.getFont().deriveFont(Font.BOLD);
		desc.setForeground(Color.BLUE);
		infoPanel.add(new JScrollPane(desc));

		Utils.makeCompactGrid(infoPanel, 2, 2, // rows, cols
				6, 6, // initX, initY
				10, 10); // xPad, yPad

		abc = new JFrame();
		abc.setBounds(900, 300, desc.getWidth(), desc.getHeight());

		abc.setVisible(true);

		JOptionPane.showMessageDialog(abc, infoPanel,
				"Showing Career Information", JOptionPane.PLAIN_MESSAGE);

	}

	public AdaptRankingDialog(JFrame main, String sideImage, String title) {
		super(main, true);
		MySql.init();
		this.title = title;
		this.sideImage = sideImage;
	}

	public ArrayList<MajorInfo> getMajorInfo() {
		return majorInfo;
	}

	public void setMajorInfo(ArrayList<MajorInfo> majorInfo) {
		this.majorInfo = majorInfo;
	}

	private void configureFrame(String sideImage, String title, boolean flag) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
		}

		this.setTitle(title);

		image = new JLabel();
		image.setIcon(new ImageIcon(FileIO.findFile(sideImage)));
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(image, BorderLayout.WEST);

		/**
		 * *******************************************************
		 */
		JPanel panel = new JPanel();
		// GridLayout theLayout=new GridLayout(7,2);

		panel.setLayout(new SpringLayout());

		panel.add(new JLabel("Major Title"));
		panel.add(majorTitle = new JLabel());
		majorTitle.setForeground(java.awt.Color.blue);
		majorTitle.setFont(majorTitle.getFont().deriveFont(Font.BOLD));

		panel.add(new JLabel("Band/Sub-Band"));
		panel.add(majorBand = new JLabel());
		majorBand.setForeground(java.awt.Color.blue);
		majorBand.setFont(majorBand.getFont().deriveFont(Font.BOLD));

		panel.add(new JLabel("Evaluation Sum"));
		panel.add(evaluation = new JLabel());
		evaluation.setForeground(java.awt.Color.blue);
		evaluation.setFont(evaluation.getFont().deriveFont(Font.BOLD));

		panel.add(new JLabel("No. of Cases"));
		panel.add(this.noOfCases = new JLabel());
		noOfCases.setForeground(java.awt.Color.blue);
		noOfCases.setFont(noOfCases.getFont().deriveFont(Font.BOLD));

		panel.add(new JLabel("Total Cases"));
		panel.add(this.totalCases = new JLabel());
		totalCases.setForeground(java.awt.Color.blue);
		totalCases.setFont(totalCases.getFont().deriveFont(Font.BOLD));

		this.matchCases = new JList<String>(casesListModel);
		matchCases.setForeground(java.awt.Color.blue);
		matchCases.setFont(matchCases.getFont().deriveFont(Font.BOLD));

		this.matchCases.setVisibleRowCount(3);
		panel.add(new JLabel("Matched Cases"));
		panel.add(new JScrollPane(matchCases));

		// panel.add(matchCases);
		matchCases.setBackground(this.getBackground());
		matchCases.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent arg0) {

				if (matchCases.getSelectedIndex() >= 0) {
					showOneCase(currentMajorInfo.getMatchCases().get(
							matchCases.getSelectedIndex()));
				}
			}

		});

		if (flag == true) {
			panel.add(new JLabel("No. of Careers"));
			panel.add(this.noOfOccup = new JLabel());
			noOfOccup.setForeground(java.awt.Color.blue);
			noOfOccup.setFont(noOfOccup.getFont().deriveFont(Font.BOLD));

			this.matchCareers = new JList<String>(careersListModel);
			matchCareers.setForeground(java.awt.Color.blue);
			matchCareers.setFont(matchCareers.getFont().deriveFont(Font.BOLD));
			this.matchCareers.setVisibleRowCount(3);
			panel.add(new JLabel("Matched Careers"));
			panel.add(new JScrollPane(matchCareers));

			matchCareers.setBackground(this.getBackground());
			matchCareers.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent arg0) {
					if (matchCareers.getSelectedIndex() >= 0) {
						showCareerInfo();
					}
					// abc.dispose();
				}

			});
		}
		panel.add(new JLabel("Total Score"));
		panel.add(this.totalScore = new JLabel());
		totalScore.setForeground(java.awt.Color.blue);
		totalScore.setFont(totalScore.getFont().deriveFont(Font.BOLD));

		if (flag == true) {
			this.matchUnivs = new JList<Object>(univsListModel);
			matchUnivs.setForeground(java.awt.Color.blue);
			matchUnivs.setFont(matchUnivs.getFont().deriveFont(Font.BOLD));
			this.matchUnivs.setVisibleRowCount(4);
			panel.add(new JLabel("Universities"));
			panel.add(new JScrollPane(matchUnivs));
		}
		int panelSize;
		if (flag == true)
			panelSize = 10;
		else
			panelSize = 7;
		Utils.makeCompactGrid(panel, panelSize, 2, // rows, cols
				6, 6, // initX, initY
				10, 10); // xPad, yPad

		JPanel casesPanel = new JPanel();
		casesPanel.setLayout(new BorderLayout());
		casesPanel.add(panel, BorderLayout.CENTER);

		JPanel casesIterPanel = new JPanel();
		casesIterPanel.setLayout(new FlowLayout());
		JButton prev = new JButton("<<");
		casesIterPanel.add(prev);
		casesIterPanel.add(caseId = new JLabel("Case id"));
		JButton follow = new JButton(">>");
		casesIterPanel.add(follow);

		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentField = (currentField + majorInfo.size() - 1)
						% majorInfo.size();
				showField();
			}
		});

		follow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentField = (currentField + 1) % majorInfo.size();
				showField();
			}
		});

		casesPanel.add(casesIterPanel, BorderLayout.NORTH);

		JPanel panelAux = new JPanel();
		panelAux.setLayout(new BorderLayout());
		panelAux.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		panelAux.add(casesPanel, BorderLayout.NORTH);

		JPanel buttons = new JPanel();
		buttons.setLayout(new BorderLayout());

		JButton ok = new JButton("Next >>");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				next();
			}
		});
		buttons.add(ok, BorderLayout.CENTER);
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FRStart.getInstance().postCycle();
				} catch (Exception ex) {
					org.apache.commons.logging.LogFactory.getLog(FRStart.class)
							.error(ex);
				}
				System.exit(-1);
			}
		});
		buttons.add(exit, BorderLayout.WEST);

		panelAux.add(buttons, BorderLayout.SOUTH);
		this.getContentPane().add(panelAux, BorderLayout.CENTER);

		/**
		 * *******************************************************
		 */
		this.pack();
		this.setSize(600, this.getHeight());
		this.setResizable(false);
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		setBounds((screenSize.width - this.getWidth()) / 2,
				(screenSize.height - this.getHeight()) / 2, getWidth(),
				getHeight());
	}

	void next() {
		this.setVisible(false);
	}

	public void showFields(Collection<RetrievalResult> eval,
			Collection<CBRCase> selected, ArrayList<RelevantMajor> relMaj,
			boolean flag) {
		this.flag = flag;
		configureFrame(sideImage, title, flag);
		try {

			majorInfo = new ArrayList<MajorInfo>();
			cases = new ArrayList<RetrievalResult>();
			for (RetrievalResult rr : eval) {
				if (selected.contains(rr.get_case())) {
					cases.add(rr);
				}
			}
			@SuppressWarnings("unused")
			StudCaseDescription desc;
			StudCaseSolution solu;
			CBRCase tempCase;
			//Iteratos on retrieved cases in outside loop
			Iterator<RetrievalResult> outiter = cases.listIterator(); 
			//Iterates on retrieved cases inside loop
			Iterator<RetrievalResult> insiter; 
			//Iterates on all cases in the case base
			Iterator<RetrievalResult> insiter2; // 
			// iterates on relevant careers
			Iterator<RelevantMajor> reliter; 
			int occpCount = 0;
			// Stores the number of matched cases for a given major 
			// from selected cases
			Double mcasesCount = 0.0;
			// Stores the number of matched cases for a given field of study
			// from the whole case base
			Double wmcasesCount = 0.0; 
			int casesCounter = 0;
			// The sum of evaluations for matched cases's for a field of study
			Double evalSum = 0.0;
			// The description of the field of study
			String majorDescription = "";
			// The band of the field of study of 
			String band = "";
			// outer loops on the cases
			while (outiter.hasNext()) {

				tempCase = outiter.next().get_case();
				desc = (StudCaseDescription) tempCase.getDescription();
				solu = (StudCaseSolution) tempCase.getSolution();

				reliter = relMaj.iterator();
				occpCount = 0;

				ArrayList<Career> matchCareers = new ArrayList<Career>();
				ArrayList<University> matchUnivs = new ArrayList<University>();

				Major tempMajor = checkMajor(solu.getFieldofStudy());

				majorDescription = tempMajor.getDescription();

				band = tempMajor.getBand();

				matchUnivs = checkUnivCapacity(band);

				Collections.sort(matchUnivs);
				Collections.reverse(matchUnivs);
				// this loop checks wheter the a given field of study is
				// relevant based on its careers
				if (flag == true)// only if adaptation is required
				{
					while (reliter.hasNext()) {
						RelevantMajor mj = (RelevantMajor) reliter.next();

						if (mj.getMajorTitle().equals(solu.getFieldofStudy())) {
							{
								Career c = checkCareer(mj.getCareerTitle());
								matchCareers.add(c);
								occpCount++;
							}
						}
					}
				}
				mcasesCount = 0.0;
				wmcasesCount = 0.0;
				evalSum = 0.0; 
				insiter = cases.listIterator();
				ArrayList<CBRCase> matchCases = new ArrayList<CBRCase>();
				casesCounter = 0;	
				// the loop below iterates on retrieved cases and compare them
				// with the current case in outer loop
				// it is used to know on which selected cases the current case's
				// field of study is present
				while (insiter.hasNext()) {
					CBRCase caseCompared = insiter.next().get_case();

					StudCaseSolution soluCompared = (StudCaseSolution) caseCompared
							.getSolution();

					if (soluCompared.getFieldofStudy().equals(
							solu.getFieldofStudy())) {
						mcasesCount++;
						matchCases.add(caseCompared);
						RetrievalResult rr = cases.get(casesCounter);
						evalSum = evalSum + (double) rr.getEval();
					}
					casesCounter++;
				}// end of small loop
				// used to get the no of times a
				// particular field on occurred in
				// the whole case base
				insiter2 = eval.iterator();
				while (insiter2.hasNext()) {
					CBRCase caseCompared = insiter2.next().get_case();

					StudCaseSolution soluCompared = (StudCaseSolution) caseCompared
							.getSolution();

					if (soluCompared.getFieldofStudy().equals(
							solu.getFieldofStudy())) {
						wmcasesCount++;
					}
				}// end of inner loop
				Double ts; // total score

				if (flag == false) // just don't adapt using careers
				{
					ts = evalSum * (mcasesCount / wmcasesCount);
				} else {
					ts = (evalSum * (mcasesCount / wmcasesCount)) + occpCount;
				}
				// add the major to final result only if it is not present
				// already
				if (searchField(solu.getFieldofStudy()) == false) {
					majorInfo.add(new MajorInfo(solu.getFieldofStudy(),
							majorDescription, band, evalSum, mcasesCount,
							wmcasesCount, matchCases, occpCount, matchCareers,
							ts, matchUnivs));
				}
			}// end of bigger loop
			// sort by total scores in ascending order then reverse
			Collections.sort(majorInfo);
			Collections.reverse(majorInfo);

		} catch (Exception e) {

			System.out.println("error!!!" + e.getMessage());
			e.printStackTrace();
		}
		currentField = 0;
		showField();

	}

	void showField() {

		casesListModel.clear();

		if (flag == true) {
			careersListModel.clear();
			univsListModel.clear();
		}

		this.caseId.setText(currentField + 1 + "/" + majorInfo.size());

		currentMajorInfo = majorInfo.get(currentField);

		this.majorTitle.setText(currentMajorInfo.getMajorTitle());
		this.majorBand.setText(currentMajorInfo.getBand());
		this.evaluation.setText(currentMajorInfo.getEvaluation().toString());
		this.noOfCases.setText(currentMajorInfo.getNoOfCases().toString());
		this.totalCases.setText(currentMajorInfo.getTotalCases().toString());

		Iterator<?> it = currentMajorInfo.getMatchCases().iterator();
		while (it.hasNext()) {
			CBRCase c = (CBRCase) it.next();
			StudCaseDescription desc = (StudCaseDescription) c.getDescription();
			casesListModel.addElement(desc.getCaseId());
		}
		if (flag == true) {
			it = currentMajorInfo.getMatchCareers().iterator();
			while (it.hasNext()) {
				careersListModel.addElement(((Career) it.next()).getSOCTitle());
			}
			it = currentMajorInfo.getMatchUnivs().iterator();
			while (it.hasNext()) {
				University uu = (University) it.next();
				univsListModel.addElement(uu.getName() + "(" + uu.getCapacity()
						+ ")");
			}

			this.noOfOccup.setText(currentMajorInfo.getNoOfOccup().toString());
		}
		this.totalScore.setText(currentMajorInfo.getTotalScore().toString());
	}

	private ArrayList<University> checkUnivCapacity(String band) {
		Statement statement = null;
		ResultSet resultSet;
		ArrayList<University> univColl = new ArrayList<University>();
		String abbr = "";
		String name = "";
		Integer capacity = 0;

		try {
			statement = MySql.db.createStatement();
			String query = "SELECT univ.UnivAbbrivation, univ.UnivName, univ_cap.Capacity FROM univ inner join univ_cap on univ.UnivAbbrivation=univ_cap.UnivAbb WHERE univ_cap.Band='"
					+ band + "'";
			resultSet = statement.executeQuery(query);	

			while (resultSet.next()) {
				abbr = resultSet.getObject(1).toString();
				name = resultSet.getObject(2).toString();
				capacity = Integer
						.parseInt((resultSet.getObject(3).toString()));

				univColl.add(new University(abbr, name, band, capacity));

			} // end while

			return univColl;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	private Career checkCareer(String ct) {

		Statement statement = null;
		ResultSet resultSet;
		String SOCTitle = "";
		String ISCOTitle = "";
		String Description = "";

		try {
			statement = MySql.db.createStatement();
			String query = "SELECT * FROM career WHERE SOCTitle='" + ct + "'";
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				SOCTitle = resultSet.getObject(1).toString();
				ISCOTitle = resultSet.getObject(2).toString();
				Description = resultSet.getObject(3).toString();
			} // end while

			return new Career(SOCTitle, ISCOTitle, Description);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		// return null;

	}

	private Major checkMajor(String mt) {

		Statement statement = null;
		ResultSet resultSet;
		String majorTitle = "";
		String Band = "";
		String Description = "";

		try {
			statement = MySql.db.createStatement();
			String query = "SELECT MajorTitle,Band,Description FROM major WHERE majorTitle='"
					+ mt + "'";
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				majorTitle = resultSet.getObject(1).toString();
				Band = resultSet.getObject(2).toString();
				Description = resultSet.getObject(3).toString();

			} // end while

			Major m = new Major(majorTitle, Band, Description);
			return m;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	private boolean searchField(String fieldofStudy) {

		Iterator<MajorInfo> it = majorInfo.listIterator();
		while (it.hasNext()) {
			MajorInfo st = (MajorInfo) it.next();
			if (st.getMajorTitle().equals(fieldofStudy)) {
				return true;
			}

		}
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AdaptRankingDialog qf = new AdaptRankingDialog(null,
				"/tiCBR/gui/step4.png", "");
		qf.setVisible(true);
		System.out.println("Bye");
	}

}
