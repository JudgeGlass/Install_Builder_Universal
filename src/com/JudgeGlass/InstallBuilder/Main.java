package com.JudgeGlass.InstallBuilder;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.JudgeGlass.InstallBuilder.tools.Logger;
import com.JudgeGlass.InstallBuilder.windows.InstallBuilderWindow;

public class Main {
	public static final String version = "v0.0.4";
	public static final boolean isDebug = false;
	
	public static void main(String args[]) {
		Logger log = new Logger("latest.log");
		
		log.Info("System.getProperty(\"os.name\") == " + System.getProperty("os.name"));
		log.Info("System.getProperty(\"os.arch\") == " + System.getProperty("os.arch"));
		log.Info("System.getProperty(\"java.home\") == " + System.getProperty("java.home"));
		log.Info("System.getProperty(\"java.version\") == " + System.getProperty("java.version"));
		
		try {
			log.Info("Setting UI...");
			if(!System.getProperty("os.name").equals("Linux")) {
				JOptionPane.showMessageDialog(null, "OS not supported!\nIf your on windows please get the windows version.\n"
						+ "https://github.com/JudgeGlass/Install_Builder_Universal/releases/tag/v0.0.4", "OS not supported!", JOptionPane.ERROR_MESSAGE);
				return;
			}else if(System.getProperty("os.name").equals("Linux")){
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
				
				UIManager.put("Button.font", new Font(Font.MONOSPACED, 0, 10));
				UIManager.put("Label.font", new Font(Font.MONOSPACED, 0, 4));
				UIManager.put("RadioButton.font", new Font(Font.MONOSPACED, 0, 10));
			}
			
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
