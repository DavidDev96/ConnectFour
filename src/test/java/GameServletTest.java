import com.example.connectFour.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameServletTest {
    private GameServlet servlet = new GameServlet();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    /*@Test
    @DisplayName("Servlet.init shows message in console")
    void initCreatesConsoleOutput() throws ServletException {
        servlet.init();
        assertEquals("ConnectFourServlet initialized()", outContent.toString());
    }*/

    /*@Test
    void doPostWithNoParametersThrowsIllegalArgumentException() {
        HttpServletRequest request = null;
        HttpServletResponse response = null
        servlet.doPost(request, response);
    } */
}
