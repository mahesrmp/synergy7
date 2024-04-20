import org.example.Data;
import org.example.model.entity.Order;
import org.example.model.entity.OrderDetail;
import org.example.model.entity.Product;
import org.example.service.OrderService;
import org.example.service.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {

    @BeforeEach
    public void setUp() {
        Data.pesananMap = new HashMap<>();
        Data.pesananDetailMap = new HashMap<>();
    }

    @Test
    public void testGetPesananList_NotNull() {
        OrderService orderService = new OrderServiceImpl();

        Map<Long, OrderDetail> result = orderService.getPesananList();

        assertNotNull(result);
    }

    @Test
    public void testCreatePesanan() {
        OrderService orderService = new OrderServiceImpl();
        Order pesanan = new Order();
        pesanan.setOrderDetails(new ArrayList<>());

        Order result = orderService.createPesanan(pesanan);

        assertNotNull(result.getId());
        assertNotNull(result.getOrder_time());
        assertEquals(1, Data.pesananMap.size());
    }

    @Test
    public void testTotalHarga() {
        OrderService orderService = new OrderServiceImpl();
        OrderDetail orderDetail1 = new OrderDetail(new Product("Es lilin", 1000), 2);
        OrderDetail orderDetail2 = new OrderDetail(new Product("Es lilin coklat", 2000), 3);
        Order pesanan = new Order();
        pesanan.setOrderDetails(Arrays.asList(orderDetail1, orderDetail2));
        Data.pesananMap.put(1L, pesanan);

        int result = orderService.totalHarga();

        assertEquals(8000, result);
    }

    @Test
    public void testTotalPembelian() {
        OrderService orderService = new OrderServiceImpl();
        OrderDetail orderDetail1 = new OrderDetail(new Product("Es lilin", 1000), 2);
        OrderDetail orderDetail2 = new OrderDetail(new Product("Es lilin coklat", 2000), 3);
        Order pesanan1 = new Order();
        pesanan1.setOrderDetails(Arrays.asList(orderDetail1));
        Order pesanan2 = new Order();
        pesanan2.setOrderDetails(Arrays.asList(orderDetail2));
        Data.pesananMap.put(1L, pesanan1);
        Data.pesananMap.put(2L, pesanan2);

        int result = orderService.totalPembelian();

        assertEquals(5, result);
    }
}

