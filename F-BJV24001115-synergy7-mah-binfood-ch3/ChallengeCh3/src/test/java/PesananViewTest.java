import org.example.controller.MenuController;
import org.example.controller.PesananController;
import org.example.model.entity.OrderDetail;
import org.example.model.entity.Product;
import org.example.view.PesananView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PesananViewTest {
    private PesananView pesananView;
    private MenuController menuController;
    private PesananController pesananController;

    @BeforeEach
    public void setUp() {
        pesananView = new PesananView();
        menuController = new MenuController();
        pesananController = new PesananController();
    }

    @Test
    public void testDisplayTitlePesanan() {
        pesananView.displayTitlePesanan();
    }

    @Test
    public void testDisplayKonfirmasiPembayaran() {
        pesananView.displayKonfirmasiPembayaran();
    }

    @Test
    public void testDisplayPesananIsEmpty() {
        pesananView.displayPesananIsEmpty();
    }

    @Test
    public void testDisplayPesanans() {
        Map<Long, OrderDetail> pesananMap = new HashMap<>();
        Product product1 = new Product(1L, "Bakso", 10000);
        OrderDetail orderDetail1 = new OrderDetail(1L, product1, 2);
        pesananMap.put(orderDetail1.getId(), orderDetail1);
        pesananView.displayPesanans(pesananMap);
    }

    private void provideInput(String data) {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        System.setIn(stdin);
    }
}
