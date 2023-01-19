package com.kuzmich.solution.service;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public interface ValidationService {
    boolean isCsvFile(MultipartFile file);

    boolean validatePrimaryKey(CSVRecord csvRecord);

    String returnValueOrNull(String string);

    Date convertStringToDate(String string);
}
