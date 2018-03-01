package org.lining.hssf;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

/**
 * description:
 * date 2018-03-01
 *
 * @author lining1
 * @version 1.0.0
 */
public class CSVUtils {

    public static void main(String[] args) throws Exception {
        String filePath = "D:\\1aaaaaaaaaaa中移物联网\\hssf\\src\\main\\resources\\write.csv";
        writeCsv(filePath);
    }

    public static void readCsv(String filePath) throws Exception {
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        CSVReader csvReader = new CSVReader(fileReader);
        String[] strs = csvReader.readNext();
        if(strs != null && strs.length > 0){
            for(String str : strs) {
                if(null != str && !str.equals("")) {
                    System.out.print(str + " , ");
                }

            }

            System.out.println("\n---------------");
        }
/*        List<String[]> list = csvReader.readAll();
        for(String[] ss : list){
            for(String s : ss) {
                if(null != s && !s.equals("")) {
                    System.out.print(s + " , ");
                }
            }
            System.out.println();
        }*/
        csvReader.close();
    }

    public static void writeCsv(String filePath) throws Exception {
        File file = new File(filePath);
        Writer writer = new FileWriter(file);
        CSVWriter csvWriter = new CSVWriter(writer);
        String[] strs = {"abc" , "abc" , "abc"};
        csvWriter.writeNext(strs);
        csvWriter.close();
    }
}
