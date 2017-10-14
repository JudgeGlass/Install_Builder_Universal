package com.JudgeGlass.InstallBuilder.tools;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class SaveFile {
	private String dir = "";
	public void save(final JPanel panel) {
		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
	    chooser.setDialogTitle("Choose Directory To Save To");
	    //chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);
	    chooser.setApproveButtonText("Save");
	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	      dir = chooser.getSelectedFile().toString();
	    } else {
	      System.err.println("Could not open Dir");
	      return;
	    }
	}
	public String getDir() {
		return dir;
	}
}
