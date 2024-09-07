package com.cola.omlink.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cola.omlink.manager.service.PostService;
import com.cola.omlink.repository.entity.article.Article;
import com.cola.omlink.manager.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;


    // find all event / post
    @Override
    public List<Article> getAllPostsByType(Integer type) {
        return postMapper.findPostsByType(type);
    }

    @Override
    public Article getPostById(Long id) {
        return postMapper.findPostById(id);
    }

    @Override
    public List<Article> getPostsSortedByTag(Integer type) {
        return postMapper.findPostsSortedByTag(type);
    }

}
