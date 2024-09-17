package com.cola.omlink.manager.mapper;

import com.cola.omlink.repository.entity.article.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    List<Article> findPostsByType(@Param("type") Integer type);

    Article findPostById(@Param("id") Long id);

    List<Article> findPostsByTag(@Param("type") Integer type);

}
