package com.JudgeGlass.InstallBuilder.windows;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LogWindow {
	private JFrame frame;
	private JPanel panel;
	private JTextArea txtArea;
	private JButton btnOk;
	private JButton btnUpdate;
	private JScrollPane scrollPane;
	
	public LogWindow() {
		frame = new JFrame();
		frame.setTitle("Latest Log");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(600, 480);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		
		panel = new JPanel();
		frame.add(panel);
		
		init();
	}
	
	private void init() {
		txtArea = new JTextArea();
		txtArea.setFont(new Font(Font.SANS_SERIF, 0, 12));
		txtArea.setEditable(false);
		scrollPane = new JScrollPane(txtArea);
		scrollPane.setBounds(5, 5, 580, 400);
		frame.getContentPane().add(scrollPane);
		
		btnOk = new JButton("OK");
		btnOk.setFont(new Font(Font.SANS_SERIF, 0, 12));
		btnOk.setBounds(485, 410, 100, 20);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		frame.getContentPane().add(btnOk);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font(Font.SANS_SERIF, 0, 12));
		btnUpdate.setBounds(380, 410, 100, 20);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		frame.getContentPane().add(btnUpdate);
		
		new Thread(new Runnable() {
			public void run() {
				while(true) {
					update();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		update();
	}
	
	private void update() {
		try {
			txtArea.setText(new String(Files.readAllBytes(new File("latest.log").toPath())));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setVisible(boolean vis) {
		frame.setVisible(vis);
	}
}
