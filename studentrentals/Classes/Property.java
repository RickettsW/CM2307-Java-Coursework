package studentrentals.Classes;

import java.util.ArrayList;
import java.util.List;

public class Property {

    private String address;
    private String postCode;
    private String description;
    private String area;
    private Homeowner homeowner;
    private List<Room> rooms;

    public Property(String address, String postCode, String description, String area, Homeowner homeowner){

        this.address = address;
        this.postCode = postCode;
        this.description = description;
        this.area = area;
        this.homeowner = homeowner;
        this.rooms = new ArrayList<>();
    }

    // getters
    public String getAddress() {
        return address;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getDescription() {
        return description;
    }

    public String getArea() {
        return area;
    }

    public Homeowner getHomeowner() {
        return homeowner;
    }

    public void addRoom(Room room){
    rooms.add(room);
    }

    public void removeRoom(Room room){
        rooms.remove(room);
    }

    public List<Room> getRooms(){
        return rooms;
    }

    
}




/*future method
calcAverageStarRating()
did in review controller update uml
 */
