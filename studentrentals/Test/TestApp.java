package studentrentals.Test;

import studentrentals.Classes.*;
import studentrentals.Controllers.*;
public class TestApp {
    public static void main(String[] args) {

        // Step 1: create a review controller
        ReviewController reviewController = new ReviewController();
        BookingController bookingController = new BookingController();

        // Step 2: create a student
        Student student = new Student("jsmith", "pass123", "John", "Smith", "jsmith@email.com",
                                      "01-01-2000", "Uni of X", "S12345");

        // Step 3: create a property
        Homeowner homeowner = new Homeowner("homeowner1", "pass456", "Alice", "Brown", "alice@email.com", "15-03-1995");
        Property property = new Property("123 Main St", "AB12CD", "Nice house", "City Center", homeowner);
        
                // create another property
        Property property2 = new Property("456 Oak St", "XY34ZZ", "Cozy apartment", "Suburbs", homeowner);

     
        // Step 4: student leaves a review
        student.leaveReview(property, 5, "Great place!", reviewController);

        //student review
        student.leaveReview(property2, 4, "Nice place too!", reviewController);

        student.requestBooking(property2, "04-03-2026", "11-03-2026", bookingController);

        // Step 5: check if review is stored
        reviewController.getReviews();
        bookingController.getBookings();


        
        // print reviews only for property1
        System.out.println("Reviews for 123 Main St:");
        reviewController.getReviewsProperty(property);

        // print reviews only for property2
        System.out.println("Reviews for 456 Oak St:");
        reviewController.getReviewsProperty(property2);

        System.out.println("booking made by student " + student.getUsername());
        bookingController.getStudentBooking(student);
    }
}
 