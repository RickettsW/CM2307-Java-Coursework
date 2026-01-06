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

    // ------------------- Registration/Login -------------------
    private void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter first name: ");
        String first = scanner.nextLine();
        System.out.print("Enter last name: ");
        String last = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter DOB: ");
        String dob = scanner.nextLine();

        System.out.print("Are you a Student (S) or Homeowner (H)? ");
        String type = scanner.nextLine().toUpperCase();

        if (type.equals("S")) {
            System.out.print("Enter university: ");
            String uni = scanner.nextLine();
            System.out.print("Enter student ID: ");
            String studentId = scanner.nextLine();
            Student student = new Student(username, password, first, last, email, dob, uni, studentId);
            users.add(student);
            System.out.println("Student registered!");
        } else if (type.equals("H")) {
            Homeowner homeowner = new Homeowner(username, password, first, last, email, dob);
            users.add(homeowner);
            System.out.println("Homeowner registered!");
        } else {
            System.out.println("Invalid type");
        }
    }

    private void loginUser() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        for (User u : users) {
            if (u.logIn(username, password)) {
                loggedInUser = u;
                System.out.println("Logged in as " + username);
                return;
            }
        }
        System.out.println("Login failed");
    }

    private void logOff() {
        loggedInUser.logOff();
        loggedInUser = null;
    }

    // ------------------- Homeowner Menu -------------------
    private void homeownerMenu(Homeowner h) {
        System.out.println("\n--- Homeowner Menu ---");
        System.out.println("1. Add Property");
        System.out.println("2. Add Room to Property");
        System.out.println("3. View Bookings");
        System.out.println("4. Logout");
        System.out.print("> ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> addProperty(h);
            case "2" -> addRoom(h);
            case "3" -> bookingController.getHomeownerBooking(h);
            case "4" -> logOff();
            default -> System.out.println("Invalid option");
        }
    }

    private void addProperty(Homeowner h) {
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter postcode: ");
        String postCode = scanner.nextLine();
        System.out.print("Enter description: ");
        String desc = scanner.nextLine();
        System.out.print("Enter area: ");
        String area = scanner.nextLine();

        Property p = new Property(address, postCode, desc, area, h);
        properties.add(p);
        System.out.println("Property added!");
    }

    private void addRoom(Homeowner h) {
        List<Property> myProperties = new ArrayList<>();
        for (Property p : properties) if (p.getHomeowner().equals(h)) myProperties.add(p);

        if (myProperties.isEmpty()) {
            System.out.println("No properties found. Add a property first.");
            return;
        }

        for (int i = 0; i < myProperties.size(); i++)
            System.out.println(i + ": " + myProperties.get(i).getAddress());

        System.out.print("Select property index: ");
        int index = Integer.parseInt(scanner.nextLine());
        Property prop = myProperties.get(index);

        System.out.print("Enter room type: ");
        String type = scanner.nextLine();
        System.out.print("Enter monthly rent: ");
        int rent = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter amenities: ");
        String amen = scanner.nextLine();
        System.out.print("Enter available dates (dd-MM-yyyy to dd-MM-yyyy): ");
        String dates = scanner.nextLine();

        Room r = new Room(type, rent, amen, dates);
        prop.addRoom(r);
        System.out.println("Room added!");
    }

    // ------------------- Student Menu -------------------
    private void studentMenu(Student s) {
        System.out.println("\n--- Student Menu ---");
        System.out.println("1. Search Rooms");
        System.out.println("2. Request Booking");
        System.out.println("3. Leave Review");
        System.out.println("4. Logout");
        System.out.print("> ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> searchRooms();
            case "2" -> requestBooking(s);
            case "3" -> leaveReview(s);
            case "4" -> logOff();
            default -> System.out.println("Invalid option");
        }
    }

    private void searchRooms() {
        System.out.print("Enter area to search: ");
        String area = scanner.nextLine();
        System.out.print("Enter min rent: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter max rent: ");
        int max = Integer.parseInt(scanner.nextLine());

        System.out.println("\n--- Search Results ---");
        for (Property p : properties) {
            if (!p.getArea().equalsIgnoreCase(area)) continue;
            for (Room r : p.getRooms()) {
                if (r.getMonthlyRent() >= min && r.getMonthlyRent() <= max) {
                    System.out.println(p.getAddress() + " | " + r.getRoomType() + " £" + r.getMonthlyRent() +
                            " | Available: " + r.getAvailableDates());
                }
            }
        }
    }

    private void requestBooking(Student s) {
        System.out.print("Enter property address: ");
        String addr = scanner.nextLine();
        System.out.print("Enter room type: ");
        String type = scanner.nextLine();
        System.out.print("Enter start date: ");
        String start = scanner.nextLine();
        System.out.print("Enter end date: ");
        String end = scanner.nextLine();

        for (Property p : properties) {
            if (p.getAddress().equalsIgnoreCase(addr)) {
                for (Room r : p.getRooms()) {
                    if (r.getRoomType().equalsIgnoreCase(type)) {
                        Booking booking = new Booking("PENDING", start, end, s, p);
                        if (bookingController.addBooking(booking))
                            System.out.println("Booking request sent!");
                        return;
                    }
                }
            }
        }
        System.out.println("Room not found.");
    }

    private void leaveReview(Student s) {
        System.out.print("Enter property address: ");
        String addr = scanner.nextLine();
        System.out.print("Enter stars (1-5): ");
        int stars = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter comment: ");
        String comment = scanner.nextLine();

        for (Property p : properties) {
            if (p.getAddress().equalsIgnoreCase(addr)) {
                s.leaveReview(p, stars, comment, reviewController);
                return;
            }
        }
        System.out.println("Property not found.");
    }
}
