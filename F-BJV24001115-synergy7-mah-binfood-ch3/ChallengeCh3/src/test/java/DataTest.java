import org.example.Data;
import org.example.model.entity.Merchant;
import org.example.model.entity.Product;
import org.example.model.entity.Role;
import org.example.model.entity.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class DataTest {

    @BeforeEach
    public void setUp() {
        Data.menuMap.clear();
        Data.pesananDetailMap.clear();
        Data.pesananMap.clear();
        Data.merchantMap.clear();
        Data.userMap.clear();
    }

    @Test
    public void testInit() {
        Data.init();

        Map<Long, Product> menuMap = Data.menuMap;
        assertNotNull(menuMap);
        assertFalse(menuMap.isEmpty());
        assertEquals(5, menuMap.size());

        Map<Long, Users> userMap = Data.userMap;
        assertNotNull(userMap);
        assertFalse(userMap.isEmpty());
        assertEquals(3, userMap.size());

        Map<Long, Merchant> merchantMap = Data.merchantMap;
        assertNotNull(merchantMap);
        assertFalse(merchantMap.isEmpty());
        assertEquals(2, merchantMap.size());

        // Pengecekan apakah data yang diinisialisasi sesuai dengan yang diharapkan
        Product menu1 = menuMap.get(1L);
        assertEquals("Nasi Goreng", menu1.getProduct_name());
        assertEquals(15000, menu1.getPrice());
        assertEquals(1L, menu1.getMerchant_id());

        Product menu5 = menuMap.get(5L);
        assertEquals("Es Jeruk", menu5.getProduct_name());
        assertEquals(5000, menu5.getPrice());
        assertEquals(2L, menu5.getMerchant_id());

        Users user1 = userMap.get(1L);
        assertEquals("Mahes", user1.getUsername());
        assertEquals("mahes@gmail.com", user1.getEmail_address());
        assertEquals("mahes", user1.getPassword());
        assertEquals(Role.MERCHANT, user1.getRole());

        Users user3 = userMap.get(3L);
        assertEquals("Bambang", user3.getUsername());
        assertEquals("bambang@gmail.com", user3.getEmail_address());
        assertEquals("bambang", user3.getPassword());
        assertEquals(Role.MERCHANT, user3.getRole());

        Merchant merchant1 = merchantMap.get(1L);
        assertEquals("Kedai Mahes", merchant1.getMerchant_name());
        assertEquals("Jl Kemenangan", merchant1.getMerchant_location());
        assertTrue(merchant1.isOpen());

        Merchant merchant2 = merchantMap.get(2L);
        assertEquals("Kedai Bambang", merchant2.getMerchant_name());
        assertEquals("Jl Kemenangan", merchant2.getMerchant_location());
        assertTrue(merchant2.isOpen());
    }
}
