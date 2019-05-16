package tiCBR.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import tiCBR.FRStart;
import tiCBR.StudCaseDescription;
import tiCBR.StudCaseSolution;
import jcolibri.cbrcore.CBRCase;
import jcolibri.util.FileIO;

public class RetainDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private static int numcases = 0;

	JLabel image;

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
	JLabel caseId;
	JLabel FieldofStudy;
	JLabel FHS;

	JButton setId;
	JCheckBox saveCheck;

	ArrayList<CBRCase> cases;
	int currentCase;

	ArrayList<CBRCase> casesToRetain;

	private JTextField idEditor;

	public RetainDialog(JFrame main) {
		super(main, true);
		configureFrame();
	}

	private void configureFrame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
		}

		this.setTitle("Revise cases");

		image = new JLabel();
		image.setIcon(new ImageIcon(
				FileIO.findFile("tiCBR/gui/step6.png")));
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(image, BorderLayout.WEST);

		/**********************************************************/
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
				currentCase = (currentCase + cases.size() - 1) % cases.size();
				showCase();
			}
		});

		follow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentCase = (currentCase + 1) % cases.size();
				showCase();
			}
		});

		casesPanel.add(casesIterPanel, BorderLayout.NORTH);

		JPanel defineIdsPanel = new JPanel();
		saveCheck = new JCheckBox("Save Case with new Id:");
		defineIdsPanel.add(saveCheck);
		saveCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableSaveCase();
			}
		});
		idEditor = new JTextField(20);
		defineIdsPanel.add(idEditor);
		setId = new JButton("Apply");
		defineIdsPanel.add(setId);

		setId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setId();
			}
		});
		enableSaveCase();

		casesPanel.add(defineIdsPanel, BorderLayout.SOUTH);

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

	void next() {
		this.setVisible(false);
	}

	void enableSaveCase() {
		idEditor.setEnabled(saveCheck.isSelected());
		setId.setEnabled(saveCheck.isSelected());
	}

	public void showCases(Collection<CBRCase> eval, int casebasesize) {
		cases = new ArrayList<CBRCase>(eval);
		casesToRetain = new ArrayList<CBRCase>();
		currentCase = 0;
		if (numcases < casebasesize)
			numcases = casebasesize + 1;
		idEditor.setText("StudCase" + (++numcases));
		showCase();
	}

	void showCase() {

		CBRCase _case = cases.get(currentCase);
		this.caseId
		.setText(" (" + (currentCase + 1) + "/" + cases.size() + ")");

		StudCaseDescription desc = (StudCaseDescription) _case.getDescription();

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
	}

	void setId() {
		CBRCase _case = cases.get(currentCase);
		cases.remove(_case);

		StudCaseDescription desc = (StudCaseDescription) _case.getDescription();

		desc.setCaseId(idEditor.getText());

		StudCaseSolution sol = (StudCaseSolution) _case.getSolution();

		sol.setId(idEditor.getText());

		casesToRetain.add(_case);

		currentCase = 0;
		idEditor.setText("StudCase" + (++numcases));
		saveCheck.setSelected(false);
		enableSaveCase();
		showCase();
	}

	public Collection<CBRCase> getCasestoRetain() {
		return casesToRetain;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RetainDialog qf = new RetainDialog(null);
		qf.setVisible(true);
		System.out.println("Bye");
	}

}
