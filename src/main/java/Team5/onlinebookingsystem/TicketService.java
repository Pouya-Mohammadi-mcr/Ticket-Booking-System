package Team5.onlinebookingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


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

        public String buildRandomTicketRef(){
        Random rand = new Random();
        StringBuilder randBookingReference = new StringBuilder();
        for(int i=0;i<10;i++){
            randBookingReference.append(Integer.toString(rand.nextInt(10)));
        }
        return randBookingReference.toString();
    }

        public String findByTicketRef(String ref){
        Ticket ticket = new Ticket();
        ticket =  tRepo.findByTicketRef(ref);
        if (ticket == null) {
            return "no";
        }
        return "yes";
    }

}
