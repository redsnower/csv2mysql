package com.cmbc.utils;



import com.mysql.cj.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by yangchuanhuan on 2018/10/25.
 */
public class ImportCVSUtil {
    public static void importToMysql(){
        String properties = PropertyUtil.getProperties("application.properties", "application.name");
        if(!StringUtils.isNullOrEmpty(properties)){
            String[] split = properties.split(",");
            for (String application : split) {
                String applicationProperty = String.format("%s.properties", application.trim());
                String tableName=PropertyUtil.getProperties(applicationProperty, "table");
                String csvFile=PropertyUtil.getProperties(applicationProperty, "csv");
                String fieldsStr=PropertyUtil.getProperties(applicationProperty, "fields");
                String delimiter=PropertyUtil.getProperties(applicationProperty, "delimiter");
                String fieldsindex=PropertyUtil.getProperties(applicationProperty, "fieldsindex");
                int[] ints = StirngToint(fieldsindex.split(","));
                String[] fields = fieldsStr.split(",");
                List resultList = readCSV(csvFile, delimiter.trim(),ints, fields);
                DbKit.batchMysql(tableName, fields , resultList);
            }
            
        }
    }

     private static int[] StirngToint(String [] strs){

        int aa[]=new int[strs.length];
         for (int i = 0; i <strs.length ; i++) {
             aa[i]=Integer.valueOf(strs[i]);
         }
         return aa;
     }
    public static List<Map> readCSV(String csvFileName, String delimiter,int[] fieldsindex, String[] fields){
        try {
            URI uri = new URI(ImportCVSUtil.class.getClassLoader().getResource("csv"+File.separator+csvFileName).toString());
            Path path = Paths.get(uri);
            List result = Files.readAllLines(path).stream().map(s ->{
                String[] results = s.split(delimiter, -1);
                Map map = new HashMap();
                System.out.println(s);
                for (int i = 0; i < fields.length; i++) {
                    map.put(fields[i], results[fieldsindex[i]]);
                }
                return map;
            })
                    .collect(Collectors.toList());
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
