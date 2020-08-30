import org.openqa.selenium.WebElement;

public class MovieDetails {
    private int rank;
    private String name;
    private int year;
    private float rating;

    public MovieDetails(int rank, String name, int year, float rating) {
        this.rank = rank;
        this.name = name;
        this.year = year;
        this.rating = rating;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public MovieDetails() {
    }
}
