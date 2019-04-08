package puppeteer;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import puppeteer.SpriteCollectionComponent.DisplayedSprite;
import javax.swing.JScrollPane;
import puppeteer.trimBlankPixels;

public class Puppeteer
{
	
	private JFrame frmPuppeteer;
	// generate the initial default creature:
	PosedCreature creature = new PosedCreature(0, 1, 'd', 0, 0, 1, 0, 0);
	CreatureGenerator theCreatureGenerator = new CreatureGenerator();
	CreatureInfo creatureInfo = new CreatureInfo();
	// and the initial selected part for that matter
	int selectedPart = 0;
	
	// define all the UI bits now so we can access them later
	JComboBox<String> comboSlot;
	JRadioButton rdbtnM;
	JRadioButton rdbtnF;
	JComboBox<String> comboAge;
	JSpinner spinMainPose;
	JComboBox<String> comboMainDirn;
	JComboBox<String> comboExpression;
	JCheckBox chckbxEyesClosed;
	JComboBox comboPartSelector = new JComboBox(creatureInfo.bodyParts);
	JComboBox<String> comboPartSpecies = new JComboBox(creatureInfo.availableSpecies);
	JComboBox<String> comboPartSlot = new JComboBox(creatureInfo.availableSlots);
	JSpinner spinPartPose = new JSpinner();
	JComboBox<String> comboPartDirn = new JComboBox(creatureInfo.dirn);
	JSpinner spinXoffset = new JSpinner();
	JSpinner spinYoffset = new JSpinner();
	JTextArea txtrAttInfo = new JTextArea();
	// Default image for now~
	List<DisplayedSprite> sprites = new ArrayList<>(14);
	SpriteCollectionComponent displayCreature = new SpriteCollectionComponent(sprites);
	
	// a method to properly update body part UI bits
	public void updatePartsUI()
	{
		comboPartSpecies.setSelectedIndex(creature.part[selectedPart].spcs);
		// convert char to index
		char y = 'a';
		char z = creature.part[selectedPart].slot;
		
		comboPartSlot.setSelectedIndex(z - y);
		comboPartDirn.setSelectedIndex(creature.part[selectedPart].dirn);
		spinPartPose.setValue(creature.part[selectedPart].pose);
		spinXoffset.setValue(creature.part[selectedPart].x);
		spinYoffset.setValue(creature.part[selectedPart].y);
	}
	
	public void updateAttInfo(String text)
	{
		txtrAttInfo.setText(text);
		txtrAttInfo.validate();
	}
	
	public void updateSprite(List<DisplayedSprite> unlayeredSprites)
	{
		sprites = unlayeredSprites;
		displayCreature.setSpritesAndRepaint(theCreatureGenerator.layerSpritesByDirn(creature.dirn, unlayeredSprites));
	}
	
	public void redrawDefaultCreature()
	{
		creature = new PosedCreature(0, 1, 'd', 0, 0, 1, 0, 0);
		updateSprite(theCreatureGenerator.getUnlayeredSpritesFromCreature(creature));
		
	}
	
	private final ButtonGroup buttonGroupMF = new ButtonGroup();
	
	//
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		// load the game paths from the cfg file
		Configgles.loadPathsFromFile();
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
		
		JComboBox<String> comboSpcs = new JComboBox(creatureInfo.availableSpecies);
		comboSpcs.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				creature.UpdateSpcs(comboSpcs.getSelectedIndex());
				updatePartsUI();
			}
		});
		pnlSpcsSlot.add(comboSpcs);
		
		JLabel lblSlot = new JLabel("Slot:");
		pnlSpcsSlot.add(lblSlot);
		
		JComboBox<String> comboSlot = new JComboBox(creatureInfo.availableSlots);
		comboSlot.setSelectedIndex(3);
		comboSlot.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				char x = 'a';
				x += comboSlot.getSelectedIndex();
				creature.UpdateSlot(x);
				updateSprite(theCreatureGenerator.getUnlayeredSpritesFromCreature(creature));
				updatePartsUI();
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
				updateSprite(theCreatureGenerator.getUnlayeredSpritesFromCreature(creature));
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
				updateSprite(theCreatureGenerator.getUnlayeredSpritesFromCreature(creature));
			}
		});
		rdbtnF.setSelected(true);
		buttonGroupMF.add(rdbtnF);
		pnlMaleFemale.add(rdbtnF);
		
		JPanel pnlAge = new JPanel();
		panelMain.add(pnlAge);
		
		JLabel lblAge = new JLabel("Age:");
		pnlAge.add(lblAge);
		
		JComboBox<String> comboAge = new JComboBox(creatureInfo.lifeStages);
		comboAge.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				creature.UpdateAge(comboAge.getSelectedIndex());
				updateSprite(theCreatureGenerator.getUnlayeredSpritesFromCreature(creature));
				updatePartsUI();
			}
		});
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
				updateSprite(theCreatureGenerator.getUnlayeredSpritesFromCreature(creature));
				updatePartsUI();
			}
		});
		pnlMainPose.add(spinMainPose);
		spinMainPose.setModel(new SpinnerNumberModel(0, 0, 3, 1));
		
		JPanel pnlMainDirn = new JPanel();
		panelMain.add(pnlMainDirn);
		
		JLabel lblDirection = new JLabel("Direction:");
		pnlMainDirn.add(lblDirection);
		
		JComboBox<String> comboMainDirn = new JComboBox(creatureInfo.dirn);
		comboMainDirn.setSelectedIndex(1);
		comboMainDirn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				creature.UpdateDirn(comboMainDirn.getSelectedIndex());
				updateSprite(theCreatureGenerator.getUnlayeredSpritesFromCreature(creature));
				updatePartsUI();
			}
		});
		
		pnlMainDirn.add(comboMainDirn);
		
		JPanel pnlExpression = new JPanel();
		panelMain.add(pnlExpression);
		
		JLabel lblExpression = new JLabel("Expression:");
		pnlExpression.add(lblExpression);
		
		JComboBox<String> comboExpression = new JComboBox(creatureInfo.expressions);
		comboExpression.setSelectedIndex(1);
		comboExpression.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				creature.UpdateExpression(comboExpression.getSelectedIndex());
				updateSprite(theCreatureGenerator.getUnlayeredSpritesFromCreature(creature));
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
				updateSprite(theCreatureGenerator.getUnlayeredSpritesFromCreature(creature));
			}
		});
		pnlEyes.add(chckbxEyesClosed);
		
		// JPanel panelCreatureDisplay = new JPanel();
		frmPuppeteer.getContentPane().add(displayCreature);
		BufferedImage default404Sprite = null;
		
		try
		{
			default404Sprite = ImageIO.read(Puppeteer.class.getResource("norn.png"));
		}
		catch (IOException e2)
		{
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		DisplayedSprite defaultSprite = new DisplayedSprite(default404Sprite, 0, 0);
		sprites.add(defaultSprite);
		
		JPanel panelATT = new JPanel();
		frmPuppeteer.getContentPane().add(panelATT);
		panelATT.setLayout(null);
		txtrAttInfo.setLineWrap(true);
		
		txtrAttInfo.setBackground(Color.WHITE);
		// txtrAttInfo.setMaximumSize(new Dimension(30, 30));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 261, 269);
		panelATT.add(scrollPane);
		scrollPane.setViewportView(txtrAttInfo);
		// txtrAttInfo.setBounds(0, 0, 261, 269);
		
		// scrollPane.add(txtrAttInfo);
		
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
				if (comboPartSelector.hasFocus())
				{
					selectedPart = comboPartSelector.getSelectedIndex();
					updatePartsUI();
				}
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
				if (comboPartSpecies.hasFocus())
				{
					creature.part[selectedPart].UpdateSpcs(comboPartSpecies.getSelectedIndex());
					updateSprite(theCreatureGenerator.UpdateAndDisplayPart(selectedPart, creature, sprites));
				}
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
				if (comboPartSlot.hasFocus())
				{
					char x = 'a';
					x += comboPartSlot.getSelectedIndex();
					creature.part[selectedPart].UpdateSlot(x);
					updateSprite(theCreatureGenerator.UpdateAndDisplayPart(selectedPart, creature, sprites));
				}
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
				// if (spinPartPose.hasFocus()) {
				creature.part[selectedPart].UpdatePose((Integer) spinPartPose.getValue());
				updateSprite(theCreatureGenerator.UpdateAndDisplayPart(selectedPart, creature, sprites));
				// }
			}
		});
		spinPartPose.setModel(new SpinnerNumberModel(0, 0, 3, 1));
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
				if (comboPartDirn.hasFocus())
				{
					creature.part[selectedPart].UpdateDirn(comboPartDirn.getSelectedIndex());
					updateSprite(theCreatureGenerator.UpdateAndDisplayPart(selectedPart, creature, sprites));
				}
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
				updateSprite(theCreatureGenerator.UpdateAndDisplayPart(selectedPart, creature, sprites));
				
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
				updateSprite(theCreatureGenerator.UpdateAndDisplayPart(selectedPart, creature, sprites));
				
			}
		});
		spinYoffset.setModel(new SpinnerNumberModel(0, -255, 255, 1));
		pnlPartOffsets.add(spinYoffset);
		
		JLabel lblGeneticPoseCombos = new JLabel("Genetic Pose Combos");
		lblGeneticPoseCombos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelParts.add(lblGeneticPoseCombos);
		
		JComboBox<String> comboBoxGeneticPose = new JComboBox(CreaturePoseLibrary.poseList.toArray());
		comboBoxGeneticPose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreaturePoseLibrary.setCreaturePose(comboBoxGeneticPose.getSelectedIndex(),creature);
				updateSprite(theCreatureGenerator.getUnlayeredSpritesFromCreature(creature));
				updatePartsUI();
			}
		});
		panelParts.add(comboBoxGeneticPose);
		
		JPanel panelExperimental = new JPanel();
		frmPuppeteer.getContentPane().add(panelExperimental);
		
		JLabel lblExperimentalControls = new JLabel("Experimental Controls");
		lblExperimentalControls.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelExperimental.add(lblExperimentalControls);
		
		JButton btnRandomizeParts = new JButton("Randomize Parts");
		btnRandomizeParts.setEnabled(false);
		panelExperimental.add(btnRandomizeParts);
		
		JButton btnRandomizePose = new JButton("Randomize Pose");
		btnRandomizePose.setEnabled(false);
		panelExperimental.add(btnRandomizePose);
		
		JLabel lblTestHybrids = new JLabel("Test Hybrid Creatures:");
		panelExperimental.add(lblTestHybrids);
		
		JPanel pnlBreedTestMom = new JPanel();
		panelExperimental.add(pnlBreedTestMom);
		
		JLabel lblBreed = new JLabel("Breed:");
		pnlBreedTestMom.add(lblBreed);
		
		JComboBox<String> comboBreedTestMom = new JComboBox<String>();
		comboBreedTestMom.setEnabled(false);
		pnlBreedTestMom.add(comboBreedTestMom);
		comboBreedTestMom.setModel(new DefaultComboBoxModel<String>(new String[]
		{
				"Displayed Creature", "Random Creatures", "Main Settings Creature"
		}));
		
		JPanel pnlBreedTestDad = new JPanel();
		panelExperimental.add(pnlBreedTestDad);
		
		JLabel lblWith = new JLabel("with:");
		pnlBreedTestDad.add(lblWith);
		
		JComboBox<String> comboBreedTestDad = new JComboBox<String>();
		comboBreedTestDad.setEnabled(false);
		pnlBreedTestDad.add(comboBreedTestDad);
		comboBreedTestDad.setModel(new DefaultComboBoxModel<String>(new String[]
		{
				"Random Creature"
		}));
		
		JButton btnGo = new JButton("Go!");
		btnGo.setEnabled(false);
		btnGo.addActionListener(new ActionListener()
		{
			// this is a very temporary testing-only action
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					testTester.main();
				}
				catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panelExperimental.add(btnGo);
		
		JButton btnPrintDebugInfo = new JButton("Print Debug Info");
		btnPrintDebugInfo.setEnabled(false);
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
		
		JButton btnGetAttInfo = new JButton("Generate Pose Details");
		btnGetAttInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAttInfo(theCreatureGenerator.FileInfoToReadableString(creature));
			}
		});
		PanelATT2.add(btnGetAttInfo);
		
		JMenuBar menuBar = new JMenuBar();
		frmPuppeteer.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSetGameDirectories = new JMenuItem("Set Game Paths");
		mntmSetGameDirectories.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				gamePathsWindow gamePathsDialog = new gamePathsWindow();
				gamePathsDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				gamePathsDialog.setLocationRelativeTo(frmPuppeteer);
				gamePathsDialog.setVisible(true);
				
//the listener that regenerates the creature once the window is closed
				gamePathsDialog.addWindowListener(new WindowAdapter() 
				{
				  public void windowClosed(WindowEvent e)
				  {
					  redrawDefaultCreature();
				  }

				  public void windowClosing(WindowEvent e)
				  {
					  redrawDefaultCreature();
				  }
				});
				
			}
		});
		
		mnFile.add(mntmSetGameDirectories);
		
		JMenuItem mntmSaveImage = new JMenuItem("Save image");
		mntmSaveImage.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// pull up the save dialog box
				JFileChooser jfc = new JFileChooser();
				int retVal = jfc.showSaveDialog(null);
				if (retVal == JFileChooser.APPROVE_OPTION)
				{
					
					displayCreature.setBackground(new Color(0, 0, 0, 0));
					BufferedImage img = new BufferedImage(displayCreature.getWidth(), displayCreature.getHeight(), BufferedImage.TYPE_INT_ARGB);
					Graphics g = img.createGraphics();
					displayCreature.paint(g);
					g.dispose();
					BufferedImage imgCropped = trimBlankPixels.TrimImage(img);
					
					File file = jfc.getSelectedFile();
					//we have to force a png extension, sigh
					String ext = "";
					if(file.getName().contains("."))
					{
					    String parts[] = file.getName().split("\\.");
					    ext = parts[parts.length - 1];
					}
					
					if (ext.equalsIgnoreCase("png"))
					{
						// filename is OK as-is
						
					}
					else
					{
						file = new File(file.toString() + ".png"); // append .xml if "foo.jpg.xml" is OK
					}
					
					try
					{
						ImageIO.write(imgCropped, "png", file);
					}
					catch (IOException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		mnFile.add(mntmSaveImage);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//replace with something more externalized eventually
				JOptionPane.showMessageDialog(null, "Puppeteer \n\n"
				+ "Developed (in large part) by Amaikokonut (amaikokonut@songua.com)\n\n"
				+ "with many, many contributions (including the Jagent libraries, the name, a \n"
				+ "smattering of useful functions, a bunch of impromptu crash course Java lessons,\n"
				+ "and an endless amount of moral support) by RProgrammer (aka PuppyPi)\n\n"
				+ "Test/Demo version 1.0, compiled April 2, 2019");
				
			}
		});
		mnHelp.add(mntmAbout);
		
		if (Configgles.gamePaths.size() == 0)
		{
			mntmSetGameDirectories.doClick();
			
		}
		else
		{
			updateSprite(theCreatureGenerator.getUnlayeredSpritesFromCreature(creature));
		}
	}
}
