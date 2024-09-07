package com.cola.omlink.manager.controller;

import com.cola.omlink.manager.service.PostService;
import com.cola.omlink.repository.entity.article.Article;
import com.cola.omlink.repository.vo.common.Result;
import com.cola.omlink.repository.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/dashboard")
public class PostController {

    @Autowired
    private PostService postService;

    // find all event / post
    @GetMapping("/posts")
    public Result getAllPosts(@RequestParam("type") Integer type) {
        List<Article> posts = postService.getAllPostsByType(type);
        return Result.build(posts, ResultCodeEnum.SUCCESS);
    }

    //  event / post detail
    @GetMapping("posts/{id}")
    public Result getPostById(@PathVariable Long id) {
        Article post = postService.getPostById(id);
        if (post != null) {
            return Result.build(post, ResultCodeEnum.SUCCESS);
        } else {
            return Result.build(null, ResultCodeEnum.NOT_FOUND);
        }
    }

    // 根据 tag 排序查找 event 或 post
    @GetMapping("posts/sorted")
    public Result getPostsSortedByTag(@RequestParam("type") Integer type) {
        List<Article> posts = postService.getPostsSortedByTag(type);
        return Result.build(posts, ResultCodeEnum.SUCCESS);
    }


}
