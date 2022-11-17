/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmenu.toolkits.filesmanagers.FilesType;

import com.mmenu.toolkits.commonparameter;
import com.mmenu.toolkits.enumExtentionFiles;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;



/**
 *
 * @author User
 */
public class XlsFiles extends files implements Ifile {

    public XlsFiles() {
        this.setXtention(".xls");
    }

    public void InitFiles() {
        this.setExtention(enumExtentionFiles.TEXTE);

    }

    public List<String> LoadDataToFiles() {
        List<String> Odata = new ArrayList<String>();
        String fichier = this.getPath_input();
        //lecture du fichier texte
        try {
            Workbook workbook = Workbook.getWorkbook(new File(fichier));
            Sheet sheet = workbook.getSheet(0);
            int sizeOfsheet = sheet.getRows();
            for (int i = 0; i < sizeOfsheet; i++) {
                /* debut Chargement des cellule */
                Cell a1 = sheet.getCell(0, 0);
                Cell b2 = sheet.getCell(1, 1);
                Cell c2 = sheet.getCell(2, 1);
                /* fin Chargement des cellule */
                String stringa1 = a1.getContents();
                String stringb2 = b2.getContents();
                String stringc2 = c2.getContents();
                //  Odata.add(ligne);
            }
            workbook.close();
            this.setMessage(commonparameter.PROCESS_SUCCESS);
        } catch (Exception e) {
            this.setMessage(commonparameter.PROCESS_FAILED);
            System.out.println(e.toString());
        }
        return Odata;
    }

      public List<String> LoadDataToFiles(String str_path_file) {
        List<String> Odata = new ArrayList<String>();
        String fichier = str_path_file;
        //lecture du fichier texte
        try {
            Workbook workbook = Workbook.getWorkbook(new File(fichier));
            Sheet sheet = workbook.getSheet(0);
            int sizeOfsheet = sheet.getRows();
            for (int i = 0; i < sizeOfsheet; i++) {
                /* debut Chargement des cellule */
                Cell a1 = sheet.getCell(0, 0);
                Cell b2 = sheet.getCell(1, 1);
                Cell c2 = sheet.getCell(2, 1);
                /* fin Chargement des cellule */
                String stringa1 = a1.getContents();
                String stringb2 = b2.getContents();
                String stringc2 = c2.getContents();
                //  Odata.add(ligne);
            }
            workbook.close();
            this.setMessage(commonparameter.PROCESS_SUCCESS);
        } catch (Exception e) {
            this.setMessage(commonparameter.PROCESS_FAILED);
            System.out.println(e.toString());
        }
        return Odata;
    }

    public List<Item> LoadDataItemToFiles() {
        List<Item> Odata = new ArrayList<Item>();
        String fichier = this.getPath_input();
        //lecture du fichier texte
        try {
            Workbook workbook = Workbook.getWorkbook(new File(fichier));
            Sheet sheet = workbook.getSheet(0);
            //int sizeOfsheet = sheet.getRows();
           // for (int i = 0; i < sizeOfsheet; i++) {
                Item OItem = new Item();
                /* debut Chargement des cellule */
                Cell a1 = sheet.getCell(0, 1);
               // Cell a2 = sheet.getCell(2, 9);
                //Cell a3 = sheet.getCell(3, 9);
               // Cell a4 = sheet.getCell(4, 9);
               // Cell a5 = sheet.getCell(5, 9);
               // Cell a6 = sheet.getCell(6, 9);
               // Cell a7 = sheet.getCell(7, 9);
               // Cell a8 = sheet.getCell(8, 9);
                //Cell a9 = sheet.getCell(9, 9);
                //Cell a10 = sheet.getCell(10, 9);
               // Cell a11 = sheet.getCell(11, 9);
               // Cell a12 = sheet.getCell(12, 9);
               // Cell a13 = sheet.getCell(13, 9);



                /* fin Chargement des cellule */
                OItem.setData1(a1.getContents()) ;
               // OItem.setData2(a2.getContents()) ;
               // OItem.setData3(a3.getContents()) ;
               // OItem.setData4(a4.getContents()) ;
               // OItem.setData5(a5.getContents()) ;
               // OItem.setData6(a6.getContents()) ;
               // OItem.setData7(a7.getContents()) ;
               // OItem.setData8(a8.getContents()) ;
               // OItem.setData9(a9.getContents()) ;
               // OItem.setData10(a10.getContents()) ;
               // OItem.setData11(a11.getContents()) ;
               // OItem.setData12(a12.getContents()) ;
               // OItem.setData13(a13.getContents()) ;

                System.out.println(OItem.getData1());

                 Odata.add(OItem);
        //    }
            workbook.close();
            this.setMessage(commonparameter.PROCESS_SUCCESS);
        } catch (Exception e) {
            this.setMessage(commonparameter.PROCESS_FAILED);
            System.out.println(e.toString());
        }
        return Odata;
    }

    public void LoadFile(String path) {
        String fileName = "";   // The file name the user entered.
        fileName = path;
        int dotPos = fileName.lastIndexOf(".");
        this.setPath_input(path);
        this.setXtention(fileName.substring(dotPos));
    }

    public void LoadRessource() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void SaveToFile(List<String> Collection_data) {
        try {
            FileWriter fw = new FileWriter(this.getPath_outut());
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter fichierSortie = new PrintWriter(bw);

            for (int i = 0; i < Collection_data.size(); i++) {
                fichierSortie.println(Collection_data.get(i).toString());
            }
            fichierSortie.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void SaveToFile(String data) {
        try {
            FileWriter fw = new FileWriter(this.getPath_outut() + enumExtentionFiles.TEXTE);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter fichierSortie = new PrintWriter(bw);

            fichierSortie.println(data);

            fichierSortie.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public PrintWriter InitSaveToFile() {
        PrintWriter fichierSortie = null;
        try {
            FileWriter fw = new FileWriter(this.getPath_outut() + this.getXtention());
            BufferedWriter bw = new BufferedWriter(fw);
            fichierSortie = new PrintWriter(bw);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return fichierSortie;
    }

    public void SaveToFile(PrintWriter fichierSortie, String data) {
        try {

            fichierSortie.println(data);


        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void Close(PrintWriter fichierSortie) {
        try {

            fichierSortie.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void Delete() {
        File f1 = new File(this.getPath_input());
        boolean success = f1.delete();
        if (!success) {
            System.out.println("Deletion failed.");
            //  System.exit(0);
        } else {
            System.out.println("File deleted.");
        }
    }

    public void MoveTo(String directoryname) {
        // File (or directory) to be moved
        File file = new File(this.getPath_input());

        // Destination directory
        File dir = new File(directoryname);

        // Move file to new directory
        boolean success = file.renameTo(new File(dir, file.getName()));
        if (!success) {
            // File was not successfully moved
        }

    }





    
}
