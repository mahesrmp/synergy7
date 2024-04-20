import org.example.Data;
import org.example.controller.MenuController;
import org.example.controller.MerchantController;
import org.example.model.entity.Role;
import org.example.model.entity.Users;
import org.example.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    private UserServiceImpl userService;
    private MenuController menuController;
    private MerchantController merchantController;

    @BeforeEach
    public void setUp() {
        Data.userMap = new HashMap<>();
        userService = new UserServiceImpl();

        userService = new UserServiceImpl();
        menuController = new MenuController();
        merchantController = new MerchantController();
    }

    @Test
    public void testRegister_DuplicateEmail() {
        Users existingUser = new Users(1L, "existinguser", "existing@example.com", "password", Role.CUSTOMER);
        Data.userMap.put(1L, existingUser);
        String username = "testuser";
        String email = "existing@example.com";
        String password = "password";
        Role role = Role.CUSTOMER;

        String result = userService.register(username, email, password, role);

        assertEquals("Email already registered", result);
        assertEquals(1, Data.userMap.size());
    }

    @Test
    public void testLogin_IncorrectCredentials() {
        Users existingUser = new Users(1L, "existinguser", "existing@example.com", "password", Role.CUSTOMER);
        Data.userMap.put(1L, existingUser);
        String email = "existing@example.com";
        String password = "incorrectpassword"; // Password salah

        String result = userService.login(email, password);

        assertEquals("Email or password is incorrect. Please try again.", result);
    }

    @Test
    public void testLogin_InvalidRole() {
        Users existingUser = new Users(1L, "existinguser", "existing@example.com", "password", null); // Role null
        Data.userMap.put(1L, existingUser);
        String email = "existing@example.com";
        String password = "password";

        String result = userService.login(email, password);

        assertEquals("Invalid role", result);
    }
}
