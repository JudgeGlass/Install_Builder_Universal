
package com.JudgeGlass.InstallBuilder;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.JudgeGlass.InstallBuilder.tools.Logger;
import com.JudgeGlass.InstallBuilder.updater.CheckUpdate;
import com.JudgeGlass.InstallBuilder.windows.InstallBuilderWindow;
import com.JudgeGlass.Tools.Download.DownloadFile;

public class Main {
	
	public static void main(String args[]) {
		/** TEST */
		/*try {
			URL url = new URL("https://raw.githubusercontent.com/JudgeGlass/OpenGL-Test/master/OpenGL/Logger.cpp");
			InputStream in = url.openStream();
			StringWriter w = new StringWriter();
			IOUtils.copy(in, w, "UTF-8");
			JOptionPane.showMessageDialog(null, w.toString());
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		Logger log = new Logger("latest.log");

		log.Custom(" ###############################################", "About", false);
		log.Custom(" # Install Builder by Hunter Wilcox", "About", false);
		log.Custom(" # Required Java Runtime Version: 1.8.x (Java 8)", "About", false);
		log.Custom(" # License: GNU General Public License v3.0", "About", false);
		log.Custom(" # Version: " + ApplicationInfo.VERSION, "About", false);
		log.Custom(" # Build date: " + ApplicationInfo.BUILD_DATE, "About", false);
		log.Custom(" ###############################################\n", "About", false);
		
		log.Info("System.getProperty(\"os.name\") == " + System.getProperty("os.name"));
		log.Info("System.getProperty(\"os.version\") == " + System.getProperty("os.version"));
		log.Info("System.getProperty(\"os.arch\") == " + System.getProperty("os.arch"));
		log.Info("System.getProperty(\"java.home\") == " + System.getProperty("java.home"));
		log.Info("System.getProperty(\"java.version\") == " + System.getProperty("java.version"));
		
		if(!new File(System.getProperty("user.dir") + "/Cf32.dat").exists()) {
			log.Error("Cf32.dat is missing. Please reinstall to fix the problem");
			JOptionPane.showMessageDialog(null, "Could not find \"Cf32.dat\".\nThis is required for this application to run.\n", "Error: Missing File", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			log.Info("Setting UI...");
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//UIManager.put("Button.font", new Font(Font.MONOSPACED, 0, 10));
			//UIManager.put("Label.font", new Font(Font.MONOSPACED, 0, 4));
			//UIManager.put("RadioButton.font", new Font(Font.MONOSPACED, 0, 10));
			
			
			
			if(ApplicationInfo.runUpdate && !System.getProperty("os.name").equals("Linux")) {
				try {
					log.Info("Fetching: https://github.com/JudgeGlass/Install_Builder_Universal/releases/download/v0.0.4/version.dat");
					DownloadFile getVersionData = new DownloadFile("version.dat", "https://github.com/JudgeGlass/Install_Builder_Universal/releases/download/v0.0.4/version.dat");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}else {
				log.Info("Updater is disabled");
			}
			
			log.Info("Starting Program...");
			InstallBuilderWindow window = new InstallBuilderWindow("Install Builder Universal [" + ApplicationInfo.VERSION + "]", log);
			window.setVisable(true);
			
			if(ApplicationInfo.isDebug) {
				log.Warning("This is a debug version of install builder!");
				JOptionPane.showMessageDialog(null, "This is a Install builder " + ApplicationInfo.VERSION + " DEBUG.\nPlease accecpt bugs.",
						"Debug Warning", JOptionPane.WARNING_MESSAGE);
			}
			
			if(ApplicationInfo.runUpdate && !System.getProperty("os.name").equals("Linux")) {
				CheckUpdate update = new CheckUpdate(log);
				new File("version.dat").delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.Error("Crash: " + e);
			JOptionPane.showMessageDialog(null, "Error:\n" + e, "Crash!", JOptionPane.ERROR_MESSAGE);
			return;
		} 
	}
}

