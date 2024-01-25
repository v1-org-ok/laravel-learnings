<?php

namespace App\Http\Controllers\Api\V1;

use App\Http\Controllers\Controller;
use App\Services\CategoryService;
use Illuminate\Http\Request;

class CategoryApiController extends Controller
{
    protected $categoryService;

    public function __construct(CategoryService $categoryService)
    {
        $this->categoryService = $categoryService;
    }

    public function index()
    {
        return $this->categoryService->getAllCategories();
    }

    public function store(Request $request)
    {
        return $this->categoryService->createCategory($request->all());
    }

    public function show($id)
    {
        return $this->categoryService->getCategoryById($id);
    }

    public function destroy($id)
    {
        return $this->categoryService->deleteCategory($id);
    }

    public function update(Request $request, $id)
    {
        return $this->categoryService->updateCategory($id, $request->all());
    }
}
