package com.kuzmich.solution.service.impl;

import com.kuzmich.solution.exception.UnformatableDateException;
import com.kuzmich.solution.service.ValidationService;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ValidationServiceImpl implements ValidationService {
    private static final String TYPE = "text/csv";
    private static final String PRIMARY_KEY = "PRIMARY_KEY";
    private static final String EMPTY_STRING = "";
    public boolean isCsvFile(MultipartFile file){
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public boolean validatePrimaryKey(CSVRecord csvRecord) {
        return !csvRecord.get(PRIMARY_KEY).equals(EMPTY_STRING);
    }

    public String returnValueOrNull(String string) {
        if (!string.equals(EMPTY_STRING)) {
            return string;
        }
        return null;
    }

    public Date convertStringToDate(String string) {
        if (!string.equals(EMPTY_STRING)) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(string);
            } catch (ParseException e) {
                throw new UnformatableDateException("Wrong date or date format. Please use yyyy-MM-dd HH:mm:ss");
            }
        }
        return null;
    }

}
