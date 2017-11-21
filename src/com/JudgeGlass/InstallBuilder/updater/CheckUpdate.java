package com.JudgeGlass.InstallBuilder.updater;

import com.JudgeGlass.InstallBuilder.ApplicationInfo;
import com.JudgeGlass.InstallBuilder.Main;
import com.JudgeGlass.InstallBuilder.tools.Crypto;
import com.JudgeGlass.InstallBuilder.tools.Logger;
import com.JudgeGlass.InstallBuilder.tools.Utils;

public class CheckUpdate {
	public static boolean updateAvailable = false;
	Logger log;
	public CheckUpdate(Logger log) {
		this.log = log;
		log.Info("Contacting the server...");
		log.Info("Checking for update...");
		if(!getWebVersion().equals(ApplicationInfo.VERSION_URL)) {
			updateAvailable = true;
			log.Info("Getting update...");
			Downloader d = new Downloader();
			try {
				if(!System.getProperty("os.name").equals("Linux")){
					Process p = Runtime.getRuntime().exec("Updater.exe");
				}else{
					Process p = Runtime.getRuntime().exec("java -jar Updater.jar");
				}
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
		Utils utils = new Utils();
		String infoGrabber = utils.readInfo();
		String[] lines = infoGrabber.split(System.getProperty("line.separator"));
		log.Info("Collected Hash: " + Utils.readLine("version.dat", 0));
		return Crypto.decrypt(Utils.indexOf(lines[0], '='), Utils.readLine("version.dat", 0), log);
	}
}
