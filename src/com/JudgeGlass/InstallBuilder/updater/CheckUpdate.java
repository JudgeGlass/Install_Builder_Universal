package com.JudgeGlass.InstallBuilder.updater;

import com.JudgeGlass.InstallBuilder.Main;
import com.JudgeGlass.InstallBuilder.tools.Logger;
import com.JudgeGlass.InstallBuilder.tools.Utils;

public class CheckUpdate {
	public CheckUpdate(Logger log) {
		log.Info("Checking for update...");
		if(!getWebVersion().equals(Main.versionUrl)) {
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
		}
	}

	private String getWebVersion() {
		return Utils.readLine("version.dat", 0);
	}
}
