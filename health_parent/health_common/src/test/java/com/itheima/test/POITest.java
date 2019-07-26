package com.itheima.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class POITest {
    @Test
    public void demo() throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook("D:/health/hello.xlsx");
        XSSFSheet sheet1 = workbook.getSheetAt(0);
        //循环遍历每一行
        for (Row row : sheet1) {
            for (Cell cell : row) {
                System.out.println(cell.getStringCellValue());
            }
        }
        workbook.close();
    }

    @Test
    public void demo2() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook("D:/health/hello.xlsx");
        XSSFSheet sheet1 = workbook.getSheetAt(0);
        int num = sheet1.getLastRowNum();
        for (int i = 0; i <= num; i++) {
            XSSFRow row = sheet1.getRow(i);
            short cellNum = row.getLastCellNum();
            for (int j = 0; j <cellNum ; j++) {
                System.out.println(row.getCell(j).getStringCellValue());
            }
        }
        workbook.close();
    }

    @Test
    public void demo3() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("序号");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("年龄");

        for (int i = 1; i < 20; i++) {
            XSSFRow row1 = sheet.createRow(i);
            row1.createCell(0).setCellValue(i);
            row1.createCell(1).setCellValue("admin"+i);
            row1.createCell(2).setCellValue(18+i);
        }
        
        OutputStream outputStream = new FileOutputStream("d:/传智播客.xlsx");
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workbook.close();

    }
}
