package Team5.onlinebookingsystem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

//    // Query to get all connecting flights by id -- ck
    @Query("select a from Flight a Where a.id = ?1")
    public List<Flight> getFlightById(long id);

    // Query to get all connecting flights -- ck
    @Query("select a from Flight a Where a.from LIKE ?1 and a.date LIKE ?2")
    public List<Flight> getAllConnectingFlights(String from,String date);

    // Query to get all connecting flights with any/all dates -- ck
    @Query("select a from Flight a Where a.from LIKE ?1")
    public List<Flight> getAllConnectingFlightsAllDates(String from);

    // Query to get all flights with any/all dates -- ck
    @Query("select a from Flight a Where a.from LIKE ?1 and a.to LIKE ?2")
    public List<Flight> getFlightsAllDates(String from,String to);

    //    Query to return all flights where from starts with the keyword -- ck
    @Query("select a from Flight a Where a.from LIKE ?1%")
    public List<Flight> findByOrigin(String keyword);

    //    Query to return all flights where to starts with the keyword -- ck
    @Query("select a from Flight a Where a.to LIKE ?1% and a.from LIKE ?2")
    public List<Flight> findByDestination(String keyword,String origin);

}
