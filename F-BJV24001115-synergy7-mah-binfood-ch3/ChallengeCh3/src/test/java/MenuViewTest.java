import org.example.controller.PesananController;
import org.example.model.entity.Product;
import org.example.view.MenuView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MenuViewTest {
    private MenuView menuView;
    private PesananController pesananController;

    @BeforeEach
    public void setUp() {
        menuView = new MenuView();
        pesananController = new PesananController();
    }

    @Test
    public void testDisplayTitle() {
        menuView.displayTitle();
    }

    @Test
    public void testDisplayMenus() {
        Map<Long, Product> menuMap = new HashMap<>();
        Product product1 = new Product(1L, "Bakso Ikan", 10000);
        Product product2 = new Product(2L, "Bakso Sapi", 12000);
        menuMap.put(product1.getId(), product1);
        menuMap.put(product2.getId(), product2);
        menuView.displayMenus(menuMap);
    }

    private void provideInput(String data) {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        System.setIn(stdin);
    }
}
