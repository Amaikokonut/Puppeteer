package puppeteer;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSpinner;
import javax.swing.JTree;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JDesktopPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;

// Does me editing work? :D —PP
public class Puppeteer
{
	
	private JFrame frmPuppeteer;
	
	// generate the initial default creature:
	PosedCreature creature = new PosedCreature(0, 1, 'd', 0, 0, 1, 0, 0);
	// and the initial selected part for that matter
	int selectedPart = 0;
	// load the game paths from the cfg file
	
	
	// define all the UI bits now so we can access them later
	JComboBox comboSlot;
	JRadioButton rdbtnM;
	JRadioButton rdbtnF;
	JComboBox comboAge;
	JSpinner spinMainPose;
	JComboBox comboMainDirn;
	JComboBox comboExpression;
	JCheckBox chckbxEyesClosed;
	JComboBox comboPartSelector = new JComboBox(CreatureInfo.bodyParts);
	JComboBox comboPartSpecies = new JComboBox(CreatureInfo.availableSpecies);
	JComboBox comboPartSlot = new JComboBox(CreatureInfo.availableSlots);
	JSpinner spinPartPose = new JSpinner();
	JComboBox comboPartDirn = new JComboBox(CreatureInfo.dirn);
	JSpinner spinXoffset = new JSpinner();
	JSpinner spinYoffset = new JSpinner();
	
	// a method to properly update body part UI bits
	public void updatePartsUI()
	{
		comboPartSpecies.setSelectedIndex(creature.part[selectedPart].spcs);
		//convert char to index
		char y = 'a';
		char z = creature.part[selectedPart].slot;
		
		comboPartSlot.setSelectedIndex(z - y);
		comboPartDirn.setSelectedIndex(creature.part[selectedPart].dirn);
		spinPartPose.setValue(creature.part[selectedPart].pose);
		spinXoffset.setValue(creature.part[selectedPart].x);
		spinYoffset.setValue(creature.part[selectedPart].y);
	}
	
	private final ButtonGroup buttonGroupMF = new ButtonGroup();
	
	//
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Puppeteer window = new Puppeteer();
					window.frmPuppeteer.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public Puppeteer()
	{
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmPuppeteer = new JFrame();
		frmPuppeteer.setTitle("Puppeteer");
		frmPuppeteer.setBounds(100, 100, 800, 600);
		frmPuppeteer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPuppeteer.getContentPane().setLayout(new GridLayout(2, 3, 0, 0));
		
		JPanel panelMain = new JPanel();
		panelMain.setBorder(null);
		frmPuppeteer.getContentPane().add(panelMain);
		panelMain.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblMainSettings = new JLabel("Main Settings");
		lblMainSettings.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelMain.add(lblMainSettings);
		
		JPanel pnlSpcsSlot = new JPanel();
		panelMain.add(pnlSpcsSlot);
		
		JLabel lblSpecies = new JLabel("Species:");
		pnlSpcsSlot.add(lblSpecies);
		
		JComboBox comboSpcs = new JComboBox(CreatureInfo.availableSpecies);
		comboSpcs.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				creature.UpdateSpcs(comboSpcs.getSelectedIndex());
			}
		});
		pnlSpcsSlot.add(comboSpcs);
		
		JLabel lblSlot = new JLabel("Slot:");
		pnlSpcsSlot.add(lblSlot);
		
		JComboBox comboSlot = new JComboBox(CreatureInfo.availableSlots);
		comboSlot.setSelectedIndex(3);
		comboSlot.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				char x = 'a';
				x += comboSlot.getSelectedIndex();
				creature.UpdateSlot(x);
			}
		});
		pnlSpcsSlot.add(comboSlot);
		
		JPanel pnlMaleFemale = new JPanel();
		panelMain.add(pnlMaleFemale);
		
		JRadioButton rdbtnM = new JRadioButton("M");
		rdbtnM.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				creature.UpdateMF(0);
			}
		});
		buttonGroupMF.add(rdbtnM);
		pnlMaleFemale.add(rdbtnM);
		
		JRadioButton rdbtnF = new JRadioButton("F");
		rdbtnF.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				creature.UpdateMF(1);
			}
		});
		rdbtnF.setSelected(true);
		buttonGroupMF.add(rdbtnF);
		pnlMaleFemale.add(rdbtnF);
		
		JPanel pnlAge = new JPanel();
		panelMain.add(pnlAge);
		
		JLabel lblAge = new JLabel("Age:");
		pnlAge.add(lblAge);
		
		JComboBox comboAge = new JComboBox(CreatureInfo.lifeStages);
		pnlAge.add(comboAge);
		
		JPanel pnlMainPose = new JPanel();
		panelMain.add(pnlMainPose);
		
		JLabel lblPose = new JLabel("Pose");
		pnlMainPose.add(lblPose);
		
		JSpinner spinMainPose = new JSpinner();
		spinMainPose.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent arg0)
			{
				// According to The Internet 'you should also call spinner.commitEdit()
				// prior to calling getValue() to ensure manually typed values with the
				// editor are propagated to the model, otherwise you will only get the old value.'
				try
				{
					spinMainPose.commitEdit();
				}
				catch (java.text.ParseException e)
				{
					// do something here maybe
				}
				creature.UpdatePose((Integer) spinMainPose.getValue());
			}
		});
		pnlMainPose.add(spinMainPose);
		spinMainPose.setModel(new SpinnerNumberModel(0, 0, 32, 1));
		
		JPanel pnlMainDirn = new JPanel();
		panelMain.add(pnlMainDirn);
		
		JLabel lblDirection = new JLabel("Direction:");
		pnlMainDirn.add(lblDirection);
		
		JComboBox comboMainDirn = new JComboBox(CreatureInfo.dirn);
		comboMainDirn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				creature.UpdateDirn(comboMainDirn.getSelectedIndex());
			}
		});
		comboMainDirn.setSelectedIndex(1);
		pnlMainDirn.add(comboMainDirn);
		
		JPanel pnlExpression = new JPanel();
		panelMain.add(pnlExpression);
		
		JLabel lblExpression = new JLabel("Expression:");
		pnlExpression.add(lblExpression);
		
		JComboBox comboExpression = new JComboBox(CreatureInfo.expressions);
		comboExpression.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				creature.UpdateExpression(comboExpression.getSelectedIndex());
			}
		});
		pnlExpression.add(comboExpression);
		
		JPanel pnlEyes = new JPanel();
		panelMain.add(pnlEyes);
		
		JCheckBox chckbxEyesClosed = new JCheckBox("Eyes Closed");
		chckbxEyesClosed.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (chckbxEyesClosed.isSelected())
				{
					creature.UpdateEyes(1);
				}
				else
				{
					creature.UpdateEyes(0);
				}
			}
		});
		pnlEyes.add(chckbxEyesClosed);
		
		JPanel panelCreatureDisplay = new JPanel();
		panelCreatureDisplay.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frmPuppeteer.getContentPane().add(panelCreatureDisplay);
		
		JLabel labelDisplayPlaceholder = new JLabel("Norn Goes Here");
		panelCreatureDisplay.add(labelDisplayPlaceholder);
		
		JPanel panelATT = new JPanel();
		frmPuppeteer.getContentPane().add(panelATT);
		
		JLabel lblATT = new JLabel("ATT Info Goes Here");
		panelATT.add(lblATT);
		
		JPanel panelParts = new JPanel();
		frmPuppeteer.getContentPane().add(panelParts);
		panelParts.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblIndividualBodyParts = new JLabel("Individual Body Part Settings");
		lblIndividualBodyParts.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIndividualBodyParts.setHorizontalAlignment(SwingConstants.CENTER);
		panelParts.add(lblIndividualBodyParts);
		
		JSeparator separator = new JSeparator();
		panelParts.add(separator);
		
		comboPartSelector.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				selectedPart = comboPartSelector.getSelectedIndex();
				updatePartsUI();
				
			}
		});
		panelParts.add(comboPartSelector);
		
		JPanel panelPartSpcsSlot = new JPanel();
		panelParts.add(panelPartSpcsSlot);
		
		JLabel labelPartSpcs = new JLabel("Species:");
		panelPartSpcsSlot.add(labelPartSpcs);
		
		comboPartSpecies.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				creature.part[selectedPart].UpdateSpcs(comboPartSpecies.getSelectedIndex());
			}
		});
		panelPartSpcsSlot.add(comboPartSpecies);
		
		JLabel labelPartSlot = new JLabel("Slot:");
		panelPartSpcsSlot.add(labelPartSlot);
		comboPartSlot.setSelectedIndex(3);
		
		comboPartSlot.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				char x = 'a';
				x += comboPartSlot.getSelectedIndex();
				creature.part[selectedPart].UpdateSlot(x);
			}
		});
		panelPartSpcsSlot.add(comboPartSlot);
		
		JPanel pnlPartPose = new JPanel();
		panelParts.add(pnlPartPose);
		
		JLabel lblPose_1 = new JLabel("Pose");
		pnlPartPose.add(lblPose_1);
		
		spinPartPose.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent arg0)
			{
				// According to The Internet 'you should also call spinner.commitEdit()
				// prior to calling getValue() to ensure manually typed values with the
				// editor are propagated to the model, otherwise you will only get the old value.'
				try
				{
					spinPartPose.commitEdit();
				}
				catch (java.text.ParseException e)
				{
					// do something here maybe
				}
				creature.part[selectedPart].UpdatePose((Integer) spinPartPose.getValue());
			}
		});
		spinPartPose.setModel(new SpinnerNumberModel(0, 0, 32, 1));
		pnlPartPose.add(spinPartPose);
		
		JPanel pnlPartDirn = new JPanel();
		panelParts.add(pnlPartDirn);
		
		JLabel label_1 = new JLabel("Direction");
		pnlPartDirn.add(label_1);
		
		comboPartDirn.setSelectedIndex(1);
		comboPartDirn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				creature.part[selectedPart].UpdateDirn(comboPartDirn.getSelectedIndex());
			}
		});
		pnlPartDirn.add(comboPartDirn);
		
		JPanel pnlPartOffsets = new JPanel();
		panelParts.add(pnlPartOffsets);
		
		JLabel lblXoffset = new JLabel("X-offset");
		pnlPartOffsets.add(lblXoffset);
		
		spinXoffset.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				try
				{
					spinXoffset.commitEdit();
				}
				catch (java.text.ParseException f)
				{
					// do something here maybe
				}
				creature.part[selectedPart].UpdateX((Integer) spinXoffset.getValue());
				
			}
		});
		spinXoffset.setModel(new SpinnerNumberModel(0, -255, 255, 1));
		pnlPartOffsets.add(spinXoffset);
		
		JSeparator separator_1 = new JSeparator();
		pnlPartOffsets.add(separator_1);
		
		JLabel lblYoffset = new JLabel("Y-offset");
		pnlPartOffsets.add(lblYoffset);
		
		spinYoffset.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent e)
			{
				try
				{
					spinYoffset.commitEdit();
				}
				catch (java.text.ParseException f)
				{
					// do something here maybe
				}
				creature.part[selectedPart].UpdateY((Integer) spinYoffset.getValue());
			}
		});
		spinYoffset.setModel(new SpinnerNumberModel(0, -255, 255, 1));
		pnlPartOffsets.add(spinYoffset);
		
		JPanel panelExperimental = new JPanel();
		frmPuppeteer.getContentPane().add(panelExperimental);
		
		JLabel lblExperimentalControls = new JLabel("Experimental Controls");
		lblExperimentalControls.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelExperimental.add(lblExperimentalControls);
		
		JButton btnRandomizeParts = new JButton("Randomize Parts");
		panelExperimental.add(btnRandomizeParts);
		
		JButton btnRandomizePose = new JButton("Randomize Pose");
		panelExperimental.add(btnRandomizePose);
		
		JLabel lblTestHybrids = new JLabel("Test Hybrid Creatures:");
		panelExperimental.add(lblTestHybrids);
		
		JPanel pnlBreedTestMom = new JPanel();
		panelExperimental.add(pnlBreedTestMom);
		
		JLabel lblBreed = new JLabel("Breed:");
		pnlBreedTestMom.add(lblBreed);
		
		JComboBox comboBreedTestMom = new JComboBox();
		pnlBreedTestMom.add(comboBreedTestMom);
		comboBreedTestMom.setModel(new DefaultComboBoxModel(new String[]
		{
				"Displayed Creature", "Random Creatures", "Main Settings Creature"
		}));
		
		JPanel pnlBreedTestDad = new JPanel();
		panelExperimental.add(pnlBreedTestDad);
		
		JLabel lblWith = new JLabel("with:");
		pnlBreedTestDad.add(lblWith);
		
		JComboBox comboBreedTestDad = new JComboBox();
		pnlBreedTestDad.add(comboBreedTestDad);
		comboBreedTestDad.setModel(new DefaultComboBoxModel(new String[]
		{
				"Random Creature"
		}));
		
		JButton btnGo = new JButton("Go!");
		panelExperimental.add(btnGo);
		
		JButton btnPrintDebugInfo = new JButton("Print Debug Info");
		btnPrintDebugInfo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				creature.DbgOuts();
			}
		});
		panelExperimental.add(btnPrintDebugInfo);
		
		JPanel PanelATT2 = new JPanel();
		frmPuppeteer.getContentPane().add(PanelATT2);
		
		JMenuBar menuBar = new JMenuBar();
		frmPuppeteer.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSetGameDirectories = new JMenuItem("Set Game Paths");
		mntmSetGameDirectories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gamePathsWindow gamePathsDialog = new gamePathsWindow();
				gamePathsDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				gamePathsDialog.setLocationRelativeTo(frmPuppeteer);
				gamePathsDialog.setVisible(true);
				
			}
		});
		mnFile.add(mntmSetGameDirectories);
		
	}
}
