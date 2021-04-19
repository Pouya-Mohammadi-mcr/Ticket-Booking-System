package Team5.onlinebookingsystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    //    Query to return all fligths where from starts with the keyword -- ck
    @Query("select a from Flight a Where a.from LIKE ?1%")
    public List<Flight> findByOrigin(String keyword);
    //    Query to return all fligths where to starts with the keyword -- ck
    @Query("select a from Flight a Where a.to LIKE ?1%")
    public List<Flight> findByDestination(String keyword);

}
