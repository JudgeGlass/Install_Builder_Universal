package com.InstallBuilder.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.InstallBuilder.windows.AboutWindow;

public class MenuActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("EXIT")) {
			System.exit(0);
		}else if(cmd.equals("ABOUT")) {
			new AboutWindow();
		}
	}
}
