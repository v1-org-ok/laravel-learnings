import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindByName_thenReturnProduct() {
        // given
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(9.99);
        product.setStock(10);
        product.setImage("test.jpg");
        entityManager.persist(product);
        entityManager.flush();

        // when
        Product found = productRepository.findByName(product.getName());

        // then
        assertThat(found.getName()).isEqualTo(product.getName());
        assertThat(found.getDescription()).isEqualTo(product.getDescription());
        assertThat(found.getPrice()).isEqualTo(product.getPrice());
        assertThat(found.getStock()).isEqualTo(product.getStock());
        assertThat(found.getImage()).isEqualTo(product.getImage());
    }
}