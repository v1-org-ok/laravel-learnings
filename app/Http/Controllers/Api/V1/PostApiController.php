<?php

namespace App\Http\Controllers\Api\V1;

use App\Http\Controllers\Controller;
use App\Models\Post;
use Illuminate\Http\Request;

class PostApiController extends Controller
{
    public function index()
    {
        return Post::all();
    }

    public function store(Request $request)
    {
        return Post::create($request->all());
    }

    public function show($id)
    {
        return Post::findOrFail($id);
    }
    
    public function destroy($id)
    {
        $post = Post::findOrFail($id);
        $post->delete();

        return 204;
    }

    public function edit(Request $request, $id)
    {
        $post = Post::findOrFail($id);
        $post->update($request->all());

        return $post;
    }
}

