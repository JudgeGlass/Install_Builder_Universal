package com.JudgeGlass.InstallBuilder.windows;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.JudgeGlass.InstallBuilder.tools.Extractor;
import com.JudgeGlass.InstallBuilder.tools.Utils;
import com.JudgeGlass.InstallBuilder.tools.Logger;

import java.io.File;
import java.io.IOException;

public class ExtractWindow {
	private JFrame frame;
	private JPanel panel;
	private JProgressBar bar;
	private JLabel lblInfo;
	
	private String zipName;

	private Logger log;

	public ExtractWindow(String zipName, Logger log) {
		this.zipName = zipName;
		this.log = log;

		frame = new JFrame();
		frame.setTitle("Extracting Files");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 80);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		
		panel = new JPanel();
		frame.add(panel);
		
		init();
	}
	
	private void init() {
		lblInfo = new JLabel("Extracting...");
		lblInfo.setBounds(5, 5, 100, 15);
		frame.getContentPane().add(lblInfo);
		
		bar = new JProgressBar();
		bar.setIndeterminate(true);
		bar.setBounds(5, 20, 280, 30);
		frame.getContentPane().add(bar);
		
		frame.setVisible(true);
		new Thread(new Runnable() {
			public void run() {
				extract();
			}
		}).start();
		
	}
	
	private void extract() {
		try {
			Thread.sleep(5000);
		}catch (Exception e){
			e.printStackTrace();
		}
		while(true) {
			File zip = new File(zipName);
			if(zip.exists()) {
				Extractor extractor;

				extractor = new Extractor();
				extractor.unZipIt(zipName, System.getProperty("user.dir"));
				zip.delete();
				try {
					File files = new File("InstallBuilder/Linux-Other/InstallBuilder.jar");
					Utils.copyFile(files, new File(System.getProperty("user.dir") + "/InstallBuilder.jar"), log);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					Process p = Runtime.getRuntime().exec("java -jar " + System.getProperty("user.dir") + "\\InstallBuilder.jar");
				} catch (IOException e) {
					e.printStackTrace();
				}
				frame.dispose();		
				System.exit(0);
			}
		}
	}
}
