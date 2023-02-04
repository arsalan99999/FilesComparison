package com.example.twodatabases;

import com.example.CsvOperations.CsvReader;
import com.example.CsvOperations.Mapping;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping
public class DatabaseController {

    @Autowired
    DatabaseService databaseService;

    @Autowired
    private JdbcTemplate mysqlJdbcTemplate;

    @Autowired
    private JdbcTemplate postgresJdbcTemplate;


    @PostMapping("/data")
    public Map<String, List<Map<String, Object>>> getData(@RequestParam("database1") String database1,
                                                          @RequestParam("database2") String database2,
                                                          @RequestParam("query1") String query1,
                                                          @RequestParam("query2") String query2){

        Map<String, List<Map<String, Object>>> results = new HashMap<>();

        List<Map<String, Object>> mysqlResult = mysqlJdbcTemplate.queryForList(query1);
        List<Map<String, Object>> postgresResult = postgresJdbcTemplate.queryForList(query2);
        results.put(database1, mysqlResult);
        results.put(database2, postgresResult);
        try {
            databaseService.processResults(mysqlResult,postgresResult);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    @PostMapping("/hello")
    public String getsmth(@RequestParam String data){
        System.out.println(data);
        return data;
    }
}
