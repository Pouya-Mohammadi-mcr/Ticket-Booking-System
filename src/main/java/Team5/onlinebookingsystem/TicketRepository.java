package Team5.onlinebookingsystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    //    // Query to get all connecting flights by id -- ck
    @Query("select a from Ticket a Where a.bookingRef Like ?1")
    public Ticket findByTicketRef(String ref);

}
