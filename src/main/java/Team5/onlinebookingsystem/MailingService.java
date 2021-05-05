package Team5.onlinebookingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailingService {

    @Autowired
    private JavaMailSender mailSender;

    String commonMessage = "\nSome General Instructions to keep in mind:\n" +
            "•       Check-in Time : Check-in desks will close 1 hour before departure.\n" +
            "•       Valid ID proof needed : Carry a valid photo identification proof (Driving Licence, Passport " +
            "or any other Government recognised photo identification)\n" +
            "•       Web Check-in (opens 48 hrs. before departure): Use Booking Reference and full name to check-in.\n"+
            "•       Visa and Passports : If you journey is international, you are required to hold a valid passport" +
            " and visa. Some countries won’t let you in without a valid visa, so double-check any visa requirements " +
            "before you leave.\n" +
            "\n" +
            "Pre-Flight Travel Checklist During Covid:\n" +
            "•       Face mask : All passengers need to wear a face mask in all public spaces throughout " +
            "your journey. \n" +
            "•       Negative COVID-19 Test  :  Passengers are required to provide a negative COVID-19 test result" +
            " before you can board a flight, which cannot be older than 72 hours from the departure time.\n" +
            "•       Health Declaration Form  :  It is a declaration that you do not have any symptoms of COVID-19" +
            " to the best of your knowledge. Passengers are required to fill in and submit this form online and also" +
            " carry a hard copy of the document.\n" +
            "•       Travel Permit : If you are travelling to a state that doesn’t permit non-essential travel due" +
            " to the coronavirus, you will most likely need to get a travel permit from the border control to enter" +
            " the state, regardless of the nature of your travel. Make sure to check the border restrictions of both" +
            " your own state and your destination state.\n" +
            "\n" +
            "We wish you a safe and a comfortable journey.\n" +
            "\n" +
            "Thank you for choosing us,\n" +
            "Team Fly Away";

    public void sendEmail(String subject, String receipient, String message) {
        String mailFrom = "boookflights@gmail.com";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailFrom);
        simpleMailMessage.setTo(receipient);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message+commonMessage);

        mailSender.send(simpleMailMessage);
    }
}
