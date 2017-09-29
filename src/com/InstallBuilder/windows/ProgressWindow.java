package com.InstallBuilder.windows;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class ProgressWindow {
	public JFrame frame;
	private JPanel panel;
	private JProgressBar proBar;
	private JLabel lblInfo;
	
	public ProgressWindow() {
		frame = new JFrame();
		frame.setTitle("Making...");
		frame.setSize(360, 120);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);
		
		panel = new JPanel();
		frame.add(panel);
		
		initialize();
		frame.setVisible(true);
	}
	
	
	
	private void initialize() {
		lblInfo = new JLabel("Making...");
		lblInfo.setBounds(5, 5, 60, 15);
		frame.getContentPane().add(lblInfo);
		
		proBar = new JProgressBar();
		proBar.setBounds(5, 25, 345, 30);
		proBar.setIndeterminate(true);
		frame.getContentPane().add(proBar);
	}
	
	
}
