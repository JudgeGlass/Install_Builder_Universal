package com.InstallBuilder.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.FileUtils;

public class Utils {
	public static String indexOf(String txt, char ch) {
		return txt.substring(txt.lastIndexOf(ch) + 1);
	}
	
	public static void copyFile(File src, File dec) {
		try {
			FileUtils.copyFile(src, dec);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeFile(String fileName, String txt) {
		try {
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
			writer.print(txt);
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.err.println("Could not write conf file!");
		}
	}
}
