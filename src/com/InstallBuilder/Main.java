package com.InstallBuilder;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.InstallBuilder.windows.InstallBuilderWindow;

public class Main {
	public static void main(String args[]) {
		System.out.println(System.getProperty("os.name"));
		try {
			if(!System.getProperty("os.name").equals("Linux")) {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		InstallBuilderWindow window = new InstallBuilderWindow("Install Builder Universal [v0.0.1]");
		window.setVisable(true);
		
	};
}
