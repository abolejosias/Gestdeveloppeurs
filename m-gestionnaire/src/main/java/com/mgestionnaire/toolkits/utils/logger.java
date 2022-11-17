/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgestionnaire.toolkits.utils;

import com.mgestionnaire.toolkits.filesmanagers.FilesType.TxtFiles;
import com.mgestionnaire.toolkits.filesmanagers.FilesType.files;
import com.mgestionnaire.toolkits.log.Category;
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
