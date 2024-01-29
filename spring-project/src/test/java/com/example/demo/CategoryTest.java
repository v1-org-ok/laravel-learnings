import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void whenFindByName_thenReturnCategory() {
        // given
        Category category = new Category();
        category.setName("Test Category");
        category.setDescription("Test Description");
        entityManager.persist(category);
        entityManager.flush();

        // when
        Category found = categoryRepository.findByName(category.getName());

        // then
        assertThat(found.getName()).isEqualTo(category.getName());
        assertThat(found.getDescription()).isEqualTo(category.getDescription());
    }
}