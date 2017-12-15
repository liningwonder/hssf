package org.lining.hssf;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


/**
 * description:
 * date 2017/12/12
 *
 * @author lining1
 * @version 1.0.0
 */
public class HSSFReadData {

    private static final String fileName = "d:\\FTP\\new2.xls";
    private static List<People> DATALIST = new LinkedList<>();
    private static int ROWS = 0;
    private static int COLUMNS = 0;
    private static int[] RAND = null;

    static {
        init();
        RAND = randomCommon(0, ROWS - 2, 6);
    }

    /**
     * read hssf file
     * @param filename
     * @return
     * @throws IOException
     */
    private static HSSFWorkbook readFile(String filename) throws IOException {
        FileInputStream fis = new FileInputStream(filename);
        try {
            return new HSSFWorkbook(fis);
        } finally {
            fis.close();
        }
    }

    public static void init() {
        try {
            HSSFWorkbook wb = HSSFReadData.readFile(fileName);
            HSSFSheet sheet = wb.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();
            int column = sheet.getRow(1).getLastCellNum();
            ROWS = rows;
            COLUMNS = column;
            for (int r = 1; r < rows; r++) {
                HSSFRow row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                HSSFCell cell0 = row.getCell(0);
                HSSFCell cell1 = row.getCell(1);
                String value0 = getStringFromHssf(cell0);
                String value1 = getStringFromHssf(cell1);
                People people = new People(value0, value1);
                DATALIST.add(people);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("初始化Excel数据异常");
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
                    value = "Error value of type " + cell.getCellTypeEnum() + " , Please convert it as String.";
            }
            System.out.println("CELL col=" + cell.getColumnIndex() + " VALUE=" + value);
        }
        return value;
    }

    public static int[] randomCommon(int min, int max, int n){
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while(count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if(num == result[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result[count] = num;
                count++;
            }
        }
        return result;
    }

    public static String selectOne () {
        People people = DATALIST.get(RAND[0]);
        return people.toString();
    }

    public static String selectTwo () {
        People people1 = DATALIST.get(RAND[1]);
        People people2 = DATALIST.get(RAND[2]);
        return people1.toString() + ";  " + people2.toString();
    }

    public static String selectThree () {
        People people1 = DATALIST.get(RAND[3]);
        People people2 = DATALIST.get(RAND[4]);
        People people3 = DATALIST.get(RAND[5]);
        return people1.toString() + ";  " + people2.toString() + ";  " + people3.toString();
    }




/*    public static void main(String[] args) {
*//*        People people = DATALIST.get(0);
        System.out.println(RAND.toString());
        System.out.println(DATALIST.get(RAND[0]));*//*

        System.out.println(selectOne());
        System.out.println(selectTwo());
        System.out.println(selectThree());
    }*/
}
