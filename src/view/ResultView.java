package view;

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

import org.json.JSONException;

import controller.ControlSystem;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ResultView {
	private JFrame frame;
	private ControlSystem controller;
	private String fileName = null;
	private JTextArea textArea ;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ResultView window = new ResultView();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public ResultView(ControlSystem controller) {
		this.controller = controller;
		initialize();
		this.frame.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 564, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnReadFile = new JButton("Read File");
		btnReadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileDialog fd = new FileDialog(frame, "Choose a file", FileDialog.LOAD);
				fd.setDirectory("C:\\");
//				fd.setFile("*.xml");
				fd.setVisible(true);
				fileName = fd.getDirectory() + fd.getFile();
//				System.out.println("fileName " + fileName);
//				String filename = fd.getFile();
				if (fileName == null)
				  System.out.println("You cancelled the choice");
				else
					try {
						controller.getDataModel(fileName);
						textArea.setText("Read Input File Success");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}
		});
		btnReadFile.setBounds(22, 35, 89, 23);
		frame.getContentPane().add(btnReadFile);
		
		JButton btnAnalysis = new JButton("Analysis");
		btnAnalysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.Analysis();
				controller.setResult(textArea);
			}
		});
		btnAnalysis.setBounds(22, 84, 89, 23);
		frame.getContentPane().add(btnAnalysis);
		
		JButton btnSaveFile = new JButton("Save File");
		btnSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a file to save");   
				 
				int userSelection = fileChooser.showSaveDialog(frame);
				 
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File fileToSave = fileChooser.getSelectedFile();
//				    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
				    try {
						controller.getSaveFileName(fileToSave.getAbsolutePath());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Write File Success");
				}

			}
		});
		btnSaveFile.setBounds(22, 136, 89, 23);
		frame.getContentPane().add(btnSaveFile);
		
		textArea = new JTextArea();
		textArea.setBounds(143, 0, 405, 546);
		frame.getContentPane().add(textArea);
	}
}
