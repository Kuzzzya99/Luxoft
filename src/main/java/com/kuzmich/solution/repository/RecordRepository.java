package com.kuzmich.solution.repository;

import com.kuzmich.solution.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer>  {
    @Override
    Optional<Record> findById(Integer id);
    @Override
    void deleteById(Integer id);
}
