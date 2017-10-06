package com.JudgeGlass.InstallBuilder.tools;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class OpenOtherFile {
	private String files = "";
	
	public void open(final JPanel panel) {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(panel);
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fc.setDialogTitle("Choose File(s)");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            
            files = file.getPath();
            //This is where a real application would open the file.
        } else {
        	return;
        }
	}
	
	public String getFile(){
		return files;
	}
}