import org.example.Data;
import org.example.model.entity.Product;
import org.example.service.ProductService;
import org.example.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class ProdukServiceTest {
    @BeforeEach
    public void setUp() {
        Data.menuMap = new HashMap<>();
        Data.menuMap.put(1L, new Product(1L, "Es lilin", 1000));
        Data.menuMap.put(2L, new Product(2L, "Es lilin coklat", 2000));
    }

    @Test
    public void testGetMenuList_NotNull() {
        ProductService productService = new ProductServiceImpl();

        Map<Long, Product> result = productService.getMenuList();

        assertNotNull(result);
    }

    @Test
    public void testRead_ExistingProduct() {
        ProductService productService = new ProductServiceImpl();
        Long productId = 1L;

        Product result = productService.read(productId);

        assertNotNull(result);
        assertEquals(productId, result.getId());
    }

    @Test
    public void testRead_NonExistingProduct() {
        ProductService productService = new ProductServiceImpl();
        Long productId = 999L;

        Product result = productService.read(productId);

        assertNull(result);
    }

}
