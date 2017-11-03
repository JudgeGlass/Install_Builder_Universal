
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
	public static final String versionUrl = "https://github.com/JudgeGlass/Install_Builder_Universal/releases/download/v0.0.5.1/InstallBuilder_v0.0.5.1.zip";
	public static final String version = "v0.0.5.2";
	public static final String buildDate = "10/25/17";
	public static final boolean isDebug = true;
	public static final boolean runUpdate = !isDebug;
	
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
			
			
			if(runUpdate && !System.getProperty("os.name").equals("Linux")) {
				try {
					DownloadFile getVersionData = new DownloadFile("version.dat", "https://github.com/JudgeGlass/Install_Builder_Universal/releases/download/v0.0.4/version.dat");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else {
				log.Info("Updater is disabled");
			}
			
			log.Info("Starting Program...");
			InstallBuilderWindow window = new InstallBuilderWindow("Install Builder Universal [" + version + "]", log);
			window.setVisable(true);
			
			if(Main.isDebug) {
				JOptionPane.showMessageDialog(null, "This is a Install builder " + Main.version + " DEBUG.\nPlease accecpt bugs.",
						"Debug Warning", JOptionPane.WARNING_MESSAGE);
			}
			
			if(runUpdate && !System.getProperty("os.name").equals("Linux")) {
				CheckUpdate update = new CheckUpdate(log);
				new File("version.dat").delete();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error:\n" + e);
			e.printStackTrace();
			log.Error("Could not load UI: " + e.getMessage());
			return;
		} 
	}
}

