package studentrentals.Classes;

public abstract class User {

    private String username;
    private String hashedPassword;
    private String firstName;
    private String lastName;
    private String email;
    private String DOB;


    public User(String username, String password, String firstName, String lastName, String email, String DOB){
        
        this.username = username;
        this.hashedPassword = PasswordUtils.hashPassword(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.DOB = DOB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User other = (User) o;
        return username.equals(other.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }


    //getters
    public String getUsername(){
        return username;
    }

    public String getHashedPassword(){
        return hashedPassword;
    }

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public String getDOB(){
        return DOB;
    }
    //methods
    public void registerForAccount(){
        System.out.println("Account created for " + username);

    }

    public boolean logIn(String enteredUsername, String enteredPassword) {
        String hashedInput = PasswordUtils.hashPassword(enteredPassword);
        return username.equals(enteredUsername) && hashedPassword.equals(hashedInput);
}


    public void logOff(){
        System.out.println(username + " has logged off");

    }


}

    
    

