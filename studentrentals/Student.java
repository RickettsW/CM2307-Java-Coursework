package studentrentals;

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

    /*  future methods
    viewStudentBookings()
    searchAvailableRooms()
    requestBooking()
    leaveReview()
    */



    
}
