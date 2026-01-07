package studentrentals.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import studentrentals.Classes.*;

public class MenuController {

    private List<User> users = new ArrayList<>();
    private ReviewController reviewController = new ReviewController();
    private BookingController bookingController = new BookingController();
    private List<Property> properties = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private User loggedInUser = null;

    public void start() {
        boolean running = true;

        while (running) {
            if (loggedInUser == null) {
                System.out.println("\n--- Welcome to StudentRentals ---");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("> ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1" -> registerUser();
                    case "2" -> loginUser();
                    case "3" -> running = false;
                    default -> System.out.println("Invalid option");
                }
            } else {
                if (loggedInUser instanceof Homeowner) {
                    homeownerMenu((Homeowner) loggedInUser);
                } else if (loggedInUser instanceof Student) {
                    studentMenu((Student) loggedInUser);
                }
            }
        }
        System.out.println("Goodbye!");
    }

    // --------------------------------------------
    // VALIDATION HELPERS

    private String readValidatedString(String prompt, int min, int max, String type) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) System.out.println("Cannot be empty.");
            else if (input.length() < min) System.out.println("Too short.");
            else if (input.length() > max) System.out.println("Too long.");
            else if (type.equals("EMAIL") &&
                    !input.matches("[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,6}"))
                System.out.println("Invalid email.");
            else return input;
        }
    }

    private int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int v = Integer.parseInt(scanner.nextLine());
                if (v < min || v > max) System.out.println("Out of range.");
                else return v;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
        }
    }

    private boolean usernameExists(String username) {
        for (User u : users)
            if (u.getUsername().equalsIgnoreCase(username)) return true;
        return false;
    }

    // ---------------------------------------
    // REGISTER / LOGIN 

    private void registerUser() {
        String username;
        do {
            username = readValidatedString("Username: ", 5, 15, "TEXT");
            if (usernameExists(username)) System.out.println("Username taken.");
        } while (usernameExists(username));

        String password = readValidatedString("Password: ", 6, 20, "TEXT");
        String first = readValidatedString("First name: ", 2, 30, "TEXT");
        String last = readValidatedString("Last name: ", 2, 30, "TEXT");
        String email = readValidatedString("Email: ", 5, 50, "EMAIL");
        String dob = readValidatedString("DOB: ", 6, 20, "TEXT");

        String type;
        do {
            type = readValidatedString("Student (S) or Homeowner (H)? ", 1, 1, "TEXT").toUpperCase();
        } while (!type.equals("S") && !type.equals("H"));

        if (type.equals("S")) {
            String uni = readValidatedString("University: ", 5, 50, "TEXT");
            String sid = readValidatedString("Student ID: ", 5, 15, "TEXT");
            users.add(new Student(username, password, first, last, email, dob, uni, sid));
            System.out.println("Student registered!");
        } else {
            users.add(new Homeowner(username, password, first, last, email, dob));
            System.out.println("Homeowner registered!");
        }
    }

    private void loginUser() {
        String username = readValidatedString("Username: ", 5, 15, "TEXT");
        String password = readValidatedString("Password: ", 6, 20, "TEXT");

        for (User u : users) {
            if (u.logIn(username, password)) {
                loggedInUser = u;
                System.out.println("Logged in as " + username);
                return;
            }
        }
        System.out.println("Login failed.");
    }

    private void logOff() {
        loggedInUser.logOff();
        loggedInUser = null;
    }

    // -----------------------------------------
    // HOMEOWNER MENU 

    private void homeownerMenu(Homeowner h) {
        System.out.println("\n--- Homeowner Menu ---");
        System.out.println("1. Add Property");
        System.out.println("2. Add Room");
        System.out.println("3. View / Manage Bookings");
        System.out.println("4. View Reviews");
        System.out.println("5. Logout");
        System.out.print("> ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> addProperty(h);
            case "2" -> addRoom(h);
            case "3" -> manageBookings(h);
            case "4" -> reviewController.getReviewsByHomeowner(h);
            case "5" -> logOff();
            default -> System.out.println("Invalid option");
        }
    }

    private void addProperty(Homeowner h) {
        String addr = readValidatedString("Address: ", 5, 100, "TEXT");
        String pc = readValidatedString("Postcode: ", 3, 10, "TEXT");
        String desc = readValidatedString("Description: ", 5, 200, "TEXT");
        String area = readValidatedString("Area: ", 2, 50, "TEXT");

        properties.add(new Property(addr, pc, desc, area, h));
        System.out.println("Property added!");
    }

    private void addRoom(Homeowner h) {
        List<Property> mine = new ArrayList<>();
        for (Property p : properties)
            if (p.getHomeowner().equals(h)) mine.add(p);

        if (mine.isEmpty()) {
            System.out.println("No properties.");
            return;
        }

        for (int i = 0; i < mine.size(); i++)
            System.out.println(i + ": " + mine.get(i).getAddress());

        int idx = readInt("Select property: ", 0, mine.size() - 1);
        Property p = mine.get(idx);

        String type = readValidatedString("Room type: ", 3, 30, "TEXT");
        int rent = readInt("Rent: ", 1, 10000);
        String amen = readValidatedString("Amenities: ", 3, 100, "TEXT");
        String dates = readValidatedString("Available dates (dd-MM-yyyy to dd-MM-yyyy): ", 17, 25, "TEXT");

        p.addRoom(new Room(type, rent, amen, dates));
        System.out.println("Room added!");
    }

    private void manageBookings(Homeowner h) {
        List<Booking> list = bookingController.getHomeownerBookings(h);

        if (list.isEmpty()) {
            System.out.println("No bookings.");
            return;
        }

        for (int i = 0; i < list.size(); i++)
            System.out.println(i + ": " + list.get(i));

        int idx = readInt("Select booking: ", 0, list.size() - 1);
        Booking b = list.get(idx);

        if (b.getBookingStatus() != BookingStatus.PENDING) {
            System.out.println("Already processed.");
            return;
        }

        System.out.println("1. Accept\n2. Reject");
        String c = scanner.nextLine();

        if (c.equals("1")) bookingController.acceptBooking(b);
        else if (c.equals("2")) bookingController.rejectBooking(b);
    }

    // --------------------------------------------
    // STUDENT MENU 

    private void studentMenu(Student s) {
        System.out.println("\n--- Student Menu ---");
        System.out.println("1. Search Rooms");
        System.out.println("2. Request Booking");
        System.out.println("3. View My Bookings");
        System.out.println("4. Leave Review");
        System.out.println("5. Logout");
        System.out.print("> ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> searchRooms();
            case "2" -> requestBooking(s);
            case "3" -> s.viewStudentBookings(bookingController);
            case "4" -> leaveReview(s);
            case "5" -> logOff();
            default -> System.out.println("Invalid option");
        }
    }

    // -----------------------------------------
    // STUDENT ACTIONS 

    private void searchRooms() {
        String area = readValidatedString("Area: ", 2, 50, "TEXT");
        int min = readInt("Min rent: ", 0, 10000);
        int max = readInt("Max rent: ", min, 10000);

        for (Property p : properties) {
            if (!p.getArea().equalsIgnoreCase(area)) continue;

            for (Room r : p.getRooms()) {
                if (r.getMonthlyRent() >= min && r.getMonthlyRent() <= max) {
                    System.out.println(p.getAddress() + " | " + r.getRoomType() +
                            " | £" + r.getMonthlyRent() +
                            " | " + r.getAvailableDates());
                }
            }
        }
    }

    private void requestBooking(Student s) {
        String addr = readValidatedString("Property address: ", 5, 100, "TEXT");
        String type = readValidatedString("Room type: ", 3, 30, "TEXT");
        String start = readValidatedString("Start date: ", 10, 10, "TEXT");
        String end = readValidatedString("End date: ", 10, 10, "TEXT");

        for (Property p : properties) {
            if (p.getAddress().equalsIgnoreCase(addr)) {
                for (Room r : p.getRooms()) {
                    if (r.getRoomType().equalsIgnoreCase(type)) {
                        Booking b = new Booking(BookingStatus.PENDING, start, end, s, p);
                        if (bookingController.addBooking(b))
                            System.out.println("Booking requested!");
                        return;
                    }
                }
            }
        }
        System.out.println("Room not found.");
    }

    private void leaveReview(Student s) {
        String addr = readValidatedString("Property address: ", 5, 100, "TEXT");
        int stars = readInt("Stars (1-5): ", 1, 5);
        String comment = readValidatedString("Comment: ", 5, 200, "TEXT");

        for (Property p : properties) {
            if (p.getAddress().equalsIgnoreCase(addr)) {
                s.leaveReview(p, stars, comment, reviewController);
                return;
            }
        }
        System.out.println("Property not found.");
    }
}
