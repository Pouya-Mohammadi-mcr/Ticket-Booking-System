package Team5.onlinebookingsystem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Flight {
    private long id;
    private String Flight_No;
    private String Seat;
    private String From;
    private String To;
    private String Time;
    private String Date;
    private String Price;

    public Flight(long id, String flight_No, String seat, String from, String to, String time, String date, String price) {
        this.id = id;
        Flight_No = flight_No;
        Seat = seat;
        From = from;
        To = to;
        Time = time;
        Date = date;
        Price = price;
    }

    public Flight(String flight_No, String seat, String from, String to, String time, String date, String price) {
        Flight_No = flight_No;
        Seat = seat;
        From = from;
        To = to;
        Time = time;
        Date = date;
        Price = price;
    }

    public Flight() {
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getFlight_No() {
        return Flight_No;
    }

    public void setFlight_No(String flight_No) {
        Flight_No = flight_No;
    }

    public String getSeat() {
        return Seat;
    }

    public void setSeat(String seat) {
        Seat = seat;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
