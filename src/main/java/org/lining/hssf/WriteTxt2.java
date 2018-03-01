package org.lining.hssf;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * description:
 * date 2018-02-27
 *
 * @author lining1
 * @version 1.0.0
 */
public class WriteTxt2 {

    public static Map readTxtFile2(String filePath) {
        Map<String, Set<String>> ss = new HashMap<>();
        try {
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file));//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                String key = "";
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    String[] split = lineTxt.split("\t");
                    if (!lineTxt.startsWith("\t")) {
                        key = split[0] + "\t" + split[1];
                        ss.put(key, new HashSet<>());
                    } else {
                        ss.get(key).add(split[1] + "\t" + split[2]);
                    }

                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return ss;
    }

    public static void main(String argv[]) throws Exception{
        String filePath = "D:\\统计数据\\subDeviceData.txt";
        String outputFilename = "D:\\统计数据\\subDeviceData.xls";
        Map<String, Set<String>> map = readTxtFile2(filePath);


        String pathname = "D:\\统计数据\\haha.txt";
        String pathname2 = "D:\\统计数据\\quchong.txt";
        File writename = new File(pathname);
        writename.createNewFile();

        File writename2 = new File(pathname2);
        writename2.createNewFile();

        BufferedWriter out = new BufferedWriter(new FileWriter(writename));
        BufferedWriter out2 = new BufferedWriter(new FileWriter(writename2));

        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            out.write(entry.getKey() + "\t" + entry.getValue());
            out.write("\n");

            boolean b = false;
            String kk = "";
            for (String s : entry.getValue()) {
                kk += s;
            }

            String lkk = kk.toLowerCase();
            if (lkk.contains("TENDA".toLowerCase()) || lkk.contains("TL-".toLowerCase())
                    || lkk.contains("HGE318".toLowerCase()) || lkk.contains("TP-LINK".toLowerCase())
                    || lkk.contains("MERCURY".toLowerCase()) || lkk.contains("FAST".toLowerCase())) {
                b = true;
            }
            if (b) {
                out2.write(entry.getKey() + "\t" + entry.getValue());
                out2.write("\n");
            }
        }
        out.flush();
        out2.flush();
        out.close();
        out2.close();
    }
}
