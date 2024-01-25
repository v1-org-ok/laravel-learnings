package com.example.services;

import com.example.models.Post;
import com.example.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post createPost(Post data) {
        return postRepository.save(data);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public void deletePost(Long id) {
        Post post = getPostById(id);
        postRepository.delete(post);
    }

    public Post updatePost(Long id, Post data) {
        Post post = getPostById(id);
        post.setTitle(data.getTitle());
        post.setContent(data.getContent());
        return postRepository.save(post);
    }
}