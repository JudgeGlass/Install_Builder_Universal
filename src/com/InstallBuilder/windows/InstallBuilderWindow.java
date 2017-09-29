package com.InstallBuilder.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.InstallBuilder.listeners.MenuActionListener;
import com.InstallBuilder.tools.ManageDirs;
import com.InstallBuilder.tools.ManageFiles;
import com.InstallBuilder.tools.OpenDirectory;
import com.InstallBuilder.tools.OpenFile;
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
		
		lblInstallFolderName = new JLabel("Install Foler Name:");
		lblInstallFolderName.setBounds(315, 390, 300, 15);
		frame.getContentPane().add(lblInstallFolderName);
		
		btnMake = new JButton("Make");
		btnMake.setBounds(718, 405, 75, 20);
		btnMake.setActionCommand("MAKE");
		btnMake.addActionListener(new BtnListener());
		frame.getContentPane().add(btnMake);
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
		fileMenu.add(exitFile);
		
		helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		JMenuItem aboutHelp = new JMenuItem("About");
		aboutHelp.setActionCommand("ABOUT");
		aboutHelp.addActionListener(new MenuActionListener());
		helpMenu.add(aboutHelp);
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
				btnMake.setEnabled(false);
				File fi = new File(apName.getText());
				fi.mkdir();
				
				content = makeConf(content);
				
				ManageFiles f = new ManageFiles();
				f.copyFiles(fileModel, apName);
				ManageDirs d = new ManageDirs();
				d.copyDir(dirModel, apName);
				content += "main-executable=" + mainExe.getText();
				Utils.writeFile("Conf.txt", content);
				
				Utils.copyFile(new File("Conf.txt"), new File(apName.getText() + "/Conf.txt"));
				Utils.copyFile(new File("SETUP.exe"), new File(apName.getText() + "/SETUP.exe"));
				btnMake.setEnabled(true);
				content = "";
				System.out.println(content);
			}
		}
	}
	
	Runnable updateThread = new Runnable() {
		public void run() {
			ProgressWindow p = new ProgressWindow();
		}
	};
	
	private String makeConf(String conf) {
		conf += "application-name=" + apName.getText() + "\n";
		conf += "application-details=" + apDec.getText() + "\n";
		conf += "default-install-dir=C:\\Program Files\n";
		conf += "license-path=" + license.getText() + "\n";
		conf += "install-folder-name=" + installFolderName.getText() + "\n";
		conf += "## FILES ##" + "\n";
		return conf;
	}
}
