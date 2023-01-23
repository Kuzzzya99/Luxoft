package com.kuzmich.solution.service.impl;

import com.kuzmich.solution.service.ValidationService;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ValidationServiceImpl implements ValidationService {
    private static final String TYPE = "text/csv";
    private static final String PRIMARY_KEY = "PRIMARY_KEY";
    private static final String UPDATED_TIMESTAMP = "UPDATED_TIMESTAMP";
    private static final String EMPTY_STRING = "";
    private static final Logger logger = LoggerFactory.getLogger(ValidationServiceImpl.class);

    public boolean isCsvFile(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public boolean validatePrimaryKey(CSVRecord csvRecord) {
        String primaryKey = csvRecord.get(PRIMARY_KEY);
        return !primaryKey.equals(EMPTY_STRING) && NumberUtils.isParsable(primaryKey);
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
                logger.error("Wrong date or date format, string={}", string);
            }
        }
        return null;
    }

    public boolean validateDate(CSVRecord csvRecord) {
        String date = csvRecord.get(UPDATED_TIMESTAMP);

        if (!date.equals(EMPTY_STRING)) {
            try {
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
                return true;
            } catch (ParseException e) {
                logger.error("Wrong date or date format, date={}", date);
                return false;
            }
        }
        return true;
    }
}
