package Team5.onlinebookingsystem;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ticket {

    private String customerEmail;
    private long flightId;
    private String seatClass;
    private String meal;
    private String priceBought;
    private int luggage;
    private String insurance;
    private String seating;

    public Ticket( String customerEmail,long flightId, String seatClass, String meal, String priceBought, int luggage,String insurance,String seating) {
        this.flightId = flightId;
        this.customerEmail = customerEmail;
        this.seatClass = seatClass;
        this.meal = meal;
        this.priceBought = priceBought;
        this.luggage = luggage;
        this.insurance = insurance;
        this.seating = seating;

    }

    public Ticket() {
    }

    @Id
    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getPriceBought() {
        return priceBought;
    }

    public void setPriceBought(String priceBought) {
        this.priceBought = priceBought;
    }

    public int getLuggage() {
        return luggage;
    }

    public void setLuggage(int luggage) {
        this.luggage = luggage;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getSeating() {
        return seating;
    }

    public void setSeating(String seating) {
        this.seating = seating;
    }
}
