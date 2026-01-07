package studentrentals.Classes;

public class Review {
    private int starRating;
    private String comment;
    private String reviewDate;
    private Student author;
    private Property reviewedProperty;

    public Review(int starRating, String comment, String reviewDate, Student author, Property reviewedProperty) {
        this.starRating = starRating;
        this.comment = comment;
        this.reviewDate = reviewDate;
        this.author = author;
        this.reviewedProperty = reviewedProperty;
    }

    @Override
    public String toString() {
        return "Review by " + author.getUsername() +  " for " + reviewedProperty.getAddress() +": " + starRating + " stars, comment: \"" + comment + "\"";
}

    //getters
    public int getStarRating() {
        return starRating;
    }

    public String getComment() {
        return comment;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public Student getAuthor() {
        return author;
    }

    public Property getReviewedProperty() {
        return reviewedProperty;
    }
    //setters
    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


        
    

}


/*future methods

createReview()
updateReview()
deleteReview()
ADDED IN REVIEW CONTROLLER UPDATE UML
*/