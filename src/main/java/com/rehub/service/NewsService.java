package com.rehub.service;

import com.rehub.model.News;
import java.util.List;
import java.util.Optional;

public interface NewsService {
    List<News> findAll();
    Optional<News> findById(Long id);
    News save(News news);
    void delete(Long id);
}
