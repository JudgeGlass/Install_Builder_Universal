package com.JudgeGlass.InstallBuilder.tools;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class OpenFile {
	//private String files = "";
	
	public File[] open(final JPanel panel) {
		JFileChooser fc = new JFileChooser();
		fc.setMultiSelectionEnabled(true);
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fc.setDialogTitle("Choose File(s)");
		fc.showOpenDialog(panel);
        return fc.getSelectedFiles();
	}
	
	
}
