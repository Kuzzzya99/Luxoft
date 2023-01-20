package com.kuzmich.solution.controller;

import com.kuzmich.solution.exception.InvalidFileFormatException;
import com.kuzmich.solution.model.Record;
import com.kuzmich.solution.service.RecordService;
import com.kuzmich.solution.service.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

@RestController
public class ClientController {
    @Autowired
    RecordService recordService;
    @Autowired
    ValidationService validationService;
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @GetMapping("/{id}")
    public Record findRecordById(@PathVariable Integer id) {
        logger.debug("Call api method findRecordById with param={}", id);
        return recordService.findRecordById(id);
    }

    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException, ParseException {
        boolean isCsvFile = validationService.isCsvFile(multipartFile);
        logger.debug("isCsvFile={}", isCsvFile);
        if (isCsvFile) {
            recordService.parseCsv(multipartFile);
        } else {
            logger.error("Invalid file format");
            throw new InvalidFileFormatException("Invalid file format");
        }
    }
}
