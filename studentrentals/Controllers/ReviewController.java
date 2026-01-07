package studentrentals.Controllers;

import java.util.ArrayList;
import java.util.List;

import studentrentals.Classes.Review;
import studentrentals.Classes.Property;
import studentrentals.Classes.Homeowner;

public class ReviewController {

    private List<Review> reviews;

    public ReviewController() {
        reviews = new ArrayList<>();
    }

    public void addReview(Review review) {
        reviews.add(review);
        System.out.println("Review added.");
    }

    public void updateReview(Review review, int newStars, String newComment) {
        review.setStarRating(newStars);
        review.setComment(newComment);
    }

    public void deleteReview(Review review) {
        reviews.remove(review);
        System.out.println("Review deleted.");
    }
    //gets all reviews
    public void getReviews() {
        for (Review r : reviews)
            System.out.println(r);
    }
    //gets all reviews for a property
    public void getReviewsProperty(Property property) {
        for (Review r : reviews) {
            if (r.getReviewedProperty().equals(property))
                System.out.println(r);
        }
    }
    //gets all reviews for a certain homeowner
    public void getReviewsByHomeowner(Homeowner homeowner) {
        boolean found = false;

        for (Review r : reviews) {
            if (r.getReviewedProperty().getHomeowner().equals(homeowner)) {
                System.out.println(r);
                found = true;
            }
        }

        if (!found)
            System.out.println("No reviews found.");
    }

    public double calcAverageStarRating(Property property) {
        int total = 0, count = 0;

        for (Review r : reviews) {
            if (r.getReviewedProperty().equals(property)) {
                total += r.getStarRating();
                count++;
            }
        }

        return count == 0 ? 0 : (double) total / count;
        //ternary op no / by 0
    }
}
