package studentrentals.Controllers;

import java.util.ArrayList;
import java.util.List;

import studentrentals.Classes.Booking;
import studentrentals.Classes.Student;
import studentrentals.Classes.Homeowner;


public class BookingController {

    private List<Booking> bookings;
    


    public BookingController(){
        bookings = new ArrayList<>();


    }
    
    public boolean addBooking(Booking booking){
    for (Booking b : bookings){
        if (b.getBookedProperty().equals(booking.getBookedProperty())) {
            // Check for overlapping dates
            if (datesOverlap(b.getStartDate(), b.getEndDate(), booking.getStartDate(), booking.getEndDate())){
                System.out.println("Error: Room already booked for these dates!");
                return false;
            }
        }
    }
    bookings.add(booking);
    return true;
}

    public void acceptBooking(Booking booking) {
        booking.confirmBooking();
        System.out.println("Booking accepted.");
    }

    public void rejectBooking(Booking booking) {
        booking.rejectBooking();
        System.out.println("Booking rejected.");
    }


private boolean datesOverlap(String start1, String end1, String start2, String end2){
    return !(end1.compareTo(start2) < 0 || start1.compareTo(end2) > 0);
}

    public void getBookings(){
        for(Booking booking : bookings) {
            System.out.println(booking);
        }
        
    }

    public void getStudentBooking(Student student){
        for(Booking booking : bookings){
            if(booking.getStudent().equals(student)){
                System.out.println(booking);
            }
        }
    }
    //gets specific homeowner booking
    public void getHomeownerBooking(Homeowner homeowner) {
    for (Booking booking : bookings) {
        if (booking.getBookedProperty().getHomeowner().equals(homeowner)) {
            System.out.println(booking);
        }
    }
}
    //gets all homeowner bookings
public List<Booking> getHomeownerBookings(Homeowner homeowner) {
    List<Booking> result = new ArrayList<>();
    for (Booking booking : bookings) {
        if (booking.getBookedProperty().getHomeowner().equals(homeowner)) {
            result.add(booking);
        }
    }
    return result;
}


    
}
