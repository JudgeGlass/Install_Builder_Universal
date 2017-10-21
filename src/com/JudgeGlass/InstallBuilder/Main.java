<<<<<<< HEAD
package com.JudgeGlass.InstallBuilder;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.JudgeGlass.InstallBuilder.tools.Logger;
import com.JudgeGlass.InstallBuilder.updater.CheckUpdate;
import com.JudgeGlass.InstallBuilder.windows.ExtractWindow;
import com.JudgeGlass.InstallBuilder.windows.InstallBuilderWindow;
import com.JudgeGlass.Tools.Download.DownloadFile;

public class Main {
	public static final String versionUrl = "https://github.com/JudgeGlass/Install_Builder_Universal/releases/download/v0.0.3/InstallBuilder_v0.0.4.zip";
	public static final String version = "v0.0.5";
	public static final String buildDate = "10/19/17";
	public static final boolean isDebug = false;
	
	public static void main(String args[]) {
		
		
		Logger log = new Logger("latest.log");
		
		log.Info("System.getProperty(\"os.name\") == " + System.getProperty("os.name"));
		log.Info("System.getProperty(\"os.version\") == " + System.getProperty("os.version"));
		log.Info("System.getProperty(\"os.arch\") == " + System.getProperty("os.arch"));
		log.Info("System.getProperty(\"java.home\") == " + System.getProperty("java.home"));
		log.Info("System.getProperty(\"java.version\") == " + System.getProperty("java.version"));
		
		try {
			log.Info("Setting UI...");
			if(!System.getProperty("os.name").equals("Linux")) {
				log.Error("OS is not supported for InstallBuilder Universal " + version);
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				
			}else if(System.getProperty("os.name").equals("Linux")){
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
				
				UIManager.put("Button.font", new Font(Font.MONOSPACED, 0, 10));
				UIManager.put("Label.font", new Font(Font.MONOSPACED, 0, 4));
				UIManager.put("RadioButton.font", new Font(Font.MONOSPACED, 0, 10));
			}
			
			
			try {
				DownloadFile getVersionData = new DownloadFile("version.dat", "https://github.com/JudgeGlass/Install_Builder_Universal/releases/download/v0.0.4/version.dat");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			log.Info("Starting Program...");
			InstallBuilderWindow window = new InstallBuilderWindow("Install Builder Universal [" + version + "]", log);
			window.setVisable(true);
			
			if(Main.isDebug) {
				JOptionPane.showMessageDialog(null, "This is a Install builder " + Main.version + " DEBUG.\nPlease accecpt bugs.",
						"Debug Warning", JOptionPane.WARNING_MESSAGE);
			}
			
			CheckUpdate update = new CheckUpdate(log);
			new File("version.dat").delete();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error:\n" + e);
			e.printStackTrace();
			log.Error("Could not load UI: " + e.getMessage());
			return;
		} 
	}
}
=======
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
>>>>>>> 703733152c91c6345f2e175520fd6e35388ae591
