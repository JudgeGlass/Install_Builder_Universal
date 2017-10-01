package com.InstallBuilder.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

public class Utils {
	public static String indexOf(String txt, char ch) {
		return txt.substring(txt.lastIndexOf(ch) + 1);
	}
	
	public static void copyFile(File src, File dec) {
		try {
			FileUtils.copyFile(src, dec);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error:\n" + e);
			e.printStackTrace();
		}
	}
	
	public String getLicenseFile() {
		String cont = "";
		InputStream input = getClass().getResourceAsStream("/com/InstallBuilder/resources/LICENSE.txt");
		try {
			cont = IOUtils.toString(input, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cont;
	}
	
	public static void writeFile(String fileName, String txt) {
		try {
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			writer.print(txt);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			JOptionPane.showMessageDialog(null, "Error:\n" + e);
			e.printStackTrace();
		}
	}
	
	
}
