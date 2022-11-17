/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmenu.toolkits.utils;

import com.mmenu.toolkits.filesmanagers.FilesType.TxtFiles;
import com.mmenu.toolkits.filesmanagers.FilesType.files;
import com.mmenu.toolkits.log.Category;
import java.io.PrintWriter;


/**
 *
 * @author Administrator
 */
public class logger extends files {

    TxtFiles OTxtFiles = new TxtFiles();
    PrintWriter fichierSortie = null;
    public Category OCategory = new Category();
    public Category oCategory = new Category();
    date key = new date();

    public logger() { 
    }


   

}
