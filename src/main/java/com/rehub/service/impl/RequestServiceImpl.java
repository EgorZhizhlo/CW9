package com.rehub.service.impl;

import com.rehub.model.Request;
import com.rehub.model.Request.Status;
import com.rehub.repository.RequestRepository;
import com.rehub.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    @Override
    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    @Override
    public Request findById(Long id) {
        return requestRepository.findById(id).orElse(null);
    }

    @Override
    public Request save(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public void markProcessed(Long id) {
        requestRepository.findById(id).ifPresent(req -> {
            req.setStatus(Status.PROCESSED);
            requestRepository.save(req);
        });
    }

    @Override
    public void delete(Long id) {
        requestRepository.deleteById(id);
    }

}
