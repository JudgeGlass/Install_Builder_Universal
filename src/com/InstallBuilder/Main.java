package com.InstallBuilder;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.InstallBuilder.windows.InstallBuilderWindow;

public class Main {
	public static void main(String args[]) {
		System.out.println(System.getProperty("os.name"));
		
		InstallBuilderWindow window = new InstallBuilderWindow("Install Builder Universal [v0.0.1]");
		window.setVisable(true);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	};
}
