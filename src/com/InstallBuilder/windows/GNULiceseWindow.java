package com.InstallBuilder.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.InstallBuilder.tools.Utils;

public class GNULiceseWindow {
	private JFrame frame;
	private JPanel panel;
	private JTextArea txtArea;
	private JButton btnOk;
	
	public GNULiceseWindow() {
		frame = new JFrame();
		frame.setTitle("License");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(600, 480);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);
		
		panel = new JPanel();
		frame.add(panel);
		
		initialize();
	}
	
	private void initialize() {
		txtArea = new JTextArea();
		txtArea.setEditable(false);
		JScrollPane sp = new JScrollPane(txtArea);
		sp.setBounds(5, 5, 580, 410);
		frame.getContentPane().add(sp);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(510, 420, 75, 20);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnOk);
		
		txtArea.append(new Utils().getLicenseFile());
		txtArea.setCaretPosition(0);
	}
	
	public void setVisible(final boolean vis) {
		frame.setVisible(vis);
	}
}
