package com.JudgeGlass.InstallBuilder.updater;

import com.JudgeGlass.InstallBuilder.ApplicationInfo;
import com.JudgeGlass.InstallBuilder.Main;
import com.JudgeGlass.InstallBuilder.tools.Logger;
import com.JudgeGlass.InstallBuilder.tools.Utils;

public class CheckUpdate {
	public static boolean updateAvailable = false;
	
	public CheckUpdate(Logger log) {
		log.Info("Contacting the server...");
		log.Info("Checking for update...");
		if(!getWebVersion().equals(ApplicationInfo.VERSION_URL)) {
			updateAvailable = true;
			log.Info("Getting update...");
			Downloader d = new Downloader();
			try {
				Process p = Runtime.getRuntime().exec("Updater.exe");
				System.exit(0);
			}catch(Exception e) {
				e.printStackTrace();
				log.Error("Could not get File!");
			}
		}else {
			log.Info("No Update Found");
			updateAvailable = false;
		}
	}

	private String getWebVersion() {
		return Utils.readLine("version.dat", 0);
	}
}
