package com.kuzmich.solution.controller;

import com.kuzmich.solution.service.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/owner")
public class OwnerController {
    @Autowired
    RecordService recordService;
    private static final Logger logger = LoggerFactory.getLogger(OwnerController.class);

    @DeleteMapping("/{id}")
    public void deleteRecordById(@PathVariable Integer id) {
        logger.debug("Call api method deleteRecordById with param={}", id);
        recordService.deleteRecordById(id);
    }
}
