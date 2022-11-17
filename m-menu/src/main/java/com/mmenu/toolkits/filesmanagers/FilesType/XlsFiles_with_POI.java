/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmenu.toolkits.filesmanagers.FilesType;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;



/**
 *
 * @author user
 */
public class XlsFiles_with_POI {

    private String FilePath;

    public XlsFiles_with_POI(String FilePath) {
        this.FilePath = FilePath;
    }

    public List LoadDataToFiles_with_POI() {

        List OListdata = new ArrayList();
        FileInputStream OFileInputStream = null;
        try {
            //creation de dun flux de fichier sortant à partir du chemin du fichier
            OFileInputStream = new FileInputStream(this.FilePath);

            //creation d'une instance de HSSFWorkbook classe java qui implemente POIDOcument à partir du flux de fichier
            HSSFWorkbook workbook = new HSSFWorkbook(OFileInputStream);
            //récupération du nombre de feuille du fichier excel
            int numberOfSheets = workbook.getNumberOfSheets();

            for (int i = 0; i < numberOfSheets; i++) {

                //parcours pour obtenir les lignes de chaque feuille
                HSSFSheet sheet = workbook.getSheetAt(i);
                Iterator rows = sheet.rowIterator();
                while (rows.hasNext()) {
                    HSSFRow row = (HSSFRow) rows.next();

                    if (row.getRowNum() == 0) {
                        continue; //just skip the rows if row number is 0 or 1
                    }
                    Iterator cells = row.cellIterator();

                    List<Cell> data = new ArrayList();

                    while (cells.hasNext()) {
                        Cell cell = (Cell) cells.next();
                        // System.out.println("cell------->"+cell);
                        data.add(cell);
                    }
                    OListdata.add(data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return OListdata;
    }

    public List<String> getAndInsertDataForFileExtract_with_POI(List Listdata) {

        List<String> lst = new ArrayList<String>();

        for (int i = 0; i < Listdata.size(); i++) {
            List<Cell> list = (List) Listdata.get(i);
            String datacell = "";
            for (int j = 0; j < list.size(); j++) {
                Cell cell = list.get(j);
                Cell cells = list.get(j);
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    datacell = datacell + "" + (int) cell.getNumericCellValue() + ";";
                    //System.out.print(((int) cell.getNumericCellValue())+" --  ");
                } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    //System.out.print(cell.getRichStringCellValue()+" --  ");
                    datacell = datacell + "" + cell.getRichStringCellValue() + ";";
                } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                    //  System.out.print(cell.getBooleanCellValue()+" --  ");
                    datacell = datacell + "" + cell.getBooleanCellValue() + ";";
                }
            }
            //System.out.println("datacell------->"+datacell);
            lst.add(datacell);

        }
        return lst;
    }

  

}
