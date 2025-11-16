package com.rehub.service.impl;

import com.rehub.model.ProgramService;
import com.rehub.repository.ProgramServiceRepository;
import com.rehub.service.ProgramServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramServiceServiceImpl implements ProgramServiceService {

    private final ProgramServiceRepository repository;

    @Override
    public List<ProgramService> findAll() {
        return repository.findAll();
    }

    @Override
    public ProgramService findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ProgramService save(ProgramService service) {
        return repository.save(service);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
