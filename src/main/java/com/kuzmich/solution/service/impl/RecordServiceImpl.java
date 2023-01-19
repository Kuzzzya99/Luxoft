package com.kuzmich.solution.service.impl;

import au.com.bytecode.opencsv.CSVReader;
import com.kuzmich.solution.model.Record;
import com.kuzmich.solution.repository.RecordRepository;
import com.kuzmich.solution.service.RecordService;
import com.kuzmich.solution.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    ValidationService validationService;

    @Autowired
    RecordRepository recordRepository;

    @Override
    public void parseCsv(MultipartFile multipartFile) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Reader reader = new InputStreamReader(multipartFile.getInputStream());
        CSVReader csvReader = new CSVReader(reader, ',', '"', 1);
        List<String[]> allRows = csvReader.readAll();
        for(String[] row : allRows){
            String[] values = row[0].split(";");
            Record record = new Record();
            record.setId(Integer.parseInt(values[0]));
            record.setName(values[1]);
            record.setDescription(values[2]);
            record.setTimestamp(LocalDate.parse(values[3],formatter));
            recordRepository.save(record);
        }
    }

    @Override
    public Record findRecordById(Integer id) {
        return recordRepository.findById(id).orElseThrow();// todo ex
    }

    @Override
    public void deleteRecordById(Integer id) {
        recordRepository.deleteById(id);
    }
}
