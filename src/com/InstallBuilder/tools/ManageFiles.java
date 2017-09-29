package com.InstallBuilder.tools;

import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;

import org.apache.commons.io.FileUtils;

import com.InstallBuilder.windows.InstallBuilderWindow;

public class ManageFiles {
	public void copyFiles(final DefaultListModel fileModel, final JTextField txt) {
		for(int i = 0;i<fileModel.size(); i++) {
			File f = new File(fileModel.getElementAt(i).toString());
			try {
				if(!System.getProperty("os.name").equals("Linux"))
					InstallBuilderWindow.content +=  Utils.indexOf(fileModel.getElementAt(i).toString(), '\\') + "\n";
				else
					InstallBuilderWindow.content +=  Utils.indexOf(fileModel.getElementAt(i).toString(), '/') + "\n";
				
				if(!System.getProperty("os.name").equals("Linux"))
					FileUtils.copyFile(f, new File(System.getProperty("user.dir") + "\\" + txt.getText() + "\\" + 
							Utils.indexOf(fileModel.getElementAt(i).toString(), '\\')));
				else
					FileUtils.copyFile(f, new File(System.getProperty("user.dir") + "/" + txt.getText() + "/" + 
							Utils.indexOf(fileModel.getElementAt(i).toString(), '/')));
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("COPYING: " + fileModel.getElementAt(i));
		}
		InstallBuilderWindow.content += "## FILES END ##\n\n## DIRS ##\n";
	}
}
