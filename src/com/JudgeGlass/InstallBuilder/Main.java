package com.JudgeGlass.InstallBuilder;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.JudgeGlass.InstallBuilder.tools.Logger;
import com.JudgeGlass.InstallBuilder.windows.InstallBuilderWindow;

public class Main {
	public static final String version = "v0.0.3.3";
	public static final boolean isDebug = true;
	
	public static void main(String args[]) {
		Logger log = new Logger("Latest.log");
		
		log.Info("System.getProperty(\"os.name\") == " + System.getProperty("os.name"));
		log.Info("System.getProperty(\"os.arch\") == " + System.getProperty("os.arch"));
		log.Info("System.getProperty(\"java.home\") == " + System.getProperty("java.home"));
		log.Info("System.getProperty(\"java.version\") == " + System.getProperty("java.version"));
		
		try {
			log.Info("Setting UI...");
			if(!System.getProperty("os.name").equals("Linux")) {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}
			UIManager.put("Button.font", new Font(Font.SANS_SERIF, 0, 11));
			UIManager.put("Label.font", new Font(Font.SANS_SERIF, 0, 11));
			UIManager.put("RadioButton.font", new Font(Font.SANS_SERIF, 0, 11));
			
			log.Info("Starting Program...");
			InstallBuilderWindow window = new InstallBuilderWindow("Install Builder Universal [" + version + "]", log);
			window.setVisable(true);
			
			if(Main.isDebug) {
				JOptionPane.showMessageDialog(null, "This is a Install builder " + Main.version + " DEBUG.\nPlease accecpt bugs.", "Debug Warning", JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error:\n" + e);
			e.printStackTrace();
			log.Error("Could not load UI: " + e.getMessage());
			return;
		} 
	}
}
