package org.lining.hssf;

import org.apache.poi.hssf.usermodel.*;

import java.io.*;

/**
 * description:
 * date 2018-03-01
 *
 * @author lining1
 * @version 1.0.0
 */
public class ExcelUtils {

    public static void writeExcel(String filePath) {
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            //sheet
            HSSFSheet s = wb.createSheet();
            //cell
            HSSFCellStyle cs = wb.createCellStyle();

            HSSFFont f = wb.createFont();
            f.setColor((short) 0xA);
            cs.setFont(f);

            wb.setSheetName(0, "HSSF Test");

            //row start from 0
            int rownum;
            for (rownum = 0; rownum < 300; rownum++) {

                //cloumn start from 0
                HSSFRow r = s.createRow(rownum);
                for (int cellnum = 0; cellnum < 2; cellnum ++) {
                    HSSFCell c = r.createCell(cellnum);
                    c.setCellValue(1);
                }
            }
            FileOutputStream out = new FileOutputStream(filePath);
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readExcel(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            HSSFWorkbook wb = new HSSFWorkbook(fis);
            HSSFSheet sheet = wb.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();
            for (int r = 1; r < rows; r++) {
                HSSFRow row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                HSSFCell cell0 = row.getCell(0);

                //value = cell.getStringCellValue();
                String value0 = getStringFromHssf(cell0);
                System.out.println(value0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getStringFromHssf(HSSFCell cell) {
        String value = null;
        if(cell != null) {
            switch (cell.getCellTypeEnum()) {
                case STRING:
                    value = cell.getStringCellValue();
                    break;

                case NUMERIC:
                    value = String.valueOf((long) cell.getNumericCellValue());
                    break;

                default:
                    value = cell.getCellTypeEnum() + "";
            }
        }
        return value;
    }


    public static void readTxt(String path) {
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(path));
            BufferedReader br = new BufferedReader(isr);
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                System.out.println(lineTxt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void writeTxt(String path) {
        try {
            File writename = new File(path);
            writename.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write(path);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePath = "D:\\1aaaaaaaaaaa中移物联网\\hssf\\src\\main\\resources\\output.xls";
        writeExcel(filePath);
        readExcel(filePath);
    }
}
