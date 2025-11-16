package com.rehub.service;

import com.rehub.model.Request;
import java.util.List;

public interface RequestService {
    List<Request> findAll();
    Request findById(Long id);
    Request save(Request request);
    void markProcessed(Long id);
    void delete(Long id);
}
