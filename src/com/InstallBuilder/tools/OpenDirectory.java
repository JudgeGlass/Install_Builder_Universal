package com.InstallBuilder.tools;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class OpenDirectory {
	private String dir = "";
	public void open(final JPanel panel) {
		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
	    chooser.setDialogTitle("choosertitle");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);

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
