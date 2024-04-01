package com.example.demo.controller;

import com.example.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
public class CategoryApiControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryApiController categoryApiController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(categoryApiController).build();
    }

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/api/v1/categories"))
                .andExpect(status().isOk());
        verify(categoryService).getAllCategories();
    }

    @Test
    public void testStore() throws Exception {
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("name", "Electronics");

        when(categoryService.createCategory(requestData)).thenReturn(requestData);

        mockMvc.perform(post("/api/v1/categories")
                .contentType("application/json")
                .content("{\"name\":\"Electronics\"}"))
                .andExpect(status().isOk());

        verify(categoryService).createCategory(requestData);
    }

    @Test
    public void testShow() throws Exception {
        Long categoryId = 1L;
        Map<String, Object> categoryData = new HashMap<>();
        categoryData.put("id", categoryId);
        categoryData.put("name", "Electronics");

        when(categoryService.getCategoryById(categoryId)).thenReturn(categoryData);

        mockMvc.perform(get("/api/v1/categories/" + categoryId))
                .andExpect(status().isOk());

        verify(categoryService).getCategoryById(categoryId);
    }

    @Test
    public void testDestroy() throws Exception {
        Long categoryId = 1L;

        doNothing().when(categoryService).deleteCategory(categoryId);

        mockMvc.perform(delete("/api/v1/categories/" + categoryId))
                .andExpect(status().isOk());

        verify(categoryService).deleteCategory(categoryId);
    }

    @Test
    public void testUpdate() throws Exception {
        Long categoryId = 1L;
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("name", "Home Appliances");

        when(categoryService.updateCategory(eq(categoryId), anyMap())).thenReturn(requestData);

        mockMvc.perform(put("/api/v1/categories/" + categoryId)
                .contentType("application/json")
                .content("{\"name\":\"Home Appliances\"}"))
                .andExpect(status().isOk());

        verify(categoryService).updateCategory(eq(categoryId), anyMap());
    }
}