package org.lining.hssf;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * date 2018-02-27
 *
 * @author lining1
 * @version 1.0.0
 */
public class ReadTxt {

    private static final String[]  DATA = new String[] {"TL-","TENDA","HGE318",};

    public static void readTxtFile(String filePath, String outputFilename) {
        try {
            File file = new File(filePath);
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet s = wb.createSheet();
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file));//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                int rownum = 0;
                while ((lineTxt = bufferedReader.readLine()) != null) {

                    if (!lineTxt.startsWith("\t")) {
                        String[] split = lineTxt.split("\t");
                        System.out.println("网关ID：" + split[0] + "网关mac：" + split[1]);
                        System.out.println("下挂设备列表:");
                        HSSFRow r = s.createRow(rownum);
                        HSSFCell c1 = r.createCell(0);
                        HSSFCell c2 = r.createCell(1);
                        c1.setCellValue(split[0]);
                        c2.setCellValue(split[1]);
                        rownum++;
                    } else {
                        String[] split = lineTxt.split("\t");
                        System.out.println(split[1] + " " + split[2]);
                        HSSFRow r = s.createRow(rownum);
                        HSSFCell c1 = r.createCell(2);
                        HSSFCell c2 = r.createCell(3);
                        c1.setCellValue(split[1]);
                        c2.setCellValue(split[2]);
                        rownum++;
                    }
                }
                FileOutputStream out = new FileOutputStream(outputFilename);
                wb.write(out);
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }

    public static void readTxtFile2(String filePath, String outputFilename) {
        try {
            File file = new File(filePath);
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet s = wb.createSheet();
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file));//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                int rownum = 0;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    HSSFRow r = s.createRow(rownum);
                    if (!lineTxt.startsWith("\t")) {
                        String[] split = lineTxt.split("\t");
                        System.out.println("网关ID：" + split[0] + "网关mac：" + split[1]);
                        System.out.println("下挂设备列表:");
                        HSSFCell c1 = r.createCell(0);
                        HSSFCell c2 = r.createCell(1);
                        c1.setCellValue(split[0]);
                        c2.setCellValue(split[1]);
                        rownum++;
                    } else {
                        String[] split = lineTxt.split("\t");
                        System.out.println(split[1] + " " + split[2]);
                        HSSFCell c1 = r.createCell(2);
                        HSSFCell c2 = r.createCell(3);
                        c1.setCellValue(split[1]);
                        c2.setCellValue(split[2]);
                    }
                }
                FileOutputStream out = new FileOutputStream(outputFilename);
                wb.write(out);
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }

    public static void main(String argv[]){
        String filePath = "D:\\统计数据\\subDeviceData.txt";
        String outputFilename = "D:\\统计数据\\subDeviceData.xls";
        readTxtFile2(filePath, outputFilename);
    }
}
