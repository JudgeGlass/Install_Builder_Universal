package com.InstallBuilder.tools;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class OpenFile {
	private final JFileChooser fc = new JFileChooser();
	private String files = "";
	
	public void open(final JPanel panel) {
		int returnVal = fc.showOpenDialog(panel);

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
