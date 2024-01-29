import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(ProductApiController.class)
public class ProductApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductApiController productApiController;

    @Test
    public void testIndex() throws Exception {
        Product product1 = new Product(1L, "Product 1");
        Product product2 = new Product(2L, "Product 2");
        List<Product> products = Arrays.asList(product1, product2);

        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Product 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Product 2"));
    }

    @Test
    public void testStore() throws Exception {
        ProductRequest request = new ProductRequest("New Product");

        Product createdProduct = new Product(1L, "New Product");

        when(productService.createProduct(request)).thenReturn(createdProduct);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/products")
                .contentType("application/json")
                .content("{\"name\":\"New Product\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("New Product"));
    }

    @Test
    public void testShow() throws Exception {
        Product product = new Product(1L, "Product 1");

        when(productService.getProductById(1L)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Product 1"));
    }

    @Test
    public void testDestroy() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/products/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdate() throws Exception {
        ProductRequest request = new ProductRequest("Updated Product");

        Product updatedProduct = new Product(1L, "Updated Product");

        when(productService.updateProduct(1L, request)).thenReturn(updatedProduct);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/products/1")
                .contentType("application/json")
                .content("{\"name\":\"Updated Product\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Product"));
    }
}