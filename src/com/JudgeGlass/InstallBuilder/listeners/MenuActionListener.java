<<<<<<< HEAD
package com.JudgeGlass.InstallBuilder.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import com.JudgeGlass.InstallBuilder.tools.Logger;
import com.JudgeGlass.InstallBuilder.updater.CheckUpdate;
import com.JudgeGlass.InstallBuilder.windows.AboutWindow;
import com.JudgeGlass.InstallBuilder.windows.InstallBuilderWindow;
import com.JudgeGlass.InstallBuilder.windows.LogWindow;

public class MenuActionListener implements ActionListener {
	
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("EXIT")) {
			System.exit(0);
		}else if(cmd.equals("ABOUT")) {
			new AboutWindow();
		}else if(cmd.equals("LOG")) {
			LogWindow logWindow = new LogWindow();
			logWindow.setVisible(true);
		}
	}
}
=======
package com.JudgeGlass.InstallBuilder.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.JudgeGlass.InstallBuilder.windows.AboutWindow;
import com.JudgeGlass.InstallBuilder.windows.LogWindow;

public class MenuActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("EXIT")) {
			System.exit(0);
		}else if(cmd.equals("ABOUT")) {
			new AboutWindow();
		}else if(cmd.equals("LOG")) {
			LogWindow logWindow = new LogWindow();
			logWindow.setVisible(true);
		}
	}
}
>>>>>>> 703733152c91c6345f2e175520fd6e35388ae591
