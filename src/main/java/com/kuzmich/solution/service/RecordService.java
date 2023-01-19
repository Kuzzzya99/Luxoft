package com.kuzmich.solution.service;

import com.kuzmich.solution.model.Record;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface RecordService {
    public void parseCsv(MultipartFile multipartFile) throws IOException;

    Record findRecordById(Integer id);

    void deleteRecordById(Integer id);
}
