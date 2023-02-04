package com.example.twodatabases;

import com.example.CsvOperations.CsvReader;
import com.example.CsvOperations.Mapping;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Service
public class DatabaseService {
    public void processResults(List<Map<String, Object>> mysqlResult, List<Map<String, Object>> postgresResult) throws CsvValidationException, IOException, ParseException {

        // Get the first row from the MySQL result and convert the set to an array of
        //strings to store the headers. For instance (id,name,role etc)
        Map<String, Object> mysql_firstRow = mysqlResult.get(0);
        Set<String> mysql_set = mysql_firstRow.keySet();
        String[] mysql_header = mysql_set.toArray(new String[mysql_set.size()]);

        //Do the same for other database

        Map<String, Object> postgresql_firstRow = postgresResult.get(0);
        Set<String> postgresql_set = postgresql_firstRow.keySet();
        String[] postgresql_header = postgresql_set.toArray(new String[postgresql_set.size()]);

        List<String[]>data1 = new ArrayList<>();
        List<String[]>data2 = new ArrayList<>();


        data1.add(mysql_header);

        for (Map<String, Object> map : mysqlResult) {
            String[] array = new String[map.size()];
            int i = 0;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                array[i++] = String.valueOf(entry.getValue());
            }
            data1.add(array);
        }

        System.out.println("\n\n-------------------------I am data from MySQL-------------------------");
        System.out.println("-------------------------I am data from MySQL-------------------------\n\n");

        for (int i = 0; i < data1.size(); i++) {
            String[] arr = data1.get(i);
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[j] + " ");
            }
            System.out.println();
        }

        data2.add(postgresql_header);


        for (Map<String, Object> map : postgresResult) {
            String[] array = new String[map.size()];
            int i = 0;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                array[i++] = entry.getValue().toString();
            }
            data2.add(array);
        }

        System.out.println("\n\n-------------------------I am data from PostgreSQL-------------------------");
        System.out.println("-------------------------I am data from PostgreSQL-------------------------\n\n");

        for (int i = 0; i < data2.size(); i++) {
            String[] arr = data2.get(i);
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[j] + " ");
            }
            System.out.println();
        }

        String a = "F:\\SpringBoot\\twodatabases\\twodatabases\\data\\MapFile.csv";
        CsvReader csvReader = new CsvReader();
        List<Mapping> mappingList = csvReader.readMapFile(a);
        System.out.println("\n\n");

        csvReader.printMappingList(mappingList);
        csvReader.csvFilesComparison(mappingList,data1,data2);
    }
}
