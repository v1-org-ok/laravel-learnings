<?php

namespace App\Services;

use App\Models\Category;

class CategoryService
{
    public function getAllCategories()
    {
        return Category::all();
    }

    public function createCategory($data)
    {
        return Category::create($data);
    }

    public function getCategoryById($id)
    {
        return Category::findOrFail($id);
    }

    public function deleteCategory($id)
    {
        $category = $this->getCategoryById($id);
        $category->delete();
        return $category;
    }

    public function updateCategory($id, $data)
    {
        $category = $this->getCategoryById($id);
        $category->update($data);
        return $category;
    }
}
