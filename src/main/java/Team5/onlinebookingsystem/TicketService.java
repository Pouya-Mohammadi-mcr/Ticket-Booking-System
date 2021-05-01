package Team5.onlinebookingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;



@Service
@Transactional
public class TicketService {

        @Autowired
        protected TicketRepository tRepo;


        public List<Ticket> listAll() {
            return tRepo.findAll();
        }

        public void save(Ticket ticket) {
            tRepo.save(ticket);
        }

        public Ticket get(long id) {
            return tRepo.findById(id).get();
        }

        public void delete(long id) {
            tRepo.deleteById(id);
        }
}
