package com.JudgeGlass.InstallBuilder.tools;

/*
 * Copyright 2012 Hunter Wilcox

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * 
 * */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	Date date;
	private static String MainLog = "";
	private static String CustomStream = "";
	
	private String filePath;
	public Logger(String filePath) {
		this.filePath = filePath;
	}
	
	public void Error(String message) {
		date = new Date();
		MainLog += "[Error: "+ dateFormat.format(date)+"] " + message + "\n";
		System.out.println("[Error: "+ dateFormat.format(date)+"] " + message);
		try {
			WriteMainLog(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Info(String message) {
		date = new Date();
		MainLog += "[Info: "+ dateFormat.format(date)+"] " + message + "\n";
		System.out.println("[Info: "+ dateFormat.format(date)+"] " + message);
		try {
			WriteMainLog(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Warning(String message) {
		date = new Date();
		MainLog += "[WARN: "+ dateFormat.format(date)+"] " + message + "\n";
		System.out.println("[WARN: "+ dateFormat.format(date)+"] " + message);
		try {
			WriteMainLog(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Custom(String message, String type, boolean includeDate) {
		date = new Date();
		if(includeDate) {
			MainLog += String.format("[%s: %s] %s\n", type, dateFormat.format(date), message);
		}else {
			MainLog += String.format("[%s] %s\n", type, message);
		}
		try {
			WriteMainLog(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void WriteMainLog(File file) throws IOException {
		BufferedWriter log = new BufferedWriter(new FileWriter(file.getName()));
		log.write(getMainLog());
		log.close();
	}
	
	public void WriteSeperatedStream(File file) throws IOException {
		BufferedWriter Seperatelog = new BufferedWriter(new FileWriter(file.getName()));
		Seperatelog.write(MainLog);
		Seperatelog.close();
	}
	

	public boolean Exists(File file) {
		try {
			if(file.exists())
				return true;
			else
				return false;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String getMainLog() {
		return MainLog;
	}
	
	public String getCustomStream() {
		return CustomStream;
	}
	
	public String getVersion() {
		return "Logger v0.0.3";
	}
}
