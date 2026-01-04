package studentrentals;

public class Room {

    private String roomType;
    private int monthlyRent;
    private String amenities;
    private String availableDates;
    

    public Room(String roomType, int monthlyRent, String amenities, String availableDates){

        this.roomType = roomType;
        this.monthlyRent = monthlyRent;
        this.amenities = amenities;
        this.availableDates = availableDates;
    }


    public String getRoomType(){
        return roomType;
    }

    public int getMonthlyRent(){
        return monthlyRent;
    }

    public String getAmenities(){
        return amenities;
    }

    public String getAvailableDates(){
        return availableDates;
    }


}






/*future methods
updateAvailability()
 */