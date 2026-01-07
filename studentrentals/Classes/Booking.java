package studentrentals.Classes;

public class Booking {
    
    private BookingStatus bookingStatus;
    private String startDate;
    private String endDate;
    private Student student;       
    
    private Property bookedProperty;     

    public Booking(BookingStatus bookingStatus, String startDate, String endDate, Student student,  Property bookedProperty) {
        this.bookingStatus = bookingStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.student = student;
        this.bookedProperty = bookedProperty;
       
    }

     @Override
    public String toString() {
        return "Booking by " + student.getUsername() +" for " + bookedProperty.getAddress() +" from " + startDate + " to " + endDate +" [" + bookingStatus + "]";
    }

    
    //getters
    public BookingStatus getBookingStatus(){
        return bookingStatus;
    }

    public String getStartDate(){
        return startDate;
    }

    public String getEndDate(){
        return endDate;
    }

    public Student getStudent(){
        return student;
    }

    public Property getBookedProperty(){
        return bookedProperty;
    }
    //methods
    public void confirmBooking(){
        bookingStatus = BookingStatus.CONFIRMED;
    }

    public void rejectBooking(){
        bookingStatus = BookingStatus.REJECTED;
    }

    public void cancelBooking(){
        bookingStatus = BookingStatus.CANCELLED;
    }

}


