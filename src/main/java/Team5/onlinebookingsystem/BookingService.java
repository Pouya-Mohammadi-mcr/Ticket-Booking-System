package Team5.onlinebookingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookingService {

    @Autowired
    protected BookingRepository repo;

    public List<Booking> listAll() {
        return repo.findAll();
    }

    public void save(Booking booking) {
        repo.save(booking);
    }

    public Booking get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
