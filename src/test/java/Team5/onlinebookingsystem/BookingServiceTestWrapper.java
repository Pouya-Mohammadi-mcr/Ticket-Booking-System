package Team5.onlinebookingsystem;

public class BookingServiceTestWrapper extends BookingService {
    public void setBookingRepository(BookingRepository bookingRepository){
        repo = bookingRepository;
    }
}
