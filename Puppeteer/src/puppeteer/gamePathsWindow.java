package puppeteer;

import java.awt.EventQueue;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.JTextPane;

public class gamePathsWindow
extends JDialog
{
	public gamePathsWindow()
	{
		setAlwaysOnTop(true);
		setResizable(false);
		setBounds(100, 100, 469, 225);
		setTitle("Configure Game Paths");
		getContentPane().setLayout(null);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(362, 162, 89, 26);
		getContentPane().add(btnClose);
		
		JButton btnAddNewGame = new JButton("Browse Path");
		btnAddNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddNewGame.setBounds(12, 162, 107, 26);
		getContentPane().add(btnAddNewGame);
		
		JList list = new JList();
		list.setBounds(12, 67, 439, 85);
		getContentPane().add(list);
		
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
		btnRemovePath.setBounds(241, 162, 109, 26);
		getContentPane().add(btnRemovePath);
		
		JButton btnEnterPath = new JButton("Enter Path");
		btnEnterPath.setBounds(131, 162, 98, 26);
		getContentPane().add(btnEnterPath);
		
	}
}
