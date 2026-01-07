package studentrentals.Classes;

import studentrentals.Controllers.ReviewController;
import studentrentals.Controllers.BookingController;


public class Student extends User {

    private String universityName;
    private String studentIdNumber;

    //constructor
    public Student(String username, String password, String firstName, String lastName, String email, String DOB, String universityName, String studentIdNumber){

        super(username, password, firstName, lastName, email, DOB);

        this.universityName = universityName;
        this.studentIdNumber = studentIdNumber;
    }

    //getters

    public String getUniversityName(){
        return universityName;

    }

    public String getStudentIdNumber(){
        return studentIdNumber;
    }

    //methods
    public void leaveReview(Property property, int stars, String comment, ReviewController controller){
        System.out.println(this.getUsername() + " created a review for " + property.getAddress());
        Review review = new Review(stars, comment,"2026-01-04", this, property);
        controller.addReview(review);
    }

    public void requestBooking(Property property, String start , String end,  BookingController controller){
        System.out.println(this.getUsername() + " created a booking for " + property.getAddress());
        Booking booking = new Booking(BookingStatus.PENDING, start, end, this, property);
        controller.addBooking(booking);
    }

    public void viewStudentBookings(BookingController controller){
        controller.getStudentBooking(this);



    } 
}
