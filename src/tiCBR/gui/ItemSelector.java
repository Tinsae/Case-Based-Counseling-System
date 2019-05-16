package tiCBR.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import jcolibri.datatypes.Instance;
import jcolibri.exception.OntologyAccessException;
import es.ucm.fdi.gaia.ontobridge.OntoBridge;
import es.ucm.fdi.gaia.ontobridge.test.gui.PnlSelectInstance;

public class ItemSelector extends JButton {

	private static final long serialVersionUID = 1L;

	private static Icon INSTANCE = new javax.swing.ImageIcon(
			PnlSelectInstance.class
			.getResource("/es/ucm/fdi/gaia/ontobridge/test/gui/instance.gif"));

	JDialog ontoDialog;
	PnlSelectInstance ontoPanel;
	String selected;
	WindowAdapter windowAction;

	public ItemSelector(JDialog parent) {

		this.setText("...");
		ontoDialog = new JDialog(parent, true);
		OntoBridge ob = jcolibri.util.OntoBridgeSingleton.getOntoBridge();
		PnlSelectInstance myinst = new PnlSelectInstance(ob);
		// myinst.setBackground(Color.red);
		ontoPanel = myinst;
		Container main = ontoDialog.getContentPane();
		main.setLayout(new BorderLayout());
		main.add(ontoPanel);

		/*
		 * windowAction = new WindowAdapter() { public void
		 * windowClosing(WindowEvent we) {
		 * ontoDialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); } };
		 */

		ontoDialog.addWindowListener(windowAction);

		// ontoDialog.setUndecorated(true);
		// ontoDialog.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		// ontoDialog.setBounds( 100, 100, 300, 200 );

		// JOptionPane.showInputDialog(ontoPanel.getSelectedInstance());

		JButton select = new JButton("Select");
		// JButton selectPrep=new JButton("Select Prep");
		// JButton selectEntr=new JButton("Select Entr");

		// String selecteditem=ontoPanel.getSelectedInstance();

		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ontoDialog.setVisible(false);

				/*
				 * String selecteditem=ontoPanel.getSelectedInstance();
				 * System.out.println("Selected field item is:" + selecteditem);
				 * if(selecteditem.endsWith("SUBJECT"))
				 * JOptionPane.showMessageDialog
				 * (null,"Select fields not subjects"); else {
				 * ontoDialog.setVisible(false); good=true; }
				 * if(selecteditem=="") good=true;
				 */
			}
		});

		/*
		 * selectPrep.addActionListener(new ActionListener(){ public void
		 * actionPerformed(ActionEvent e){ String
		 * selecteditem=ontoPanel.getSelectedInstance();
		 * 
		 * System.out.println("Selected prep item is:" + selecteditem);
		 * 
		 * if(selecteditem.endsWith("SUBJECT")==false ||
		 * selecteditem=="Aptitude" && selecteditem.isEmpty()==false)
		 * JOptionPane.showMessageDialog(null,
		 * "Please select an appropraite Preparatory Subject"); else {
		 * ontoDialog.setVisible(false); good=true; }
		 * 
		 * if(selecteditem=="") good=true; }
		 * 
		 * });
		 */
		/*
		 * selectEntr.addActionListener(new ActionListener(){ public void
		 * actionPerformed(ActionEvent e){ String
		 * selecteditem=ontoPanel.getSelectedInstance();
		 * System.out.println("Selected entr item is:" + selecteditem);
		 * 
		 * if(!(selecteditem.endsWith("SUBJECT"))&&
		 * selecteditem.isEmpty()==false || selecteditem=="AMHARIC_SUBJECT" ||
		 * selecteditem=="PHYSCIAL_EDUCATION_SUBJECT" ||
		 * selecteditem=="IT_SUBJECT" ||
		 * selecteditem=="TECHNICAL_DRAWING_SUBJECT" ||
		 * selecteditem=="BUSINESS_SUBJECT")
		 * 
		 * JOptionPane.showMessageDialog(null,"Please select Subjects not Fields"
		 * );
		 * 
		 * else { ontoDialog.setVisible(false); good=true; }
		 * 
		 * if(selecteditem=="") good=true;
		 * 
		 * } });
		 */

		/*
		 * if(type=="Field") { main.add(select,BorderLayout.SOUTH);
		 * 
		 * } else if(type=="Preparatory") {
		 * main.add(selectPrep,BorderLayout.SOUTH);
		 * 
		 * } else {
		 * 
		 * main.add(selectEntr,BorderLayout.SOUTH);
		 * 
		 * }
		 */

		main.add(select, BorderLayout.SOUTH);
		ontoDialog.pack();

		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit()
				.getScreenSize();
		ontoDialog.setBounds((screenSize.width - ontoDialog.getWidth()) / 2,
				(screenSize.height - ontoDialog.getHeight()) / 2,
				ontoDialog.getWidth(), ontoDialog.getHeight());

		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectInstance();
			}
		});
	}

	public Instance getSelectedInstance() {
		try {
			if (selected == null)
				return null;
			return new Instance(selected);
		} catch (OntologyAccessException e) {
			System.out.println("error region selector");
			org.apache.commons.logging.LogFactory.getLog(this.getClass())
			.error(e);
		}
		return null;
	}

	public void setSelectedInstance(Instance instance) {
		selected = instance.toString();
		this.setText(selected);
		this.setIcon(INSTANCE);
	}

	void selectInstance() {
		ontoDialog.setVisible(true);
		selected = ontoPanel.getSelectedInstance();

		if (selected == null) {
			this.setText("...");
			this.setIcon(null);
		} else {
			this.setText(selected);
			this.setIcon(INSTANCE);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
