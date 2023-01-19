package com.kuzmich.solution.controller;

import com.kuzmich.solution.model.Record;
import com.kuzmich.solution.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecordController {
    @Autowired
    RecordService recordService;
    @GetMapping("/{id}")
    public Record findRecordById(@PathVariable Integer id){
        return recordService.findRecordById(id);
    }
}
