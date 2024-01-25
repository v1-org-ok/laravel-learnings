<?php

namespace App\Http\Controllers\Api\V1;

use App\Http\Controllers\Controller;
use App\Services\ProductService;
use Illuminate\Http\Request;

class ProductApiController extends Controller
{
    protected $productService;

    public function __construct(ProductService $productService)
    {
        $this->productService = $productService;
    }

    public function index()
    {
        return $this->productService->getAllProducts();
    }

    public function store(Request $request)
    {
        return $this->productService->createProduct($request->all());
    }

    public function show($id)
    {
        return $this->productService->getProductById($id);
    }

    public function destroy($id)
    {
        return $this->productService->deleteProduct($id);
    }

    public function update(Request $request, $id)
    {
        return $this->productService->updateProduct($id, $request->all());
    }
}
