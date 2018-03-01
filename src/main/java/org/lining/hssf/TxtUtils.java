package org.lining.hssf;

import java.io.*;

/**
 * description:
 * date 2018-03-01
 *
 * @author lining1
 * @version 1.0.0
 */
public class TxtUtils {

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
}
