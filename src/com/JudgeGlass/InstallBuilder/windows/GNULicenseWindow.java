package com.JudgeGlass.InstallBuilder.windows;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.JudgeGlass.InstallBuilder.tools.Utils;

public class GNULicenseWindow {
	private JFrame frame;
	private JPanel panel;
	private JTextArea txtArea;
	private JButton btnOk;
	
	public GNULicenseWindow() {
		frame = new JFrame();
		frame.setTitle("License");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(615, 460);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);
		
		panel = new JPanel();
		frame.add(panel);
		
		initialize();
	}
	
	private void initialize() {
		txtArea = new JTextArea();
		txtArea.setFont(new Font(Font.SANS_SERIF, 0, 12));
		txtArea.setEditable(false);
		JScrollPane sp = new JScrollPane(txtArea);
		sp.setBounds(5, 5, 600, 410);
		frame.getContentPane().add(sp);
		
		btnOk = new JButton("OK");
		btnOk.setFont(new Font(Font.SANS_SERIF, 0, 12));
		btnOk.setBounds(510, 420, 95, 20);
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
