package puppeteer;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class gamePathsWindow
extends JDialog
{
	public gamePathsWindow(Configgles gamePaths)
	{
		
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setBounds(100, 100, 469, 225);
		setTitle("Configure Game Paths");
		getContentPane().setLayout(null);
		
		JList list = new JList(gamePaths.readableGamePaths);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		list.setBounds(12, 67, 439, 85);
		
		JButton btnClose = new JButton("Done");
		btnClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (gamePaths.gamePaths.size() == 0)
				{
					JOptionPane.showMessageDialog(null, "At least one Creatures 3 or Docking Station game path is required to use this tool.");
				}
				dispose();
			}
		});
		btnClose.setBounds(362, 162, 89, 26);
		getContentPane().add(btnClose);
		
		JButton btnAddNewGame = new JButton("Browse Path");
		btnAddNewGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int result;
				
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Find Game Path");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				// disable the "All files" option.
				chooser.setAcceptAllFileFilterUsed(false);
				//
				if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION)
				{
					System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
					try
					{
						JOptionPane.showMessageDialog(null, gamePaths.pathStatus(chooser.getSelectedFile().toString()));
					}
					catch (HeadlessException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					catch (IOException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else
				{
					// you hit the cancel button oh noes
				}
				// refresh the display
				// this is copypasted, again, because I'm too tired to figure out
				// how to make this repeatable or fix all the warnings
				list.setModel(new AbstractListModel()
				{
					String[] values = gamePaths.readableGamePaths;
					
					public int getSize()
					{
						return values.length;
					}
					
					public Object getElementAt(int index)
					{
						return values[index];
					}
				});
			}
			
		});
		btnAddNewGame.setBounds(12, 162, 107, 26);
		getContentPane().add(btnAddNewGame);
		
		JLabel lblAddYourCreatures = new JLabel("Add your Creatures game paths here. These must be root game directories,\r\n");
		lblAddYourCreatures.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblAddYourCreatures.setBounds(12, 12, 420, 16);
		getContentPane().add(lblAddYourCreatures);
		
		JLabel lblNewLabel = new JLabel("IE Creatures 3 or Docking Station, containing both an Images and a");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel.setBounds(12, 28, 420, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Body Data folder.");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(12, 44, 420, 16);
		getContentPane().add(lblNewLabel_1);
		
		JButton btnRemovePath = new JButton("Remove Path");
		btnRemovePath.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int indexToRemove = list.getSelectedIndex();
				if (indexToRemove > -1)
				{
					gamePaths.removePath(indexToRemove);
					// refresh the display
					// this is copypasted, again, because I'm too tired to figure out
					// how to make this repeatable or fix all the warnings
					list.setModel(new AbstractListModel()
					{
						String[] values = gamePaths.readableGamePaths;
						
						public int getSize()
						{
							return values.length;
						}
						
						public Object getElementAt(int index)
						{
							return values[index];
						}
					});
				}
			}
			
		});
		btnRemovePath.setBounds(241, 162, 109, 26);
		getContentPane().add(btnRemovePath);
		
		JButton btnEnterPath = new JButton("Enter Path");
		btnEnterPath.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String inputValue = JOptionPane.showInputDialog("Paste your game path here");
				try
				{
					JOptionPane.showMessageDialog(null, gamePaths.pathStatus(inputValue));
					
				}
				catch (HeadlessException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// this seems to be the way to refresh the list and it's driving me insane
				// that I can't figure out the formatting to just make this an object itself
				// but I am tired and brainfoggy
				list.setModel(new AbstractListModel()
				{
					String[] values = gamePaths.readableGamePaths;
					
					public int getSize()
					{
						return values.length;
					}
					
					public Object getElementAt(int index)
					{
						return values[index];
					}
				});
				
			}
		});
		btnEnterPath.setBounds(131, 162, 98, 26);
		getContentPane().add(btnEnterPath);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 69, 439, 81);
		scrollPane.setViewportView(list);
		getContentPane().add(scrollPane);
		
	}
}
