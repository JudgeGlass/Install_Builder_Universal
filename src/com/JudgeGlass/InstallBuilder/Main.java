
package com.JudgeGlass.InstallBuilder;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.JudgeGlass.InstallBuilder.tools.Crypto;
import com.JudgeGlass.InstallBuilder.tools.Logger;
import com.JudgeGlass.InstallBuilder.updater.CheckUpdate;
import com.JudgeGlass.InstallBuilder.windows.InstallBuilderWindow;
import com.JudgeGlass.Tools.Download.DownloadFile;

public class Main {
	
	public static void main(String args[]) {
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
		
		try {
			log.Info("Setting UI...");
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			if(ApplicationInfo.runUpdate) {
				try {
					log.Info("Fetching Hash: E+D0FsJXcGskuSQWALAGgmVWjuVse/gTtPaJq27pwpNqEBPtgA+1w1DVxR+Af/IGgWPS9/D+PtW69Vx2zTuJN08mM7WFcyDX4DhK65YRpM77jx9m6Epu3rM4wCLnm9Eg");
					DownloadFile getVersionData = new DownloadFile("version.dat", "https://github.com/JudgeGlass/Install_Builder_Universal/releases/download/v0.0.4/test.dat");
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
			
			if(ApplicationInfo.runUpdate) {
				CheckUpdate update = new CheckUpdate(log);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.Error("Crash: " + e);
			JOptionPane.showMessageDialog(null, "Error:\n" + e, "Crash!", JOptionPane.ERROR_MESSAGE);
			return;
		} 
	}
}

