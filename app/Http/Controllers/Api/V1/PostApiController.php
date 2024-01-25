<?php

namespace App\Http\Controllers\Api\V1;

use App\Http\Controllers\Controller;
use App\Services\PostService;
use Illuminate\Http\Request;

class PostApiController extends Controller
{
    protected $postService;

    public function __construct(PostService $postService)
    {
        $this->postService = $postService;
    }

    public function index()
    {
        return $this->postService->getAllPosts();
    }

    public function store(Request $request)
    {
        return $this->postService->createPost($request->all());
    }

    public function show($id)
    {
        return $this->postService->getPostById($id);
    }
    
    public function destroy($id)
    {
        return $this->postService->deletePost($id);
    }

    public function edit(Request $request, $id)
    {
        return $this->postService->updatePost($id, $request->all());
    }
}

