package tiCBR.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tiCBR.FRStart;
import tiCBR.MySql;
import tiCBR.RelevantMajor;
import tiCBR.StudCaseDescription;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.datatypes.Instance;
import jcolibri.util.FileIO;

public class QueryDialog extends JDialog {

	private ArrayList<RelevantMajor> relMaj;
	private ResultSet resultSet;

	private static final long serialVersionUID = 1L;
	StudCaseDescription desc;
	// private String fh, sh, th; // codes above 20 are stored here;, no more
	// top holland
	private String fh_sorted = "", sh_sorted = "", th_sorted = ""; // just

	// without
	// regard to
	// 20, three
	// top
	// hollands
	// are
	// stored
	// here

	public String getFh_sorted() {
		return fh_sorted;
	}

	public void setFh_sorted(String fh_sorted) {
		this.fh_sorted = fh_sorted;
	}

	public String getSh_sorted() {
		return sh_sorted;
	}

	public void setSh_sorted(String sh_sorted) {
		this.sh_sorted = sh_sorted;
	}

	public String getTh_sorted() {
		return th_sorted;
	}

	public void setTh_sorted(String th_sorted) {
		this.th_sorted = th_sorted;
	}

	private int[][] personality;// codes are sorted and kept on this array

	JLabel image;
	JComboBox<String> Stream;
	ItemSelector BackgroundInterest;
	SpinnerNumberModel Duration;
	SpinnerNumberModel EntranceResult;
	ItemSelector EntranceTop;
	ItemSelector FamilyInterest;
	ItemSelector PastExperience;
	ItemSelector Preparatory1st;
	ItemSelector Preparatory2nd;
	ItemSelector SpecialSkill;
	SpinnerNumberModel Realistic;
	SpinnerNumberModel Investigative;
	SpinnerNumberModel Artistic;
	SpinnerNumberModel Social;
	SpinnerNumberModel Enterprising;
	SpinnerNumberModel Conventional;

	JLabel sum;

	public QueryDialog(JFrame parent) {
		super(parent, true);
		configureFrame();

	}

	public void checkCareers() {

		// now to get careers, there is no top hollands, rather just the primary
		// interest area
		// is only needed, that is stored on fh_sorted
		// then according to o*net all careers that contain that as primary will
		// be retrived

		Statement statement = null;
		MySql.init();
		try {
			statement = MySql.db.createStatement();
			String query = "";

			// according to o*net interest tool
                        //one or two holland codes may be part of primary interest
			// thus.. I need to check using if
			String fullPrimary = getFh_sorted();
			query = "SELECT major_career.Major_Title, career.SOCTitle, career.FST  FROM major_career inner join career on major_career.Career_Title=career.SOCTitle";
			//if the primary interest contains single area then the following query is used
                        if (fullPrimary.length() ==1) {
				query = query + " WHERE career.FST LIKE '%" + fullPrimary+ "%'";
			} else if (fullPrimary.length() == 2)
				query = query + " WHERE career.FST LIKE '" + fullPrimary.substring(0, 1)
						+ "%' OR career.FST LIKE '" + fullPrimary.substring(1, 2) + "%' OR career.FST LIKE '%"+fullPrimary+"%'"
                                                +"OR career.FST LIKE '%"+fullPrimary.substring(1,2)+fullPrimary.substring(0,1)+"%'";
					
                        resultSet = statement.executeQuery(query);
			System.out.println();
			relMaj = new ArrayList<RelevantMajor>();

			String majorTitle;
			String careerTitle;
			String careerFST;

JPanel panel=new JPanel();
panel.setLayout(new BorderLayout());
			while (resultSet.next()) {
				majorTitle = resultSet.getObject(1).toString().trim();
				careerTitle = resultSet.getObject(2).toString().trim();
				careerFST = resultSet.getObject(3).toString().trim();
				relMaj.add(new RelevantMajor(majorTitle, careerTitle, careerFST));
			} // end while

			/*
			 * System.out.println("trying to display the dynamic array list(" +
			 * relMaj.size() + ")");
			 * 
			 * Iterator<RelevantMajor> it = relMaj.iterator();
			 * 
			 * while (it.hasNext()) {
			 * 
			 * System.out.println(it.next().getMajorTitle());
			 * System.out.println();
			 * 
			 * }
			 */
			// }
			// else
			// System.out
			// .println("\nNo highscores detected, Barett's advice...");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void configureFrame() {
		try {

			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			this.setTitle("Configure Query");

			image = new JLabel();
			image.setIcon(new ImageIcon(FileIO.findFile("tiCBR/gui/step1.png")));
			this.getContentPane().setLayout(new BorderLayout());
			this.getContentPane().add(image, BorderLayout.WEST);

			/**********************************************************/
			JPanel panel = new JPanel();
			panel.setLayout(new SpringLayout());

			JLabel label;

			panel.add(label = new JLabel("Attribute"));
			label.setFont(label.getFont().deriveFont(Font.BOLD));
			panel.add(label = new JLabel("Value"));
			label.setFont(label.getFont().deriveFont(Font.BOLD));

			panel.add(new JLabel("Stream"));
			String[] streamanswers = { "NATURAL", "SOCIAL" };
			panel.add(Stream = new JComboBox<String>(streamanswers));

			panel.add(new JLabel("Duration"));
			Duration = new SpinnerNumberModel(3, 1, 7, 1);
			panel.add(new JSpinner(Duration));

			panel.add(new JLabel("Entrance Result"));
			EntranceResult = new SpinnerNumberModel(400, 1, 700, 1);
			panel.add(new JSpinner(EntranceResult));

			panel.add(new JLabel("Background Interest"));
			BackgroundInterest = new ItemSelector(this);
			Instance ins = new Instance("MEDICINE");
			BackgroundInterest.setSelectedInstance(ins);
			panel.add(BackgroundInterest);

			panel.add(new JLabel("Family Interest"));
			FamilyInterest = new ItemSelector(this);
			// ins = new Instance("MEDICINE");
			FamilyInterest.setSelectedInstance(ins);
			panel.add(FamilyInterest);

			panel.add(new JLabel("Entrance Top Subject"));
			EntranceTop = new ItemSelector(this);
			ins = new Instance("PHYSICS_SUBJECT");
			EntranceTop.setSelectedInstance(ins);
			panel.add(EntranceTop);

			panel.add(new JLabel("Prep 1st"));
			Preparatory1st = new ItemSelector(this);
			ins = new Instance("CIVIC_SUBJECT");
			Preparatory1st.setSelectedInstance(ins);
			panel.add(Preparatory1st);

			panel.add(new JLabel("Prep 2nd"));
			Preparatory2nd = new ItemSelector(this);
			ins = new Instance("ENGLISH_SUBJECT");
			Preparatory2nd.setSelectedInstance(ins);
			panel.add(Preparatory2nd);

			panel.add(new JLabel("Special Skill"));
			SpecialSkill = new ItemSelector(this);
			ins = new Instance("UNDISCOVERED");
			SpecialSkill.setSelectedInstance(ins);
			panel.add(SpecialSkill);

			panel.add(new JLabel("Past Experience"));
			PastExperience = new ItemSelector(this);
			ins = new Instance("UNDISCOVERED");
			PastExperience.setSelectedInstance(ins);
			panel.add(PastExperience);
			// */
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

			// Lay out the panel.
			Utils.makeCompactGrid(panel, 11, 2, // rows, cols
					3, 3, // initX, initY
					10, 10); // xPad, yPad

			JPanel panelAux = new JPanel();
			panelAux.setLayout(new BorderLayout());
			panelAux.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			panelAux.add(panel, BorderLayout.NORTH);

			JPanel hollandCodes = new JPanel();
			hollandCodes.setLayout(new SpringLayout());

			hollandCodes.add(new JLabel("Realistic"));
			Realistic = new SpinnerNumberModel(21, 1, 30, 1);
			hollandCodes.add(new JSpinner(Realistic));

			Realistic.addChangeListener(new comboListener());

			hollandCodes.add(new JLabel("Investigative"));
			Investigative = new SpinnerNumberModel(23, 1, 30, 1);
			hollandCodes.add(new JSpinner(Investigative));
			Investigative.addChangeListener(new comboListener());

			hollandCodes.add(new JLabel("Artistic"));
			Artistic = new SpinnerNumberModel(12, 1, 30, 1);
			hollandCodes.add(new JSpinner(Artistic));
			Artistic.addChangeListener(new comboListener());

			hollandCodes.add(new JLabel("Social"));
			Social = new SpinnerNumberModel(12, 1, 30, 1);
			hollandCodes.add(new JSpinner(Social));
			Social.addChangeListener(new comboListener());

			hollandCodes.add(new JLabel("Enterprising"));
			Enterprising = new SpinnerNumberModel(9, 1, 30, 1);
			hollandCodes.add(new JSpinner(Enterprising));
			Enterprising.addChangeListener(new comboListener());

			hollandCodes.add(new JLabel("Conventional"));
			Conventional = new SpinnerNumberModel(13, 1, 30, 1);
			hollandCodes.add(new JSpinner(Conventional));
			Conventional.addChangeListener(new comboListener());

			/*
			 * hollandCodes.add(new JLabel("Sum")); sum = new JLabel("?");
			 * hollandCodes.add(sum);
			 */
			Utils.makeCompactGrid(hollandCodes, 2, 6, // rows, cols
					3, 3, // initX, initY
					10, 10); // xPad, yPad

			JPanel buttons = new JPanel();
			buttons.setLayout(new BorderLayout());

			JButton ok = new JButton("Set Query >>>");

			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setQuery();

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

			panelAux.add(hollandCodes, BorderLayout.CENTER);
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

		} catch (Exception e1) {
			javax.swing.JOptionPane.showMessageDialog(null,
					"Catched Exception in constructor");
			e1.printStackTrace();
		}
	}

	void setQuery() {
		this.setVisible(false);
	}

	public CBRQuery getQuery() {
		desc = new StudCaseDescription();

		desc.setStream((String) this.Stream.getSelectedItem());
		// System.out.println((String) this.Stream.getSelectedItem());
		desc.setBackgroundInterest(this.BackgroundInterest
				.getSelectedInstance());
		// JOptionPane.showMessageDialog(null, "here is the bi"+
		// desc.getBackgroundInterest());

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
		String FHS = "";

		/*
		 * if (getFh() == null) FHS = "null"; else if (getSh() == null) FHS =
		 * getFh(); else if (getTh() == null) FHS = getFh() + getSh(); else FHS
		 * = getFh() + getSh() + getTh();
		 */

		FHS = getFh_sorted() + getSh_sorted() + getTh_sorted();
		desc.setFHS(FHS);

		CBRQuery query = new CBRQuery();
		query.setDescription(desc);
		sort();
		return query;
	}

	/**
	 * @param args
	 */

	public void sort() {
		personality = new int[3][6];
		// the first row stores the indexes, the indexes are analogous to RIASEC
		personality[0][0] = 0;
		personality[0][1] = 1;
		personality[0][2] = 2;
		personality[0][3] = 3;
		personality[0][4] = 4;
		personality[0][5] = 5;
		// the second row stores the person's result for the attributes
		personality[1][0] = desc.getRealistic();
		personality[1][1] = desc.getInvestigative();
		personality[1][2] = desc.getArtistic();
		personality[1][3] = desc.getSocial();
		personality[1][4] = desc.getEnterprising();
		personality[1][5] = desc.getConventional();
		// the third row stores the rank of the attributes
		personality[2][0] = -1;
		personality[2][1] = -1;
		personality[2][2] = -1;
		personality[2][3] = -1;
		personality[2][4] = -1;
		personality[2][5] = -1;

		//this sorts the holland intereset areas largest to smallest
		int highest;
		for (int i = 0; i < personality[1].length - 1; i++) {
			highest = i;
			for (int index = i + 1; index < personality[1].length; index++)
				if (personality[1][index] > personality[1][highest])
					highest = index;
			int temporary = personality[1][i];
			personality[1][i] = personality[1][highest];
			personality[1][highest] = temporary;
			
			temporary = personality[0][i];
			personality[0][i] = personality[0][highest];
			personality[0][highest] = temporary;
		}
        
		// this loop below determines the rank of the attributes
		for (int i = 0; i < 5; i++) {

			if (personality[2][i] == -1) {
			if(i==0)
                        {
                            personality[2][i] = i + 1;
			    for (int j = i + 1; j < 5; j++) {
					//according to the interst tool, a score within 5 or below range is in one category
					if (Math.abs(personality[1][i]-personality[1][j])<=5) {
						personality[2][j] = i + 1;
					}
				}
			}
                        else
                        {
                            int rank=personality[2][i-1]+1;
                            personality[2][i] =rank ;
			    for (int j = i + 1; j <=5; j++) {
					//according to the interst tool, a score within 5 or below range is in one category
					if (personality[1][i]==personality[1][j]) {
						personality[2][j] = rank;
					}
				}
                            
                        }
                       }

		}

                for(int i=0;i<3;i++)
                {
                
                    for(int j=0;j<6;j++)
                    {
                        System.out.print(personality[i][j]+"    ");
                    }
                        System.out.println();

                        
                        
                }
                
		// the loop below brings similar ranks 1-3 together into 3 variables

		fh_sorted = "";
		sh_sorted = "";
		th_sorted = "";

		for (int i = 0; i < 5; i++) {
			if (personality[2][i] == 1)
				fh_sorted += detHoll(personality[0][i]);
			else if (personality[2][i] == 2)
				sh_sorted += detHoll(personality[0][i]);
			else if (personality[2][i] == 3)
				th_sorted += detHoll(personality[0][i]);
		}

		
		System.out.print("\n\nSorted as follows: ");
		for (int i = 0; i < 5; i++) {
		System.out.print(detHoll(personality[0][i])+"("+personality[1][i]+")");
		}
		System.out.println();
		System.out.println("Primary===>"+fh_sorted);
		System.out.println("Secondary===>"+sh_sorted);
		System.out.println("Tertiary===>"+th_sorted);

	}

	public String detHoll(int code) {
		switch (code) {
		case 0:
			return "R";
		case 1:
			return "I";
		case 2:
			return "A";
		case 3:
			return "S";
		case 4:
			return "E";
		case 5:
			return "C";
		default:
			return null;
		}
	}

	public static void main(String[] args) {
		QueryDialog qf = new QueryDialog(null);
		qf.setVisible(true);
		System.out.println("Bye");
	}

	public ArrayList<RelevantMajor> getRelMaj() {
		return relMaj;
	}

	public void setRelMaj(ArrayList<RelevantMajor> relMaj) {
		this.relMaj = relMaj;
	}

}
