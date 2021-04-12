package Team5.onlinebookingsystem;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class appControllerTest {

    @Test
    void showSearchPageTest_ValidCase(){
        //Arrange
        Model model = Mockito.mock(Model.class);
        appController controller = new appController();
        String expectedResult = "SearchPage";

        //Act
        String page = controller.showSearchPage(model);

        //Assert
        verify(model, times(1)).addAttribute(any(String.class), any(Flight.class) );
        assertEquals(expectedResult, page);
    }

    @Test
    void showSearchPageTest_NullModelCase(){
        //Arrange
        appController controller = new appController();

        //Act
        Exception exception = assertThrows(NullPointerException.class, () -> {
            controller.showSearchPage(null);
        });
        String actualMessage = exception.getMessage();
        String expectedMessage = "\"model\" is null";

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void search() {
    }
}