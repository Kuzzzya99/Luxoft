package com.kuzmich.solution.service.impl;

import com.kuzmich.solution.exception.NoSuchRecordException;
import com.kuzmich.solution.model.Record;
import com.kuzmich.solution.repository.RecordRepository;
import com.kuzmich.solution.service.RecordService;
import com.kuzmich.solution.service.ValidationService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    ValidationService validationService;
    @Autowired
    RecordRepository recordRepository;

    private char DELIMITER = ';';
    private static final String PRIMARY_KEY = "PRIMARY_KEY";
    private static final String NAME = "NAME";
    private static final String DESCRIPTION = "DESCRIPTION";
    private static final String UPDATED_TIMESTAMP = "UPDATED_TIMESTAMP";
    private static final Logger logger = LoggerFactory.getLogger(RecordServiceImpl.class);

    @Override
    public void parseCsv(MultipartFile multipartFile) throws IOException {
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()));
        CSVFormat csvFormat = CSVFormat.newFormat(DELIMITER).withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim();
        CSVParser csvParser = new CSVParser(fileReader,
                csvFormat);
        Iterable<CSVRecord> csvRecords = csvParser.getRecords();
        for (CSVRecord csvRecord : csvRecords) {
            if (validationService.validatePrimaryKey(csvRecord) && validationService.validateDate(csvRecord)) {
                logger.debug("Primary key is valid for record={}", csvRecord);
                recordRepository.save(buildRecord(csvRecord));
            }
        }
    }

    @Override
    public Record findRecordById(Integer id) {
        return recordRepository.findById(id).orElseThrow(() -> {
            logger.error("No record found with such id={}", id);
            throw new NoSuchRecordException("No record found with such id");
        });
    }

    @Override
    public void deleteRecordById(Integer id) {
        try {
            recordRepository.deleteById(id);
        } catch (Exception exception) {
            logger.error("Cant delete record, no record found with such id={}", id);
            throw new NoSuchRecordException("No record found with such id");
        }
    }

    private Record buildRecord(CSVRecord csvRecord) {
        String name = validationService.returnValueOrNull(csvRecord.get(NAME));
        String description = validationService.returnValueOrNull(csvRecord.get(DESCRIPTION));
        Date timestamp = validationService.convertStringToDate(csvRecord.get(UPDATED_TIMESTAMP));
        return new Record(
                Integer.parseInt(csvRecord.get(PRIMARY_KEY)),
                name,
                description,
                timestamp);
    }
}
