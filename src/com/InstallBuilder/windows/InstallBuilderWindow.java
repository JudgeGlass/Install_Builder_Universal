package com.InstallBuilder.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.InstallBuilder.listeners.MenuActionListener;
import com.InstallBuilder.tools.ManageDirs;
import com.InstallBuilder.tools.ManageFiles;
import com.InstallBuilder.tools.OpenDirectory;
import com.InstallBuilder.tools.OpenFile;
import com.InstallBuilder.tools.SaveFile;
import com.InstallBuilder.tools.Utils;

public class InstallBuilderWindow {
	
	/** Window Dimensions */
	private final int WIDTH = 805;
	private final int HEIGHT = 460;
	
	/** Components */
	private JFrame frame;
	private JPanel panel;
	
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu helpMenu;
	private JLabel lblChooseFile;
	private JLabel lblChooseDir;
	private JList fileList;
	private DefaultListModel fileModel;
	private JList dirList;
	private DefaultListModel dirModel;
	private JButton btnFileDelete;
	private JButton btnDirDelete;
	private JButton btnFileBrowse;
	private JButton btnDirBrowse;
	
	private JTextField mainExe;
	private JTextField license;
	private JTextField apName;
	private JTextField apDec;
	private JTextField installFolderName;
	
	private JLabel lblMainExe;
	private JLabel lblLicense;
	private JLabel lblApName;
	private JLabel lblApDec;
	private JLabel lblInstallFolderName;
	
	private JButton btnMainExeBrowse;
	private JButton btnLicenseBrowse;
	private JButton btnMake;
	private JButton btnApplication;
	
	private JProgressBar bar;
	
	public static String content = "";
	
	/** Makes The Window */
	public InstallBuilderWindow(final String title) {
		frame = new JFrame();
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);
		
		panel = new JPanel();
		frame.add(panel);
		
		initialize();
	}
	
	/** Adds The Components*/

	private void initialize() {
		menuBar();
		addFileList();
		addDirList();
		
		lblChooseFile = new JLabel("Choose Files");
		lblChooseFile.setBounds(5, 20, 175, 15);
		frame.getContentPane().add(lblChooseFile);
		
		lblChooseDir = new JLabel("Choose Directory");
		lblChooseDir.setBounds(495, 20, 175, 15);
		frame.getContentPane().add(lblChooseDir);
		
		btnFileDelete = new JButton("Delete");
		btnFileDelete.setBounds(230, 240, 75, 20);
		btnFileDelete.setActionCommand("FILE_DELETE");
		btnFileDelete.addActionListener(new BtnListener());
		frame.getContentPane().add(btnFileDelete);
		
		btnDirDelete = new JButton("Delete");
		btnDirDelete.setBounds(718, 240, 75, 20);
		btnDirDelete.setActionCommand("DIR_DELETE");
		btnDirDelete.addActionListener(new BtnListener());
		frame.getContentPane().add(btnDirDelete);
		
		btnFileBrowse = new JButton("Browse...");
		btnFileBrowse.setBounds(5, 240, 90, 20);
		btnFileBrowse.setActionCommand("OPEN_FILE");
		btnFileBrowse.addActionListener(new BtnListener());
		frame.getContentPane().add(btnFileBrowse);
		
		btnDirBrowse = new JButton("Browse...");
		btnDirBrowse.setBounds(495, 240, 90, 20);
		btnDirBrowse.setActionCommand("OPEN_DIR");
		btnDirBrowse.addActionListener(new BtnListener());
		frame.getContentPane().add(btnDirBrowse);
		
		mainExe = new JTextField();
		mainExe.setBounds(5, 360, 150, 20);
		frame.getContentPane().add(mainExe);
		
		btnMainExeBrowse = new JButton("Browse...");
		btnMainExeBrowse.setBounds(160, 359, 90, 20);
		btnMainExeBrowse.setActionCommand("OPEN_MAINEXE");
		btnMainExeBrowse.addActionListener(new BtnListener());
		frame.getContentPane().add(btnMainExeBrowse);
		
		license = new JTextField();
		license.setBounds(255, 360, 150, 20);
		frame.getContentPane().add(license);
		
		btnLicenseBrowse = new JButton("Browse...");
		btnLicenseBrowse.setBounds(410, 359, 90, 20);
		btnLicenseBrowse.setActionCommand("OPEN_LICENSE");
		btnLicenseBrowse.addActionListener(new BtnListener());
		frame.getContentPane().add(btnLicenseBrowse);
		
		apName = new JTextField();
		apName.setBounds(5, 405, 150, 20);
		frame.getContentPane().add(apName);
		
		apDec = new JTextField();
		apDec.setBounds(160, 405, 150, 20);
		frame.getContentPane().add(apDec);
	
		installFolderName = new JTextField();
		installFolderName.setBounds(315, 405, 150, 20);
		frame.getContentPane().add(installFolderName);
		
		lblMainExe = new JLabel("Choose Main Executable:");
		lblMainExe.setBounds(5, 345, 300, 15);
		frame.getContentPane().add(lblMainExe);
		
		lblLicense = new JLabel("License:");
		lblLicense.setBounds(255, 345, 100, 15);
		frame.getContentPane().add(lblLicense);
		
		lblApName = new JLabel("Application Name:");
		lblApName.setBounds(5, 390, 200, 15);
		frame.getContentPane().add(lblApName);
		
		lblApDec = new JLabel("Application Description:");
		lblApDec.setBounds(160, 390, 200, 15);
		frame.getContentPane().add(lblApDec);
		
		lblInstallFolderName = new JLabel("Install Folder Name:");
		lblInstallFolderName.setBounds(315, 390, 300, 15);
		frame.getContentPane().add(lblInstallFolderName);
		
		btnMake = new JButton("Make");
		btnMake.setBounds(718, 405, 75, 20);
		btnMake.setActionCommand("MAKE");
		btnMake.addActionListener(new BtnListener());
		frame.getContentPane().add(btnMake);
		
		btnApplication = new JButton("Apps.");
		btnApplication.setBounds(718, 380, 75, 20);
		btnApplication.setActionCommand("APPLICATTIONS");
		btnApplication.addActionListener(new BtnListener());
		frame.getContentPane().add(btnApplication);
		
		bar = new JProgressBar();
		bar.setIndeterminate(true);
		bar.setBounds(475, 405, 240, 15);
		bar.hide();
		frame.getContentPane().add(bar);
	}
	
	/** Make The Menu Bar*/
	private void menuBar() {
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 795, 20);
		frame.getContentPane().add(menuBar);
		
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem exitFile = new JMenuItem("Exit");
		exitFile.setActionCommand("EXIT");
		exitFile.addActionListener(new MenuActionListener());
		
		helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		JMenuItem aboutHelp = new JMenuItem("About");
		aboutHelp.setActionCommand("ABOUT");
		aboutHelp.addActionListener(new MenuActionListener());
		helpMenu.add(aboutHelp);
		
		JMenuItem saveConf = new JMenuItem("Save Configuration");
		saveConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveConfiguration();
			}
		});
		
		JMenuItem openConf = new JMenuItem("OpenConfiguration");
		openConf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openConfiguration();
			}
		});
		
		fileMenu.add(openConf);
		fileMenu.add(saveConf);
		fileMenu.add(exitFile);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addFileList() {
		fileModel = new DefaultListModel();
		fileList = new JList(fileModel);
		JScrollPane sp = new JScrollPane(fileList);
		sp.setBounds(5, 35, 300, 200);
		frame.getContentPane().add(sp);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addDirList() {
		dirModel = new DefaultListModel();
		dirList = new JList(dirModel);
		JScrollPane sp = new JScrollPane(dirList);
		sp.setBounds(495, 35, 300, 200);
		frame.getContentPane().add(sp);
	}
	
	public void setVisable(final boolean vis) {
		frame.setVisible(vis);
	}
	
	
	private class BtnListener implements ActionListener{
		@SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(cmd.equals("FILE_DELETE")) {
				if(!fileList.isSelectionEmpty())
					fileModel.removeElementAt(fileList.getSelectedIndex());
			}else if(cmd.equals("DIR_DELETE")) {
				if(!dirList.isSelectionEmpty()) {
					dirModel.remove(dirList.getLeadSelectionIndex());
				}
			}else if(cmd.equals("OPEN_FILE")) {
				OpenFile opf = new OpenFile();
				opf.open(panel);
				if(opf.getFile().isEmpty()) return;
				fileModel.addElement(opf.getFile());
			}else if(cmd.equals("OPEN_DIR")) {
				OpenDirectory opd = new OpenDirectory();
				opd.open(panel);
				if(opd.getDir().isEmpty()) return;
				dirModel.addElement(opd.getDir());
			}else if(cmd.equals("OPEN_LICENSE")) {
				OpenFile opf = new OpenFile();
				opf.open(panel);
				if(opf.getFile().isEmpty()) return;
				license.setText(opf.getFile());
			}else if(cmd.equals("OPEN_MAINEXE")) {
				OpenFile opf = new OpenFile();
				opf.open(panel);
				if(opf.getFile().isEmpty()) return;
				mainExe.setText(opf.getFile());
			}else if(cmd.equals("MAKE")) {
				if(fileModel.size() == 0) {
					JOptionPane.showMessageDialog(null, "You need at least one file to continue", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(apName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "\"Application Name\" must be filled", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(license.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "\"License\" must be filled", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(installFolderName.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "\"Install Folder Name\" must be filled", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(apDec.getText().isEmpty()){
					apDec.setText("Description not given");
				}
				
				bar.show();
				enableButtons(false);
				make();
			}else if(cmd.equals("APPLICATTIONS")) {
				try {
					Process p = Runtime.getRuntime().exec("explorer " + System.getProperty("user.dir"));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Error:\n" + e1, "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
	}
	
	private void make() {
		new Thread(new Runnable() {
			public void run() {
				File fi = new File(apName.getText());
				fi.mkdir();
				
				content = makeConf(content);
				
				ManageFiles f = new ManageFiles();
				f.copyFiles(fileModel, apName);
				ManageDirs d = new ManageDirs();
				d.copyDir(dirModel, apName);
				content += "main-executable=" + mainExe.getText();
				Utils.writeFile("Conf.txt", content);
				
				if(!System.getProperty("os.name").equals("Linux"))
					Utils.copyFile(new File(license.getText()), new File(apName.getText() + "/" + Utils.indexOf(license.getText(), '\\')));
				else
					Utils.copyFile(new File(license.getText()), new File(apName.getText() + "/" + Utils.indexOf(license.getText(), '/')));
				Utils.copyFile(new File("Conf.txt"), new File(apName.getText() + "/Conf.txt"));
				Utils.copyFile(new File("Cf32.dat"), new File(apName.getText() + "/SETUP.jar"));
				bar.hide();
				enableButtons(true);
				content = "";
				System.out.println(content);
				JOptionPane.showMessageDialog(null, "Setup is completed!", "Done", JOptionPane.INFORMATION_MESSAGE);
			}
		}).start();
	}
	
	private void enableButtons(final boolean enable) {
		btnFileDelete.setEnabled(enable);
		btnDirDelete.setEnabled(enable);
		btnFileBrowse.setEnabled(enable);
		btnDirBrowse.setEnabled(enable);
		btnMainExeBrowse.setEnabled(enable);
		btnLicenseBrowse.setEnabled(enable);
		btnMake.setEnabled(enable);
		
	}
	
	private String makeConf(String conf) {
		conf += "application-name=" + apName.getText() + "\n";
		conf += "application-details=" + apDec.getText() + "\n";
		conf += "default-install-dir=C:\\Program Files\n";
		
		if(!System.getProperty("os.name").equals("Linux"))
			conf += "license-path=" + Utils.indexOf(license.getText(), '\\') + "\n";
		else
			conf += "license-path=" + Utils.indexOf(license.getText(), '/') + "\n";
		
		conf += "install-folder-name=" + installFolderName.getText() + "\n";
		conf += "## FILES ##" + "\n";
		return conf;
	}
	
	private void saveConfiguration() {
		List<String> lines = new ArrayList<String>();
		lines.add(mainExe.getText());
		lines.add(license.getText());
		lines.add(apName.getText());
		lines.add(apDec.getText());
		lines.add(installFolderName.getText());
		lines.add("## FILES ##");
		
		for(int i = 0; i < fileModel.getSize(); i++) {
			lines.add(fileModel.getElementAt(i).toString());
		}
		lines.add("## FILES END ##");
		lines.add("## DIRS ##");
		if(dirModel.size() != 0) {
			for(int i = 0; i < dirModel.getSize(); i++) {
				lines.add(dirModel.getElementAt(i).toString());
			}
			lines.add("## DIRS END ##");
		}else {
			lines.add("\n## DIRS END ##");
		}
		
		
		SaveFile saveFile = new SaveFile();
		saveFile.save(panel);
		String dir = saveFile.getDir();
		try {
			PrintWriter writer = new PrintWriter(new File(dir + "/SaveInstallBuilder.dat"), "UTF-8");
			String linesToString = "";
			for(int i = 0; i<lines.size(); i++) {
				linesToString += lines.get(i) + "\n";
			}
			writer.write(linesToString);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	private void openConfiguration() {
		OpenFile opf = new OpenFile();
		opf.open(panel);
		
		String fileName = opf.getFile();
		mainExe.setText(Utils.readLine(fileName, 0));
		license.setText(Utils.readLine(fileName, 1));
		apName.setText(Utils.readLine(fileName, 2));
		apDec.setText(Utils.readLine(fileName, 3));
		installFolderName.setText(Utils.readLine(fileName, 4));
		
		int fileStartPoint = 6;
		while(!Utils.readLine(fileName, fileStartPoint).equals("## FILES END ##")) {
			fileModel.addElement(Utils.readLine(fileName, fileStartPoint));
			fileStartPoint++;
		}
		
		int dirStartPoint = fileStartPoint + 2;
		while(!Utils.readLine(fileName, dirStartPoint).equals("## DIRS END ##")) {
			if(Utils.readLine(fileName, dirStartPoint).isEmpty()) break;
			dirModel.addElement(Utils.readLine(fileName, dirStartPoint));
			dirStartPoint++;
		}
	}
}
