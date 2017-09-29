package com.InstallBuilder.windows;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class AboutWindow {
	
	private JFrame frame;
	private JPanel panel;
	private JLabel lblAuthor;
	private JLabel lblApName;
	private JLabel lblVersion;
	private JLabel lblCopyright;
	private JLabel lblImage;
	private JTextPane txtPane;
	private JLabel lblDec;
	
	public AboutWindow() {
		frame = new JFrame();
		frame.setTitle("About");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(300, 280);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		
		panel = new JPanel();
		frame.add(panel);
		
		initialize();
		frame.setVisible(true);
	}
	
	private void initialize() {
		ImageIcon image = new ImageIcon(getClass().getResource("/com/InstallBuilder/resources/MainIcon.png"));
		lblImage = new JLabel();
		lblImage.setIcon(image);
		lblImage.setBounds(5, 5, 64, 64);
		frame.getContentPane().add(lblImage);
		
		lblApName = new JLabel("Install Builder Universal");
		lblApName.setBounds(77, 10, 300, 15);
		frame.getContentPane().add(lblApName);
		
		lblAuthor = new JLabel("Author: Hunter Wilcox");
		lblAuthor.setBounds(77, 40, 300, 15);
		frame.getContentPane().add(lblAuthor);
		
		lblVersion = new JLabel("Version: v0.0.1 [Build in Java 8]");
		lblVersion.setBounds(77, 65, 300, 15);
		frame.getContentPane().add(lblVersion);
		
		lblCopyright = new JLabel("Copyright (c) 2017 Hunter Wilcox");
		lblCopyright.setBounds(77, 90, 300, 15);
		frame.getContentPane().add(lblCopyright);
		
		lblDec = new JLabel("Description:");
		lblDec.setBounds(5, 115, 200, 15);
		frame.getContentPane().add(lblDec);
		
		txtPane = new JTextPane();
		txtPane.setEditable(false);
		txtPane.setBounds(5, 130, 280, 110);
		txtPane.setText("This is a program that makes a install wizard\nfor your application. It should work Linux, Window,\nand MaOS.");
		frame.getContentPane().add(txtPane);
	}
}
