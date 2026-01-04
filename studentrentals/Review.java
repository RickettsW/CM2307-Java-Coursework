package studentrentals;

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

}


/*future methods

createReview()
updateReview()
deleteReview()
*/