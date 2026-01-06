
package studentrentals.Controllers;
import java.util.ArrayList;
import java.util.List;
import studentrentals.Classes.Review;
import studentrentals.Classes.Property;


public class ReviewController {


    private List<Review> reviews;
    


    public ReviewController(){
        reviews = new ArrayList<>();


    }
    
    public void addReview(Review review){
        reviews.add(review);
        
    }

    

    public void updateReview(Review review, int newStars, String newComment){
        review.setStarRating(newStars);
        review.setComment(newComment);
    }

    public void deleteReview(Review review){
        reviews.remove(review);
        System.out.println("Review removed");

    }

    public void getReviews(){
        for(Review review : reviews) {
            System.out.println(review);
        }
        
    }

    public void getReviewsProperty(Property property){
        for(Review review : reviews){
            if(review.getReviewedProperty().equals(property)){
                System.out.println(review);
            }
        }


    }

    public double calcAverageStarRating(Property property){
        int count = 0;
        int total = 0;
        for(Review review: reviews){
            if(review.getReviewedProperty().equals(property)){
                total += review.getStarRating();
                count++;
            }
        }
            if (count ==  0){
                return 0;
            }

        return (double) total / count;

        
    }

}


