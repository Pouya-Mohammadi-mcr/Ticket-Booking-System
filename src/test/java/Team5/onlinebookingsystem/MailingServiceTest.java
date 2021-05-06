package Team5.onlinebookingsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

class MailingServiceTest {
    @Mock
    JavaMailSender mailSender;

    @InjectMocks
    MailingService mailingService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendEmailTest(){
        //  Arrange
        Mockito.doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        //Act
        //MailingService mailingService = new MailingService();
        mailingService.sendEmail("as","as","as");

        // Assert
        Mockito.verify(mailSender, times(1)).send(any(SimpleMailMessage.class));

    }
}
