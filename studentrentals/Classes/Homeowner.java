package studentrentals.Classes;
import studentrentals.Controllers.BookingController;
public class Homeowner extends User {

    public Homeowner(String username, String password, String firstName, String lastName, String email, String DOB){

     super(username, password, firstName, lastName, email, DOB);

    }


    public void listProperty(BookingController controller){
        controller.getHomeownerBooking(this);

    }
}
