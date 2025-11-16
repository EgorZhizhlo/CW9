package com.rehub.service;

import com.rehub.model.ProgramService;
import java.util.List;

public interface ProgramServiceService {
    List<ProgramService> findAll();
    ProgramService findById(Long id);
    ProgramService save(ProgramService service);
    void delete(Long id);
}
