package studentrentals;

public class Booking {
    
    private String bookingStatus;
    private String startDate;
    private String endDate;

    public Booking(String bookingStatus, String startDate, String endDate){
        this.bookingStatus = bookingStatus;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getBookingStatus(){
        return bookingStatus;
    }

    public String getStartDate(){
        return startDate;
    }

    public String getEndDate(){
        return endDate;
    }

}


/*future methods
confirmBooking()
rejectBooking()
cancelBooking()

*/