package com.InstallBuilder;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.InstallBuilder.windows.InstallBuilderWindow;

public class Main {
	public static final String version = "v0.0.2.2";
	
	public static void main(String args[]) {
		System.out.println("OS Name: " + System.getProperty("os.name"));
		System.out.println("OS Type: " + System.getProperty("os.arch"));
		System.out.println("Java Installation Path: " + System.getProperty("java.home"));
		System.out.println("Java Version: " + System.getProperty("java.version"));
		try {
			if(!System.getProperty("os.name").equals("Linux")) {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}
			UIManager.put("Button.font", new Font(Font.SANS_SERIF, 0, 11));
			UIManager.put("Label.font", new Font(Font.SANS_SERIF, 0, 11));
			UIManager.put("RadioButton.font", new Font(Font.SANS_SERIF, 0, 11));
			
			InstallBuilderWindow window = new InstallBuilderWindow("Install Builder Universal [" + version + "]");
			window.setVisable(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error:\n" + e);
			e.printStackTrace();
			return;
		} 
	}
}
