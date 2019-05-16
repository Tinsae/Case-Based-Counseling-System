package tiCBR.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tiCBR.FRStart;
import tiCBR.StudCaseDescription;
import jcolibri.cbrcore.Attribute;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.local.EnumCyclicDistance;
import jcolibri.method.retrieve.NNretrieval.similarity.local.EnumDistance;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Threshold;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntCosine;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDeep;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDeepBasic;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDetail;
import jcolibri.util.FileIO;

public class SimilarityDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private String fh_from_query;
    private String sh_from_query;
    private String th_from_query;

    public String getFh_from_query() {
        return fh_from_query;
    }

    public void setFh_from_query(String fh_from_query) {
        this.fh_from_query = fh_from_query;
    }

    public String getSh_from_query() {
        return sh_from_query;
    }

    public void setSh_from_query(String sh_from_query) {
        this.sh_from_query = sh_from_query;
    }

    public String getTh_from_query() {
        return th_from_query;
    }

    public void setTh_from_query(String th_from_query) {
        this.th_from_query = th_from_query;
    }

    JLabel image;

    SimilConfigPanel caseId;
    SimilConfigPanel Stream;
    SimilConfigPanel BackgroundInterest;
    SimilConfigPanel Duration;
    SimilConfigPanel EntranceResult;
    SimilConfigPanel EntranceTop;
    SimilConfigPanel FamilyInterest;

    SimilConfigPanel PastExperience;
    SimilConfigPanel Preparatory1st;
    SimilConfigPanel Preparatory2nd;
    SimilConfigPanel SpecialSkill;
    SimilConfigPanel Realistic;
    SimilConfigPanel Investigative;
    SimilConfigPanel Artistic;
    SimilConfigPanel Social;
    SimilConfigPanel Enterprising;
    SimilConfigPanel Conventional;

    SpinnerNumberModel k;

    public SimilarityDialog(JFrame main, String fh_from_query,
            String sh_from_query, String th_from_query) {
        super(main, true);
        this.fh_from_query = fh_from_query;
        this.sh_from_query = sh_from_query;
        this.th_from_query = th_from_query;

        configureFrame();
    }

    private void configureFrame() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e1) {
        }

        int size = 12;
        this.setTitle("Configure Similarity");

        image = new JLabel();
        image.setIcon(new ImageIcon(
                FileIO.findFile("tiCBR/gui/step2.png")));
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(image, BorderLayout.WEST);

        /**
         * *******************************************************
         */
        Vector<String> stringfunctions = new Vector<String>();
        stringfunctions.add("Equal");

        Vector<String> numberfunctions = new Vector<String>();
        numberfunctions.add("Threshold");
        numberfunctions.add("Interval");
        numberfunctions.add("Equal");

        Vector<String> enumfunctions = new Vector<String>();
        enumfunctions.add("EnumCyclicDistance");
        enumfunctions.add("EnumDistance");
        enumfunctions.add("Equal");

        Vector<String> ontofunctions = new Vector<String>();
        ontofunctions.add("OntCosine");
		// ontofunctions.add("OntDeep");
        // ontofunctions.add("OntDeepBasic");
        ontofunctions.add("OntDetail");
        ontofunctions.add("Equal");

        JPanel panel = new JPanel();
        // panel.setLayout(new GridLayout(10,2));
        panel.setLayout(new SpringLayout());

        JLabel label;
        panel.add(label = new JLabel("Attribute"));
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        JPanel l = new JPanel();
        l.setLayout(new GridLayout(1, 3));
        l.add(label = new JLabel("Function"));
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        l.add(label = new JLabel("Weight"));
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        l.add(label = new JLabel("Default"));
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        panel.add(l);
        l.add(label = new JLabel("Parameters"));
        label.setFont(label.getFont().deriveFont(Font.BOLD));

        panel.add(new JLabel("Stream"));
        panel.add(Stream = new SimilConfigPanel(stringfunctions, 0, 100, "Stream"));

        panel.add(new JLabel("Background Interest"));
        panel.add(BackgroundInterest = new SimilConfigPanel(ontofunctions, 0, 100, ""));

        panel.add(new JLabel("Duration"));
        panel.add(Duration = new SimilConfigPanel(numberfunctions, 2, 90, ""));

        panel.add(new JLabel("Entrance Result"));
        panel.add(EntranceResult = new SimilConfigPanel(numberfunctions, 0, 95, "Entrance"));

        panel.add(new JLabel("Entrance Top Subject"));
        panel.add(EntranceTop = new SimilConfigPanel(ontofunctions, 0, 80, ""));

        panel.add(new JLabel("Family Interest"));
        panel.add(FamilyInterest = new SimilConfigPanel(ontofunctions, 0, 100, ""));

        panel.add(new JLabel("Past Experience"));
        panel.add(PastExperience = new SimilConfigPanel(ontofunctions, 0, 90, ""));

        panel.add(new JLabel("Prepartory Top Subject"));
        panel.add(Preparatory1st = new SimilConfigPanel(ontofunctions, 0, 90, ""));

        panel.add(new JLabel("Prepratory Second Top Subject"));
        panel.add(Preparatory2nd = new SimilConfigPanel(ontofunctions, 0, 85, ""));

        panel.add(new JLabel("SpecialSkill"));
        panel.add(SpecialSkill = new SimilConfigPanel(ontofunctions, 0, 90, ""));

        Realistic = new SimilConfigPanel(numberfunctions, 2, 0, "R");
        Investigative = new SimilConfigPanel(numberfunctions, 2, 0, "I");
        Artistic = new SimilConfigPanel(numberfunctions, 2, 0, "A");
        Social = new SimilConfigPanel(numberfunctions, 2, 0, "S");
        Enterprising = new SimilConfigPanel(numberfunctions, 2, 0, "E");
        Conventional = new SimilConfigPanel(numberfunctions, 2, 0, "C");

        String[] fullNames = {"Realistic", "Investigative", "Artistic",
            "Social", "Enterprising", "Conventional"};

        for (String fullName : fullNames) {
            String interestArea = fullName.substring(0, 1);
            Integer weight = detWeight(interestArea);
            if (weight != 0) {
                panel.add(new JLabel(fullName));
                if (interestArea.equals("R")) {
                    panel.add(Realistic = new SimilConfigPanel(numberfunctions, 1, weight, interestArea));
                } else if (interestArea.equals("I")) {
                    panel.add(Investigative = new SimilConfigPanel(numberfunctions, 1, weight, interestArea));
                } else if (interestArea.equals("A")) {
                    panel.add(Artistic = new SimilConfigPanel(numberfunctions, 1, weight, interestArea));
                } else if (interestArea.equals("S")) {
                    panel.add(Social = new SimilConfigPanel(numberfunctions, 1, weight, interestArea));
                } else if (interestArea.equals("E")) {
                    panel.add(Enterprising = new SimilConfigPanel(numberfunctions, 1, weight, interestArea));
                } else if (interestArea.equals("C")) {
                    panel.add(Conventional = new SimilConfigPanel(numberfunctions, 1, weight, interestArea));
                }
                size++;
            }
        }
        panel.add(label = new JLabel("K"));
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        panel.add(new JSpinner(k = new SpinnerNumberModel(10, 1, 100, 1)));
        // Lay out the panel.
        Utils.makeCompactGrid(panel, size, 2, // rows, cols
                6, 6, // initX, initY
                19, 10); // xPad, yPad
        JPanel panelAux = new JPanel();
        panelAux.setLayout(new BorderLayout());
        panelAux.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelAux.add(panel, BorderLayout.NORTH);

        JPanel buttons = new JPanel();
        buttons.setLayout(new BorderLayout());

        JButton ok = new JButton("Set Similarity Configuration >>");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSimilarity();
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

    private Integer detWeight(String interestArea) {
		// this method determines the weight of a holland code based on its
                //value
        if (fh_from_query.contains(interestArea)) {
            return 100;
        } else if (sh_from_query.contains(interestArea)) {
            return 95;
        } else if (th_from_query.contains(interestArea)) {
            return 90;
        } else {
            return 0;
        }

    }

    void setSimilarity() {
        this.setVisible(false);
    }

    public NNConfig getSimilarityConfig() {
        // System.out.println("HEY3");

        NNConfig config = new NNConfig();
        Attribute attribute;
        SimilConfigPanel similConfig;
        LocalSimilarityFunction function;

        similConfig = Stream;
        attribute = new Attribute("Stream", StudCaseDescription.class);
        function = localSimilFactory(similConfig.getSimilFuntion(),
                similConfig.getParam());
        config.addMapping(attribute, function);
        config.setWeight(attribute, similConfig.getWeight());
        // config.setWeight(attribute, 0.0);

        similConfig = BackgroundInterest;
        attribute = new Attribute("BackgroundInterest",
                StudCaseDescription.class);
        function = localSimilFactory(similConfig.getSimilFuntion(),
                similConfig.getParam());
        config.addMapping(attribute, function);
        config.setWeight(attribute, similConfig.getWeight());
        // config.setWeight(attribute, 0.0);

        similConfig = Duration;
        attribute = new Attribute("Duration", StudCaseDescription.class);
        function = localSimilFactory(similConfig.getSimilFuntion(),
                similConfig.getParam());
        config.addMapping(attribute, function);
        config.setWeight(attribute, similConfig.getWeight());
        // config.setWeight(attribute, 0.0);

        similConfig = EntranceResult;
        attribute = new Attribute("EntranceResult", StudCaseDescription.class);
        function = localSimilFactory(similConfig.getSimilFuntion(),
                similConfig.getParam());
        config.addMapping(attribute, function);
        config.setWeight(attribute, similConfig.getWeight());
        // config.setWeight(attribute, 0.0);

        similConfig = EntranceTop;
        attribute = new Attribute("EntranceTop", StudCaseDescription.class);
        function = localSimilFactory(similConfig.getSimilFuntion(),
                similConfig.getParam());
        config.addMapping(attribute, function);
        config.setWeight(attribute, similConfig.getWeight());
        // config.setWeight(attribute, 0.0);

        similConfig = FamilyInterest;
        attribute = new Attribute("FamilyInterest", StudCaseDescription.class);
        function = localSimilFactory(similConfig.getSimilFuntion(),
                similConfig.getParam());
        config.addMapping(attribute, function);
        config.setWeight(attribute, similConfig.getWeight());

        similConfig = PastExperience;
        attribute = new Attribute("PastExperience", StudCaseDescription.class);
        function = localSimilFactory(similConfig.getSimilFuntion(),
                similConfig.getParam());
        config.addMapping(attribute, function);
        config.setWeight(attribute, similConfig.getWeight());

        similConfig = Preparatory1st;
        attribute = new Attribute("Preparatory1st", StudCaseDescription.class);
        function = localSimilFactory(similConfig.getSimilFuntion(),
                similConfig.getParam());
        config.addMapping(attribute, function);
        config.setWeight(attribute, similConfig.getWeight());

        similConfig = Preparatory2nd;
        attribute = new Attribute("Preparatory2nd", StudCaseDescription.class);
        function = localSimilFactory(similConfig.getSimilFuntion(),
                similConfig.getParam());
        config.addMapping(attribute, function);
        config.setWeight(attribute, similConfig.getWeight());

        similConfig = SpecialSkill;
        attribute = new Attribute("SpecialSkill", StudCaseDescription.class);
        function = localSimilFactory(similConfig.getSimilFuntion(),
                similConfig.getParam());
        config.addMapping(attribute, function);
        config.setWeight(attribute, similConfig.getWeight());
        
        similConfig = Realistic;
        attribute = new Attribute("Realistic", StudCaseDescription.class);
        function = localSimilFactory(similConfig.getSimilFuntion(),
                similConfig.getParam());
        config.addMapping(attribute, function);
        config.setWeight(attribute, similConfig.getWeight());

        similConfig = Investigative;
        attribute = new Attribute("Investigative", StudCaseDescription.class);
        function = localSimilFactory(similConfig.getSimilFuntion(),
                similConfig.getParam());
        config.addMapping(attribute, function);
        config.setWeight(attribute, similConfig.getWeight());

        similConfig = Artistic;
        attribute = new Attribute("Artistic", StudCaseDescription.class);
        function = localSimilFactory(similConfig.getSimilFuntion(),
                similConfig.getParam());
        config.addMapping(attribute, function);
        config.setWeight(attribute, similConfig.getWeight());

        similConfig = Social;
        attribute = new Attribute("Social", StudCaseDescription.class);
        function = localSimilFactory(similConfig.getSimilFuntion(),
                similConfig.getParam());
        config.addMapping(attribute, function);
        config.setWeight(attribute, similConfig.getWeight());

        similConfig = Enterprising;
        attribute = new Attribute("Enterprising", StudCaseDescription.class);
        function = localSimilFactory(similConfig.getSimilFuntion(),
                similConfig.getParam());
        config.addMapping(attribute, function);
        config.setWeight(attribute, similConfig.getWeight());

        similConfig = Conventional;
        attribute = new Attribute("Conventional", StudCaseDescription.class);
        function = localSimilFactory(similConfig.getSimilFuntion(),
                similConfig.getParam());
        config.addMapping(attribute, function);
        config.setWeight(attribute, similConfig.getWeight());

        return config;
    }

    public int getK() {
        return k.getNumber().intValue();
    }

    private LocalSimilarityFunction localSimilFactory(String name, int param) {
        if (name.equals("Equal")) {
            return new Equal();
        } else if (name.equals("Interval")) {
            return new Interval(param);
        } else if (name.equals("Threshold")) {
            return new Threshold(param);
        } else if (name.equals("EnumCyclicDistance")) {
            return new EnumCyclicDistance();
        } else if (name.equals("EnumDistance")) {
            return new EnumDistance();
        } else if (name.equals("OntCosine")) {
            return new OntCosine();
        } else if (name.equals("OntDeep")) {
            return new OntDeep();
        } else if (name.equals("OntDeepBasic")) {
            return new OntDeepBasic();
        } else if (name.equals("OntDetail")) {
            return new OntDetail();
        } else {
            org.apache.commons.logging.LogFactory.getLog(this.getClass())
                    .error("Simil Function not found");
            System.out.println("Similarity Function not found");
            return null;
        }
    }

    private class SimilConfigPanel extends JPanel {

        private static final long serialVersionUID = 1L;

        Vector<String> functions;
        JComboBox<String> functionCombo;
        SpinnerNumberModel param;
        JSpinner paramSpinner;
        JSlider weight;
        JLabel weightValue;
        String title;

        public SimilConfigPanel(Vector<String> functions,
                Integer selectedIndex, Integer domainWeight, String title) {

            this.title = title;
            this.functions = new Vector<String>(functions);
            this.setLayout(new GridLayout(1, 4));
            functionCombo = new JComboBox<String>(functions);
            functionCombo.setSelectedIndex(selectedIndex);
            this.add(functionCombo);

            weight = new JSlider(0, 100, 100);
            weight.setValue(domainWeight);
            weight.setPaintLabels(false);
            this.add(weight);
            weightValue = new JLabel();

            Double dw = domainWeight / 100.0;
            weightValue.setText(dw.toString());

            weight.addChangeListener(new ChangeListener() {

                public void stateChanged(ChangeEvent e) {
                    Double dw = (weight.getValue() / 100.0);
                    weightValue.setText(dw.toString());
                }
            });
            this.add(weightValue);
            paramSpinner = new JSpinner(param = new SpinnerNumberModel());
            this.add(paramSpinner);

            functionCombo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    updateParam();
                }

            });
            updateParam();
        }

        void updateParam() {
            int sel = functionCombo.getSelectedIndex();
            if (sel == -1) {
                paramSpinner.setVisible(false);
                return;
            }
            String f = functions.elementAt(sel);
            paramSpinner.setVisible(f.endsWith("Interval")
                    || f.endsWith("Threshold"));

            if (title.equals("R") || title.equals("I")
                    || title.equals("A") || title.equals("S")
                    || title.equals("E") || title.equals("C")) {
                paramSpinner.setValue(15);
            } else if (title.equals("Entrance")) {
                paramSpinner.setValue(10);
            } else {
                paramSpinner.setValue(0);
            }

        }
        public double getWeight() {
            // here scale division is changed to 100 to accomodate more weights
            return ((double) weight.getValue()) / 100.0;
        }

        public int getParam() {
            return param.getNumber().intValue();
        }

        public String getSimilFuntion() {
            return (String) functionCombo.getSelectedItem();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
		// SimilarityDialog qf = new SimilarityDialog(null);
        // qf.setVisible(true);
        // System.out.println("Bye");
    }

}
