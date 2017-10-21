package com.JudgeGlass.InstallBuilder.updater;

import com.JudgeGlass.InstallBuilder.tools.Utils;
import com.JudgeGlass.InstallBuilder.windows.ExtractWindow;
import com.JudgeGlass.InstallBuilder.tools.Logger;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.awt.*;
import java.io.BufferedOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Downloader {
    public void GetFile(String Url, String filename, String labelText, String windowTitle, Logger log) {
        JLabel process = new JLabel(labelText + "");
        final JProgressBar jProgressBar = new JProgressBar();
        jProgressBar.setStringPainted(true);
        jProgressBar.setMaximum(100000);
        JFrame frame = new JFrame();
        frame.setTitle(windowTitle);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        jProgressBar.setPreferredSize(new Dimension(280, 30));
        frame.add(process);
        frame.add(jProgressBar);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(360, 120);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Runnable updatethread = new Runnable() {
            public void run() {
                try {

                    URL url = new URL(Url);
                    HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
                    long completeFileSize = httpConnection.getContentLength();

                    java.io.BufferedInputStream in = new java.io.BufferedInputStream(httpConnection.getInputStream());
                    java.io.FileOutputStream fos = new java.io.FileOutputStream(
                            filename);
                    java.io.BufferedOutputStream bout = new BufferedOutputStream(
                            fos, 1024);
                    byte[] data = new byte[1024];
                    long downloadedFileSize = 0;
                    int x = 0;
                    while ((x = in.read(data, 0, 1024)) >= 0) {
                        downloadedFileSize += x;

                        // calculate progress
                        final int currentProgress = (int) ((((double) downloadedFileSize) / ((double) completeFileSize)) * 100000d);
                        process.setText(labelText + (downloadedFileSize / 1024000) + "MB / " + (completeFileSize / 1024000) + "MB");
                        // update progress bar
                        SwingUtilities.invokeLater(new Runnable() {

                            @Override
                            public void run() {
                                jProgressBar.setValue(currentProgress);
                            }
                        });

                        bout.write(data, 0, x);
                    }
                    bout.close();
                    in.close();
                    frame.dispose();

                    System.out.println(Utils.indexOf(getWebVersion(), '/'));
                    new ExtractWindow(Utils.indexOf(getWebVersion(), '/'), log);
                } catch (FileNotFoundException e) {
                } catch (IOException e) {
                    e.printStackTrace();
                    log.Error(e.getMessage());
                }
            }
        };
        new Thread(updatethread).

                start();

    }

    private String getWebVersion() {
        return Utils.readLine("version.dat", 0);
    }
}
