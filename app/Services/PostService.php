<?php

namespace App\Services;

use App\Models\Post;

class PostService
{
    public function getAllPosts()
    {
        return Post::all();
    }

    public function createPost($data)
    {
        return Post::create($data);
    }

    public function getPostById($id)
    {
        return Post::findOrFail($id);
    }

    public function deletePost($id)
    {
        $post = $this->getPostById($id);
        $post->delete();
        return $post;
    }

    public function updatePost($id, $data)
    {
        $post = $this->getPostById($id);
        $post->update($data);
        return $post;
    }
}
