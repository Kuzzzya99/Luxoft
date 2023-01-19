package com.kuzmich.solution.controller;

import com.kuzmich.solution.service.RecordService;
import com.kuzmich.solution.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/csv")
public class CsvController {
    @Autowired
    ValidationService validationService;
    @Autowired
    RecordService recordService;

    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        recordService.parseCsv(multipartFile);
    }
}
