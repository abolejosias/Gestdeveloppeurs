/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmenu.toolkits.partitionInformation;

import java.io.File;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author user
 */
public class RootSystem {

    public RootSystem() {
    }

    public String getRootSytemInfo() {
        String PathRootSystem = "";
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] roots = fsv.getRoots();
        for (int i = 0; i < roots.length; i++) {
            System.out.println("Root: " + roots[i].getParent());
            PathRootSystem = roots[i].getParent();
        }
        return PathRootSystem;
    }

    public void ViewAllPartition() {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] roots = fsv.getRoots();
        System.out.println("File system roots returned by File.listRoots():");
        File[] f = File.listRoots();
        for (int i = 0; i < f.length; i++) {
            System.out.println("Drive: " + f[i] + " space" + f[i].getTotalSpace());

        }
    }
}
