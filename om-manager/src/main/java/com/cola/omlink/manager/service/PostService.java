package com.cola.omlink.manager.service;


import com.cola.omlink.repository.entity.article.Article;

import java.util.List;

public interface PostService {
    List<Article> getAllPostsByType(Integer type);

    Article getPostById(Long id);

    List<Article> getPostsSortedByTag(Integer type);
}
