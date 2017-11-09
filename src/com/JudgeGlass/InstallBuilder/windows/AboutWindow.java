package com.JudgeGlass.InstallBuilder.windows;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import com.JudgeGlass.InstallBuilder.ApplicationInfo;
import com.JudgeGlass.InstallBuilder.Main;

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
	private JLabel lblJava;
	private JButton btnOk;
	private JButton btnLicense;
	
	public AboutWindow() {
		frame = new JFrame();
		frame.setTitle("About");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(325, 280);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		
		panel = new JPanel();
		frame.add(panel);
		
		initialize();
		frame.setVisible(true);
	}
	
	private void initialize() {
		ImageIcon image = new ImageIcon(getClass().getResource("/com/JudgeGlass/InstallBuilder/resources/MainIcon.png"));
		lblImage = new JLabel();
		lblImage.setIcon(image);
		lblImage.setBounds(5, 5, 64, 64);
		frame.getContentPane().add(lblImage);
		
		lblApName = new JLabel("Install Builder Universal");
		lblApName.setFont(new Font(Font.SANS_SERIF, 0, 12));
		lblApName.setBounds(77, 10, 170, 15);
		lblApName.setForeground(Color.BLUE);
		lblApName.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://github.com/JudgeGlass/Install_Builder_Universal"));
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error:\n" + e1);
					e1.printStackTrace();
				} 
			}
		});
		lblApName.setCursor(new Cursor(Cursor.HAND_CURSOR));
		frame.getContentPane().add(lblApName);
		
		lblAuthor = new JLabel("Author: Hunter Wilcox");
		lblAuthor.setFont(new Font(Font.SANS_SERIF, 0, 12));
		lblAuthor.setBounds(77, 35, 300, 15);
		frame.getContentPane().add(lblAuthor);
		
		lblVersion = new JLabel("Version: " + ApplicationInfo.VERSION + " [Build Date: " + ApplicationInfo.BUILD_DATE + "]");
		lblVersion.setFont(new Font(Font.SANS_SERIF, 0, 12));
		lblVersion.setBounds(77, 60, 300, 15);
		frame.getContentPane().add(lblVersion);
		
		lblCopyright = new JLabel("Copyright (c) 2017 Hunter Wilcox");
		lblCopyright.setFont(new Font(Font.SANS_SERIF, 0, 12));
		lblCopyright.setBounds(77, 85, 300, 15);
		frame.getContentPane().add(lblCopyright);
		
		lblJava = new JLabel("Java Version: " + System.getProperty("java.version"));
		lblJava.setFont(new Font(Font.SANS_SERIF, 0, 12));
		lblJava.setBounds(77, 108, 300, 15);
		frame.getContentPane().add(lblJava);
		
		lblDec = new JLabel("Description:");
		lblDec.setFont(new Font(Font.SANS_SERIF, 0, 12));
		lblDec.setBounds(5, 115, 200, 15);
		frame.getContentPane().add(lblDec);
		
		txtPane = new JTextPane();
		txtPane.setFont(new Font(Font.SANS_SERIF, 0, 12));
		txtPane.setEditable(false);
		txtPane.setBounds(5, 130, 295, 90);
		txtPane.setText("This is a program that makes a install wizard\nfor your application. It should work Linux, Window, and MacOS.");
		frame.getContentPane().add(txtPane);
		
		btnOk = new JButton("OK");
		btnOk.setFont(new Font(Font.SANS_SERIF, 0, 12));
		btnOk.setBounds(210, 225, 95, 20);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnOk);
		
		btnLicense = new JButton("License");
		btnLicense.setFont(new Font(Font.SANS_SERIF, 0, 12));
		btnLicense.setBounds(125, 225, 80, 20);
		btnLicense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GNULicenseWindow().setVisible(true);
			}
		});
		frame.getContentPane().add(btnLicense);
	}
}



