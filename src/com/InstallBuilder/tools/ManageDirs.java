package com.InstallBuilder.tools;

import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JTextField;

import org.apache.commons.io.FileUtils;

import com.InstallBuilder.windows.InstallBuilderWindow;

public class ManageDirs {
	public void copyDir(final DefaultListModel dirModel, final JTextField txt) {
		if(dirModel.size() != 0) {
			for(int i = 0;i<dirModel.size(); i++) {
				System.out.println("COPYING: <DIR> " + dirModel.getElementAt(i));
				File f = new File(dirModel.getElementAt(i).toString());
				try {
					if(!System.getProperty("os.name").equals("Linux"))
						InstallBuilderWindow.content +=  Utils.indexOf(dirModel.getElementAt(i).toString(), '\\') + "\n";
					else
						InstallBuilderWindow.content +=  Utils.indexOf(dirModel.getElementAt(i).toString(), '/') + "\n";
					
					if(!System.getProperty("os.name").equals("Linux"))
						FileUtils.copyDirectory(f, new File(System.getProperty("user.dir") + "/" + txt.getText() + "/" + 
								Utils.indexOf(dirModel.getElementAt(i).toString(), '\\')));
					else
						FileUtils.copyDirectory(f, new File(System.getProperty("user.dir") + "/" + txt.getText() + "/" + 
								Utils.indexOf(dirModel.getElementAt(i).toString(), '/')));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			InstallBuilderWindow.content += "## DIRS END ##\n";
		}else {
			InstallBuilderWindow.content += "\n## DIRS END ##\n";
		}
	}
}
