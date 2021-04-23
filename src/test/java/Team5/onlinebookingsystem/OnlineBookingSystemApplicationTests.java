package Team5.onlinebookingsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OnlineBookingSystemApplicationTests {

	@Test
	// The main method should run without any exceptions
	void MainTest() {
		assertDoesNotThrow(() -> OnlineBookingSystemApplication.main(new String[]{}));
	}
}



