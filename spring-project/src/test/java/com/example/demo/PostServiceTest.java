package com.example.services;

import com.example.models.Post;
import com.example.repositories.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceTest {

    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        postService = new PostService(postRepository);
    }

    @Test
    void getAllPosts_shouldReturnAllPosts() {
        // Arrange
        List<Post> expectedPosts = new ArrayList<>();
        expectedPosts.add(new Post(1L, "Title 1", "Content 1"));
        expectedPosts.add(new Post(2L, "Title 2", "Content 2"));
        when(postRepository.findAll()).thenReturn(expectedPosts);

        // Act
        List<Post> actualPosts = postService.getAllPosts();

        // Assert
        assertEquals(expectedPosts, actualPosts);
        verify(postRepository, times(1)).findAll();
    }

    @Test
    void createPost_shouldSavePostAndReturnIt() {
        // Arrange
        Post post = new Post(1L, "Title", "Content");
        when(postRepository.save(post)).thenReturn(post);

        // Act
        Post createdPost = postService.createPost(post);

        // Assert
        assertEquals(post, createdPost);
        verify(postRepository, times(1)).save(post);
    }

    @Test
    void getPostById_shouldReturnPostIfExists() {
        // Arrange
        Long postId = 1L;
        Post post = new Post(postId, "Title", "Content");
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        // Act
        Post actualPost = postService.getPostById(postId);

        // Assert
        assertEquals(post, actualPost);
        verify(postRepository, times(1)).findById(postId);
    }

    @Test
    void getPostById_shouldThrowExceptionIfPostNotFound() {
        // Arrange
        Long postId = 1L;
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> postService.getPostById(postId));
        verify(postRepository, times(1)).findById(postId);
    }

    @Test
    void deletePost_shouldDeletePostIfExists() {
        // Arrange
        Long postId = 1L;
        Post post = new Post(postId, "Title", "Content");
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        // Act
        postService.deletePost(postId);

        // Assert
        verify(postRepository, times(1)).findById(postId);
        verify(postRepository, times(1)).delete(post);
    }

    @Test
    void deletePost_shouldThrowExceptionIfPostNotFound() {
        // Arrange
        Long postId = 1L;
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> postService.deletePost(postId));
        verify(postRepository, times(1)).findById(postId);
        verify(postRepository, never()).delete(any());
    }

    @Test
    void updatePost_shouldUpdatePostIfExists() {
        // Arrange
        Long postId = 1L;
        Post existingPost = new Post(postId, "Title", "Content");
        Post updatedPost = new Post(postId, "Updated Title", "Updated Content");
        when(postRepository.findById(postId)).thenReturn(Optional.of(existingPost));
        when(postRepository.save(existingPost)).thenReturn(updatedPost);

        // Act
        Post actualPost = postService.updatePost(postId, updatedPost);

        // Assert
        assertEquals(updatedPost, actualPost);
        verify(postRepository, times(1)).findById(postId);
        verify(postRepository, times(1)).save(existingPost);
    }

    @Test
    void updatePost_shouldThrowExceptionIfPostNotFound() {
        // Arrange
        Long postId = 1L;
        Post updatedPost = new Post(postId, "Updated Title", "Updated Content");
        when(postRepository.findById(postId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> postService.updatePost(postId, updatedPost));
        verify(postRepository, times(1)).findById(postId);
        verify(postRepository, never()).save(any());
    }
}
