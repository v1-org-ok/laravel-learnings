import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PostTest {

    @Test
    public void testGetId() {
        Post post = new Post();
        post.setId(1L);
        assertEquals(1L, post.getId());
    }

    @Test
    public void testGetName() {
        Post post = new Post();
        post.setName("Test Post");
        assertEquals("Test Post", post.getName());
    }

    @Test
    public void testGetDetail() {
        Post post = new Post();
        post.setDetail("Test Detail");
        assertEquals("Test Detail", post.getDetail());
    }

    @Test
    public void testSetId() {
        Post post = new Post();
        post.setId(1L);
        assertEquals(1L, post.getId());
    }

    @Test
    public void testSetName() {
        Post post = new Post();
        post.setName("Test Post");
        assertEquals("Test Post", post.getName());
    }

    @Test
    public void testSetDetail() {
        Post post = new Post();
        post.setDetail("Test Detail");
        assertEquals("Test Detail", post.getDetail());
    }
}