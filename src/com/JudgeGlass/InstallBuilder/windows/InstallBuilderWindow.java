
package com.JudgeGlass.InstallBuilder.windows;

import java.awt.Font;
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
import javax.swing.JCheckBoxMenuItem;
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
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

import com.JudgeGlass.InstallBuilder.ApplicationInfo;
import com.JudgeGlass.InstallBuilder.listeners.MenuActionListener;
import com.JudgeGlass.InstallBuilder.tools.Logger;
import com.JudgeGlass.InstallBuilder.tools.ManageDirs;
import com.JudgeGlass.InstallBuilder.tools.ManageFiles;
import com.JudgeGlass.InstallBuilder.tools.OpenDirectory;
import com.JudgeGlass.InstallBuilder.tools.OpenFile;
import com.JudgeGlass.InstallBuilder.tools.OpenOtherFile;
import com.JudgeGlass.InstallBuilder.tools.SaveFile;
import com.JudgeGlass.InstallBuilder.tools.Utils;
import com.JudgeGlass.InstallBuilder.updater.CheckUpdate;

public class InstallBuilderWindow {
	
	/** Window Dimensions */
	private final int WIDTH = 820;
	private final int HEIGHT = 460;
	
	/** Components */
	private JFrame frame;
	private JPanel panel;
	
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu helpMenu;
	private JMenu styleMenu;
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
	
	private JButton btnLicenseBrowse;
	private JButton btnMake;
	private JButton btnApplication;
	
	private JProgressBar fileBar;
	private JProgressBar dirBar;
	private JLabel lblInstalling;
	
	public static String content = "";
	
	private Logger log;
	
	/** Makes The Window */
	public InstallBuilderWindow(final String title, final Logger log) {
		
		this.log = log;
		frame = new JFrame();
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false);
		
		panel = new JPanel();
		frame.add(panel);
		
		log.Info("Initializing Components...");
		initialize();
		log.Info("Initialized components successfully!");
	}
	
	/** Adds The Components*/

	@SuppressWarnings("deprecation")
	private void initialize() {
		menuBar();
		addFileList();
		addDirList();
		
		lblChooseFile = new JLabel("Files");
		lblChooseFile.setFont(new Font(Font.SANS_SERIF, 0, 12));
		lblChooseFile.setBounds(5, 20, 175, 15);
		frame.getContentPane().add(lblChooseFile);
		
		lblChooseDir = new JLabel("Directories");
		lblChooseDir.setFont(new Font(Font.SANS_SERIF, 0, 12));
		lblChooseDir.setBounds(445, 20, 175, 15);
		frame.getContentPane().add(lblChooseDir);
		
		btnFileDelete = new JButton("Delete");
		btnFileDelete.setFont(new Font(Font.SANS_SERIF, 0, 12));
		btnFileDelete.setBounds(280, 240, 75, 20);
		btnFileDelete.setActionCommand("FILE_DELETE");
		btnFileDelete.addActionListener(new BtnListener());
		frame.getContentPane().add(btnFileDelete);
		
		btnDirDelete = new JButton("Delete");
		btnDirDelete.setFont(new Font(Font.SANS_SERIF, 0, 12));
		btnDirDelete.setBounds(730, 240, 75, 20);
		btnDirDelete.setActionCommand("DIR_DELETE");
		btnDirDelete.addActionListener(new BtnListener());
		frame.getContentPane().add(btnDirDelete);
		
		btnFileBrowse = new JButton("Browse...");
		btnFileBrowse.setFont(new Font(Font.SANS_SERIF, 0, 12));
		btnFileBrowse.setBounds(5, 240, 90, 20);
		btnFileBrowse.setActionCommand("OPEN_FILE");
		btnFileBrowse.addActionListener(new BtnListener());
		frame.getContentPane().add(btnFileBrowse);
		
		btnDirBrowse = new JButton("Browse...");
		btnDirBrowse.setFont(new Font(Font.SANS_SERIF, 0, 12));
		btnDirBrowse.setBounds(445, 240, 90, 20);
		btnDirBrowse.setActionCommand("OPEN_DIR");
		btnDirBrowse.addActionListener(new BtnListener());
		frame.getContentPane().add(btnDirBrowse);
		
		mainExe = new JTextField();
		mainExe.setFont(new Font(Font.SANS_SERIF, 0, 12));
		mainExe.setBounds(5, 360, 150, 20);
		mainExe.setToolTipText("Ex: program.exe or folder/pro.exe");
		frame.getContentPane().add(mainExe);
		
		license = new JTextField();
		license.setFont(new Font(Font.SANS_SERIF, 0, 12));
		license.setBounds(160, 360, 150, 20);
		frame.getContentPane().add(license);
		
		btnLicenseBrowse = new JButton("Browse...");
		btnLicenseBrowse.setFont(new Font(Font.SANS_SERIF, 0, 12));
		btnLicenseBrowse.setBounds(315, 359, 90, 20);
		btnLicenseBrowse.setActionCommand("OPEN_LICENSE");
		btnLicenseBrowse.addActionListener(new BtnListener());
		frame.getContentPane().add(btnLicenseBrowse);
		
		apName = new JTextField();
		apName.setFont(new Font(Font.SANS_SERIF, 0, 12));
		apName.setBounds(5, 405, 150, 20);
		frame.getContentPane().add(apName);
		
		apDec = new JTextField();
		apDec.setFont(new Font(Font.SANS_SERIF, 0, 12));
		apDec.setBounds(160, 405, 150, 20);
		frame.getContentPane().add(apDec);
	
		installFolderName = new JTextField();
		installFolderName.setFont(new Font(Font.SANS_SERIF, 0, 12));
		installFolderName.setBounds(315, 405, 150, 20);
		frame.getContentPane().add(installFolderName);
		
		lblMainExe = new JLabel("Main Exe File(From list)");
		lblMainExe.setFont(new Font(Font.SANS_SERIF, 0, 12));
		lblMainExe.setBounds(5, 345, 300, 15);
		frame.getContentPane().add(lblMainExe);
		
		lblLicense = new JLabel("License:");
		lblLicense.setFont(new Font(Font.SANS_SERIF, 0, 12));
		lblLicense.setBounds(160, 345, 100, 15);
		frame.getContentPane().add(lblLicense);
		
		lblApName = new JLabel("Application Name:");
		lblApName.setFont(new Font(Font.SANS_SERIF, 0, 12));
		lblApName.setBounds(5, 390, 200, 15);
		frame.getContentPane().add(lblApName);
		
		lblApDec = new JLabel("Application Description:");
		lblApDec.setFont(new Font(Font.SANS_SERIF, 0, 12));
		lblApDec.setBounds(160, 390, 200, 15);
		frame.getContentPane().add(lblApDec);
		
		lblInstallFolderName = new JLabel("Install Folder Name:");
		lblInstallFolderName.setFont(new Font(Font.SANS_SERIF, 0, 12));
		lblInstallFolderName.setBounds(315, 390, 300, 15);
		frame.getContentPane().add(lblInstallFolderName);
		
		btnMake = new JButton("Make");
		btnMake.setFont(new Font(Font.SANS_SERIF, 0, 12));
		btnMake.setBounds(718, 405, 75, 20);
		btnMake.setActionCommand("MAKE");
		btnMake.addActionListener(new BtnListener());
		frame.getContentPane().add(btnMake);
		
		btnApplication = new JButton("Apps.");
		btnApplication.setFont(new Font(Font.SANS_SERIF, 0, 12));
		btnApplication.setBounds(718, 380, 75, 20);
		btnApplication.setActionCommand("APPLICATTIONS");
		btnApplication.addActionListener(new BtnListener());
		if(System.getProperty("os.name").equals("Linux")) {
			btnApplication.setEnabled(false);
			btnApplication.setToolTipText("Not Compatible with your operating system");
		}
		frame.getContentPane().add(btnApplication);
		lblInstalling = new JLabel("Making...");
		lblInstalling.setFont(new Font(Font.SANS_SERIF, 0, 12));
		lblInstalling.setBounds(475, 390, 100, 15);
		lblInstalling.hide();
		frame.getContentPane().add(lblInstalling);
		
		fileBar = new JProgressBar();
		//bar.setIndeterminate(true);
		fileBar.setStringPainted(true);
		fileBar.setBounds(475, 405, 240, 15);
		fileBar.hide();
		frame.getContentPane().add(fileBar);
		
		dirBar = new JProgressBar();
		//bar.setIndeterminate(true);
		dirBar.setStringPainted(true);
		dirBar.setBounds(475, 405, 240, 15);
		dirBar.hide();
		frame.getContentPane().add(dirBar);
	}
	
	/** Make The Menu Bar*/
	private void menuBar() {
		menuBar = new JMenuBar();
		menuBar.setFont(new Font(Font.SANS_SERIF, 0, 12));
		menuBar.setBounds(0, 0, frame.getWidth(), 20);
		frame.getContentPane().add(menuBar);
		
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem exitFile = new JMenuItem("Exit");
		exitFile.setActionCommand("EXIT");
		exitFile.addActionListener(new MenuActionListener());
		
		styleMenu = new JMenu("Style");
		menuBar.add(styleMenu);
		
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
		
		JMenuItem openLog = new JMenuItem("Latest Log");
		openLog.setActionCommand("LOG");
		openLog.addActionListener(new MenuActionListener());
		
		JMenuItem update = new JMenuItem("Check for update");
		
		if(!ApplicationInfo.runUpdate)
			update.setEnabled(false);
			
		
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CheckUpdate(log);
				if(!CheckUpdate.updateAvailable) {
					JOptionPane.showMessageDialog(null, "InstallBuilder " + ApplicationInfo.VERSION + " is the latest.", "No Update", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		JCheckBoxMenuItem  normalTheme = new JCheckBoxMenuItem ("System Theme");
		JCheckBoxMenuItem javaTheme = new JCheckBoxMenuItem("Metal Theme");
		normalTheme.setSelected(true);
		
		normalTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isChecked;
				if(normalTheme.isSelected()) {
					
			        try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						javaTheme.setSelected(false);
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
							| UnsupportedLookAndFeelException e1) {
						e1.printStackTrace();
					}
			       
			        SwingUtilities.updateComponentTreeUI(frame);
				}else {
					try {
						UIManager.setLookAndFeel(new MetalLookAndFeel());
						javaTheme.setSelected(true);
					} catch (UnsupportedLookAndFeelException e1) {
						e1.printStackTrace();
					}
			       
			        SwingUtilities.updateComponentTreeUI(frame);
				}
			}
		});
		
		javaTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(normalTheme.isSelected()) {
					
			        try {
			        	UIManager.setLookAndFeel(new MetalLookAndFeel());
			        	normalTheme.setSelected(false);
					} catch (UnsupportedLookAndFeelException e1) {
						e1.printStackTrace();
					}
			       
			        SwingUtilities.updateComponentTreeUI(frame);
				}else {
					try {
						try {
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
							e1.printStackTrace();
						}
						normalTheme.setSelected(true);
					} catch (UnsupportedLookAndFeelException e1) {
						e1.printStackTrace();
					}
			       
			        SwingUtilities.updateComponentTreeUI(frame);
				}
			}
			
		});
		
		
		
		
		styleMenu.add(normalTheme);
		styleMenu.add(javaTheme);
		
		fileMenu.add(openConf);
		fileMenu.add(saveConf);
		fileMenu.add(openLog);
		fileMenu.add(update);
		fileMenu.add(exitFile);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addFileList() {
		fileModel = new DefaultListModel();
		fileList = new JList(fileModel);
		fileList.setFont(new Font(Font.SANS_SERIF, 0, 12));
		JScrollPane sp = new JScrollPane(fileList);
		sp.setBounds(5, 35, 350, 200);
		frame.getContentPane().add(sp);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addDirList() {
		dirModel = new DefaultListModel();
		dirList = new JList(dirModel);
		dirList.setFont(new Font(Font.SANS_SERIF, 0, 12));
		JScrollPane sp = new JScrollPane(dirList);
		sp.setBounds(445, 35, 360, 200);
		frame.getContentPane().add(sp);
	}
	
	public void setVisable(final boolean vis) {
		frame.setVisible(vis);
	}
	
	/**
	 * Button Commands
	 */
	private class BtnListener implements ActionListener{
		@SuppressWarnings({ "unchecked", "deprecation" })
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if(cmd.equals("FILE_DELETE")) {
				if(!fileList.isSelectionEmpty()) {
					log.Info("Removed: " + fileList.getSelectedIndex());
					fileModel.removeElementAt(fileList.getSelectedIndex());
				}
			}else if(cmd.equals("DIR_DELETE")) {
				if(!dirList.isSelectionEmpty()) {
					log.Info("Removed: " + fileList.getSelectedIndex());
					dirModel.remove(dirList.getLeadSelectionIndex());
				}
			}else if(cmd.equals("OPEN_FILE")) {
				OpenFile opf = new OpenFile();
				File files[] = opf.open(panel);
				
				if(files.length == 0) return;
				log.Custom("Index Length: " + files.length, "DEBUG", true);
				for(int x = 0; x < files.length; x++) {
					if(fileModel.contains(files[x])) {
						log.Error("\"" + files[x] + "\" already exists");
						JOptionPane.showMessageDialog(null, "\"" + files[x] + "\" already exists", "Error", JOptionPane.ERROR_MESSAGE);
						continue;
					}
					
					if(files[x].toString().equals(license.getText())) {
						log.Error("\"" + files[x] + "\" already exists as the license");
						JOptionPane.showMessageDialog(null, "\"" + files[x] + "\" already exists as the license", "Error", JOptionPane.ERROR_MESSAGE);
						continue;
					}
					log.Info("Adding: " + files[x] + " to FileModel");
					fileModel.addElement(files[x]);
				}
				
			}else if(cmd.equals("OPEN_DIR")) {
				OpenDirectory opd = new OpenDirectory();
				opd.open(panel);
				if(opd.getDir().isEmpty()) return;
				
				for(int i = 0; i<dirModel.size(); i++) {
					if(opd.getDir().equals(dirModel.getElementAt(i))) {
						log.Error("\"" + opd.getDir() + "\" already exists");
						JOptionPane.showMessageDialog(null, "\"" + opd.getDir() + "\" already exists", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				log.Info("Adding: " + opd.getDir() + " to DirModel");
				dirModel.addElement(opd.getDir());
			}else if(cmd.equals("OPEN_LICENSE")) {
				OpenOtherFile opl = new OpenOtherFile();
				opl.open(panel);
				if(opl.getFile().isEmpty()) return;
				
				if(fileModel.contains(opl.getFile())) {
					log.Error("\"" + opl.getFile() + "\" already exits in the file list");
					JOptionPane.showMessageDialog(null, "\"" + opl.getFile() + "\" already exits in the file list", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				license.setText(opl.getFile());
			}else if(cmd.equals("MAKE")) {
				if(fileModel.size() == 0) {
					log.Error("You need at least one file to continue");
					JOptionPane.showMessageDialog(null, "You need at least one file to continue", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(mainExe.getText().isEmpty()) {
					log.Error("\"Main Exe\" must be filled\nEx: program.exe or folder/pro.exe from the dir list or file list");
					JOptionPane.showMessageDialog(null, "\"Main Exe\" must be filled\nEx: program.exe or folder/pro.exe from the dir list or file list", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(apName.getText().isEmpty()) {
					log.Error("\"Application Name\" must be filled");
					JOptionPane.showMessageDialog(null, "\"Application Name\" must be filled", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(license.getText().isEmpty()){
					log.Error("\"License\" must be filled");
					JOptionPane.showMessageDialog(null, "\"License\" must be filled", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(installFolderName.getText().isEmpty()){
					log.Error("\"Install Folder Name\" must be filled");
					JOptionPane.showMessageDialog(null, "\"Install Folder Name\" must be filled", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(apDec.getText().isEmpty()){
					log.Warning("Description not given");
					apDec.setText("Description not given");
				}
				
				fileBar.show();
				enableButtons(false);
				make();
			}else if(cmd.equals("APPLICATTIONS")) {
				try {
					Process p = Runtime.getRuntime().exec("explorer " + System.getProperty("user.dir"));
				} catch (IOException e1) {
					log.Error("Could not run program: " + e1.getMessage());
					JOptionPane.showMessageDialog(null, "Error:\n" + e1, "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Makes the application
	 */
	private void make() {
		log.Info("Starting process thread...");
		new Thread(new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				File fi = new File(apName.getText());
				fi.mkdir();
				File filesDir = new File(fi.getPath() + "/Files");
				filesDir.mkdir();
				lblInstalling.show();
				log.Info("Making list...");
				content = makeConf(content);
				log.Info("Copying Files...");
				ManageFiles f = new ManageFiles();
				f.copyFiles(fileModel, apName, fileBar, log);
				fileBar.hide();
				dirBar.show();
				log.Info("Copying Dirs...");
				ManageDirs d = new ManageDirs();
				d.copyDir(dirModel, apName, dirBar, log);
				dirBar.hide();
				lblInstalling.hide();
				if(!System.getProperty("os.name").equals("Linux"))
					content += "main-executable=Files\\" + mainExe.getText();
				else
					content += "main-executable=Files\\" + mainExe.getText();
				log.Info("Writing conf...");
				Utils.writeFile("Conf.txt", content);
				
				if(!System.getProperty("os.name").equals("Linux")) {
					log.Info("Copy File Type: Linux");
					Utils.copyFile(new File(license.getText()), new File(apName.getText() + "/Files/" + Utils.indexOf(license.getText(), '\\')), log);
					Utils.copyFile(new File("Cf32W.dat"), new File(apName.getText() + "/SETUP.exe"), log);
				}else {
					log.Info("Copy File Type: Windows/Other");
					Utils.copyFile(new File(license.getText()), new File(apName.getText() + "/Files/" + Utils.indexOf(license.getText(), '/')), log);
					Utils.copyFile(new File("Cf32L.dat"), new File(apName.getText() + "/SETUP.jar"), log);
				}
					Utils.copyFile(new File("Conf.txt"), new File(apName.getText() + "/Conf.txt"), log);
				
				
				enableButtons(true);
				content = "";
				System.out.println(content);
				JOptionPane.showMessageDialog(null, "Setup is completed!", "Done", JOptionPane.INFORMATION_MESSAGE);
				log.Info("Setup is complete");
			}
		}).start();
		log.Info("Process thread started successfully!");
	}
	
	/**
	 * enable or disable buttons
	 */
	private void enableButtons(final boolean enable) {
		btnFileDelete.setEnabled(enable);
		btnDirDelete.setEnabled(enable);
		btnFileBrowse.setEnabled(enable);
		btnDirBrowse.setEnabled(enable);
		btnLicenseBrowse.setEnabled(enable);
		btnMake.setEnabled(enable);
	}
	
	private String makeConf(String conf) {
		conf += "application-name=" + apName.getText() + "\n";
		conf += "application-details=" + apDec.getText() + "\n";
		conf += "default-install-dir=" + System.getProperty("user.home") + "\n";
		
		if(!System.getProperty("os.name").equals("Linux"))
			conf += "license-path=" + "Files\\" + Utils.indexOf(license.getText(), '\\') + "\n";
		else
			conf += "license-path=" + "Files/" + Utils.indexOf(license.getText(), '/') + "\n";
		
		conf += "install-folder-name=" + installFolderName.getText() + "\n";
		conf += "## FILES ##" + "\n";
		return conf;
	}
	
	/**
	 * Saves the fields
	 */
	private void saveConfiguration() {
		log.Info("Saving...");
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
			if(new File(dir).exists()) {
				JOptionPane.showMessageDialog(null, "File already exists", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			PrintWriter writer = new PrintWriter(new File(dir + ".dat"), "UTF-8");
			String linesToString = "";
			for(int i = 0; i<lines.size(); i++) {
				linesToString += lines.get(i) + "\n";
			}
			writer.write(linesToString);
			writer.close();
			log.Info("Configuration saved!");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
			log.Error("Could not save: " + e.getMessage());
		}
		
	}
	
	/**
	 * Fills in fields from save file
	 */
	@SuppressWarnings("unchecked")
	private void openConfiguration() {
		log.Info("Opening file...");
		OpenOtherFile opf = new OpenOtherFile();
		String fileName = null;
		try {
			opf.open(panel);
			fileName = opf.getFile();
		}catch(Exception e) {
			log.Error("Could not set file name\n" + e);
			e.printStackTrace();
			return;
		}
		if(opf.getFile().isEmpty()) return;
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


