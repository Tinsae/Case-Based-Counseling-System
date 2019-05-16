
package tiCBR.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tiCBR.FRStart;
import tiCBR.StudCaseDescription;
import tiCBR.StudCaseSolution;
import jcolibri.cbrcore.CBRCase;
import jcolibri.util.FileIO;

public class RevisionDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	JLabel image;

	JComboBox<String> Stream;

	// JTextField BackgroundInterest;
	ItemSelector BackgroundInterest;

	SpinnerNumberModel Duration;
	// RegionSelector region;
	SpinnerNumberModel EntranceResult;

	// JTextField EntranceTop;
	ItemSelector EntranceTop;

	// JTextField FamilyInterest;
	ItemSelector FamilyInterest;

	ItemSelector PastExperience;

	// JTextField Preparatory1st;
	ItemSelector Preparatory1st;
	// JTextField Preparatory2nd;
	ItemSelector Preparatory2nd;

	// JTextField SpecialSkill;
	ItemSelector SpecialSkill;

	SpinnerNumberModel Realistic;
	SpinnerNumberModel Investigative;
	SpinnerNumberModel Artistic;
	SpinnerNumberModel Social;
	SpinnerNumberModel Enterprising;
	SpinnerNumberModel Conventional;

	JLabel caseId;
	JTextField FieldOfStudy;

	JLabel sum;

	ArrayList<CBRCase> cases;
	int currentCase;

	public RevisionDialog(JFrame main) {
		super(main, true);
		configureFrame();
	}

	private void configureFrame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			this.setTitle("Revise Cases");

			image = new JLabel();
			image.setIcon(new ImageIcon(
					FileIO.findFile("tiCBR/gui/step5.png")));
			this.getContentPane().setLayout(new BorderLayout());
			this.getContentPane().add(image, BorderLayout.WEST);

			/**********************************************************/
			JPanel panelTop = new JPanel();
			// panel.setLayout(new GridLayout(25,2));
			panelTop.setLayout(new SpringLayout());

			JLabel label;

			panelTop.add(label = new JLabel("Description"));
			label.setFont(label.getFont().deriveFont(Font.BOLD));
			panelTop.add(label = new JLabel());

			panelTop.add(new JLabel("Stream"));
			String[] streamanswers = { "NATURAL", "SOCIAL" };
			panelTop.add(Stream = new JComboBox<String>(streamanswers));

			panelTop.add(new JLabel("Duration"));
			Duration = new SpinnerNumberModel(3, 1, 7, 1);
			panelTop.add(new JSpinner(Duration));

			panelTop.add(new JLabel("Entrance Result"));
			EntranceResult = new SpinnerNumberModel(300, 1, 700, 1);
			panelTop.add(new JSpinner(EntranceResult));

			panelTop.add(new JLabel("Background Interest"));
			panelTop.add(BackgroundInterest = new ItemSelector(this));

			panelTop.add(new JLabel("Family Interest"));
			// panelTop.add(FamilyInterest = new JTextField("NATURAL SCIENCE"));
			panelTop.add(FamilyInterest = new ItemSelector(this));

			panelTop.add(new JLabel("Entrance Top Subject"));
			// String[] transportations = {"Plane","Car","Coach","Train"};
			panelTop.add(EntranceTop = new ItemSelector(this));

			panelTop.add(new JLabel("Prepartory Top Subject"));
			// panelTop.add(Preparatory1st = new JTextField("CIVICS"));
			panelTop.add(Preparatory1st = new ItemSelector(this));

			panelTop.add(new JLabel("Prepartory Second Top Subject"));
			// panelTop.add(Preparatory2nd = new JTextField("GEOGRAPHY"));
			panelTop.add(Preparatory2nd = new ItemSelector(this));

			panelTop.add(new JLabel("Special Skill"));
			panelTop.add(SpecialSkill = new ItemSelector(this));

			panelTop.add(new JLabel("Past Expeirence"));
			panelTop.add(PastExperience = new ItemSelector(this));

			JLabel label2;

			panelTop.add(label2 = new JLabel("Personality"));
			// label2.setVerticalTextPosition((SwingConstants.TOP));
			label2.setFont(label2.getFont().deriveFont(Font.BOLD));
			panelTop.add(label2 = new JLabel());

			label2.setSize(1, 1);
			panelTop.add(label2 = new JLabel());
			panelTop.add(label2 = new JLabel());

			// Lay out the panelTop.
			Utils.makeCompactGrid(panelTop, 13, 2, // rows, cols
					1, 1, // initX, initY
					30, 10); // xPad, yPad

			class comboListener implements ChangeListener {

				public void stateChanged(ChangeEvent arg0) {
					/*
					 * sum.setText((Realistic.getNumber().intValue() +
					 * Investigative.getNumber().intValue() +
					 * Artistic.getNumber().intValue() +
					 * Social.getNumber().intValue() +
					 * Enterprising.getNumber().intValue() + Conventional
					 * .getNumber().intValue()) + "");
					 */
				}

			}
			JPanel panelCenter = new JPanel(new SpringLayout());

			/*
			 * JLabel label2;
			 * 
			 * panelCenter.add(label2 = new JLabel("Personality"));
			 * label2.setFont(label2.getFont().deriveFont(Font.BOLD));
			 * panelCenter.add(label2 = new JLabel());
			 */
			panelCenter.add(new JLabel("Realistic"));
			Realistic = new SpinnerNumberModel(21, 1, 36, 1);
			panelCenter.add(new JSpinner(Realistic));
			Realistic.addChangeListener(new comboListener());

			panelCenter.add(new JLabel("Investigative"));
			Investigative = new SpinnerNumberModel(23, 1, 36, 1);
			panelCenter.add(new JSpinner(Investigative));
			Investigative.addChangeListener(new comboListener());

			panelCenter.add(new JLabel("Artistic"));
			Artistic = new SpinnerNumberModel(12, 1, 36, 1);
			panelCenter.add(new JSpinner(Artistic));
			Artistic.addChangeListener(new comboListener());

			panelCenter.add(new JLabel("Social"));
			Social = new SpinnerNumberModel(12, 1, 36, 1);
			panelCenter.add(new JSpinner(Social));
			Social.addChangeListener(new comboListener());

			panelCenter.add(new JLabel("Enterprising"));
			Enterprising = new SpinnerNumberModel(9, 1, 36, 1);
			panelCenter.add(new JSpinner(Enterprising));
			Enterprising.addChangeListener(new comboListener());

			panelCenter.add(new JLabel("Conventional"));
			Conventional = new SpinnerNumberModel(13, 1, 36, 1);
			panelCenter.add(new JSpinner(Conventional));
			Conventional.addChangeListener(new comboListener());

			// panelCenter.add(new JLabel("Sum"));
			// sum = new JLabel("?");
			// panelCenter.add(sum);

			Utils.makeCompactGrid(panelCenter, 2, 6, // rows, cols
					7, 7, // initX, initY
					10, 10); // xPad, yPad

			JPanel panelBottom = new JPanel(new SpringLayout());
			panelBottom.add(label = new JLabel("Solution"));
			label.setFont(label.getFont().deriveFont(Font.BOLD));
			panelBottom.add(label = new JLabel());

			panelBottom.add(new JLabel("Field of Study"));
			panelBottom.add(FieldOfStudy = new JTextField());

			Utils.makeCompactGrid(panelBottom, 2, 2, // rows, cols
					3, 3, // initX, initY
					10, 10); // xPad, yPad

			JPanel panel = new JPanel(new BorderLayout());
			panel.add(panelTop, BorderLayout.NORTH);
			panel.add(panelCenter, BorderLayout.CENTER);
			panel.add(panelBottom, BorderLayout.SOUTH);

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
					saveCase();
					currentCase = (currentCase + cases.size() - 1)
							% cases.size();
					showCase();
				}
			});

			follow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					saveCase();
					currentCase = (currentCase + 1) % cases.size();
					showCase();
				}
			});

			casesPanel.add(casesIterPanel, BorderLayout.NORTH);

			// JPanel hollandCodes=new JPanel(new SpringLayout());

			JPanel panelAux = new JPanel();
			panelAux.setLayout(new BorderLayout());
			panelAux.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			panelAux.add(casesPanel, BorderLayout.NORTH);

			JPanel buttons = new JPanel();
			buttons.setLayout(new BorderLayout());

			JButton ok = new JButton("Set Revisions >>");

			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			
					saveCase();
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
						org.apache.commons.logging.LogFactory.getLog(
								FRStart.class).error(ex);
					}
					System.exit(-1);
				}
			});
			buttons.add(exit, BorderLayout.WEST);

			panelAux.add(buttons, BorderLayout.SOUTH);
			this.getContentPane().add(panelAux, BorderLayout.CENTER);

			/**********************************************************/

			this.pack();
			this.setSize(600, this.getHeight());
			this.setResizable(false);
			Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
					.getScreenSize();
			setBounds((screenSize.width - this.getWidth()) / 2,
					(screenSize.height - this.getHeight()) / 2, getWidth(),
					getHeight());
		}

		catch (Exception e) {

			System.out.println("Design error " + e);

		}
	}

	void next() {
		this.setVisible(false);
	}

	public void showCases(ArrayList<CBRCase> cases) {

		this.cases = new ArrayList<CBRCase>(cases);
		currentCase = 0;
		showCase();
	}

	void showCase() {
		CBRCase _case = cases.get(currentCase);
		this.caseId.setText(_case.getID().toString() + " (" + (currentCase + 1)
				+ "/" + cases.size() + ")");
		// this.caseId.setText(currentCase + 1 + "/" + cases.size());

		StudCaseDescription desc = (StudCaseDescription) _case.getDescription();

		this.Stream.setSelectedItem(desc.getStream().toString());
		if (desc.getBackgroundInterest() != null)
			this.BackgroundInterest.setSelectedInstance(desc
					.getBackgroundInterest());
		if (desc.getDuration() != null)
			this.Duration.setValue(desc.getDuration());
		if (desc.getEntranceResult() != null)
			this.EntranceResult.setValue(desc.getEntranceResult());
		if (desc.getEntranceTop() != null)
			this.EntranceTop.setSelectedInstance(desc.getEntranceTop());
		if (desc.getFamilyInterest() != null)
			this.FamilyInterest.setSelectedInstance(desc.getFamilyInterest());
		if (desc.getPastExperience() != null)
			this.PastExperience.setSelectedInstance(desc.getPastExperience());
		if (desc.getPreparatory1st() != null)
			this.Preparatory1st.setSelectedInstance(desc.getPreparatory1st());
		if (desc.getPreparatory2nd() != null)
			this.Preparatory2nd.setSelectedInstance(desc.getPreparatory2nd());
		if (desc.getSpecialSkill() != null)
			this.SpecialSkill.setSelectedInstance(desc.getSpecialSkill());
		if (desc.getRealistic() != null)
			this.Realistic.setValue(desc.getRealistic());
		if (desc.getInvestigative() != null)
			this.Investigative.setValue(desc.getInvestigative());
		if (desc.getArtistic() != null)
			this.Artistic.setValue(desc.getArtistic());
		if (desc.getSocial() != null)
			this.Social.setValue(desc.getSocial());
		if (desc.getEnterprising() != null)
			this.Enterprising.setValue(desc.getEnterprising());
		if (desc.getConventional() != null)
			this.Conventional.setValue(desc.getConventional());
		// this.FHS.setText(desc.getFHS().toString());

		StudCaseSolution sol = (StudCaseSolution) _case.getSolution();
		this.FieldOfStudy.setText(sol.getFieldofStudy().toString());

	}

	void saveCase() {
		CBRCase _case = cases.get(currentCase);
		this.caseId.setText("(" + (currentCase + 1) + "/" + cases.size() + ")");

		StudCaseDescription desc = (StudCaseDescription) _case.getDescription();

		desc.setStream(this.Stream.getSelectedItem().toString());
		desc.setBackgroundInterest(this.BackgroundInterest
				.getSelectedInstance());
		desc.setDuration(this.Duration.getNumber().intValue());
		desc.setEntranceResult(this.EntranceResult.getNumber().intValue());
		desc.setEntranceTop(this.EntranceTop.getSelectedInstance());
		desc.setFamilyInterest(this.FamilyInterest.getSelectedInstance());

		desc.setPastExperience(this.PastExperience.getSelectedInstance());
		desc.setPreparatory1st(this.Preparatory1st.getSelectedInstance());
		desc.setPreparatory2nd(this.Preparatory2nd.getSelectedInstance());
		desc.setSpecialSkill(this.SpecialSkill.getSelectedInstance());

		desc.setRealistic(this.Realistic.getNumber().intValue());
		desc.setInvestigative(this.Investigative.getNumber().intValue());
		desc.setArtistic(this.Artistic.getNumber().intValue());
		desc.setSocial(this.Social.getNumber().intValue());
		desc.setEnterprising(this.Enterprising.getNumber().intValue());
		desc.setConventional(this.Conventional.getNumber().intValue());
		// this.FHS.setText(desc.setgetFHS().toString());

		StudCaseSolution sol = (StudCaseSolution) _case.getSolution();
		sol.setFieldofStudy(this.FieldOfStudy.getText());

	}

	public static void main(String[] args) {
		RevisionDialog qf = new RevisionDialog(null);
		qf.setVisible(true);
		System.out.println("Bye");
	}

}
