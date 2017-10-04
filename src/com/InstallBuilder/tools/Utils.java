package com.InstallBuilder.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class Utils {
	public static String indexOf(String txt, char ch) {
		return txt.substring(txt.lastIndexOf(ch) + 1);
	}
	
	public static void copyFile(File src, File dec, Logger log) {
		try {
			FileUtils.copyFile(src, dec);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error:\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			log.Error("Could not copy \"" + src + "\"\n" + e);
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
	
	public static String readLine(String fileName, int lineNumber) {
		String line;
		try {
			line = Files.readAllLines(Paths.get(fileName)).get(lineNumber);
		} catch (IOException e) {
			e.printStackTrace();
			return "Error";
		}
		return line;
	}
	
	public static void writeFile(String fileName, String txt) {
		try {
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			writer.print(txt);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			JOptionPane.showMessageDialog(null, "Error:\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
