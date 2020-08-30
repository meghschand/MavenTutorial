package Model;

/**
 * The type Model.Movie details.
 */
public class MovieDetails {
    /**
     * The Rank.
     */
    private int rank;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Year.
     */
    private int year;
    /**
     * The Rating.
     */
    private float rating;
    /**
     * Instantiates a new Model.Movie details.
     *
     * @param rank   the rank
     * @param name   the name
     * @param year   the year
     * @param rating the rating
     */
    public MovieDetails(int rank, String name, int year, float rating) {
        this.rank = rank;
        this.name = name;
        this.year = year;
        this.rating = rating;
    }
    /**
     * Instantiates a new Model.Movie details.
     */
    public MovieDetails() {
    }
    /**
     * Gets rank.
     *
     * @return the rank
     */
    public int getRank() {
        return rank;
    }
    /**
     * Sets rank.
     *
     * @param rank the rank
     */
    public void setRank(int rank) {
        this.rank = rank;
    }
    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets year.
     *
     * @return the year
     */
    public int getYear() {
        return year;
    }
    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(int year) {
        this.year = year;
    }
    /**
     * Gets rating.
     *
     * @return the rating
     */
    public float getRating() {
        return rating;
    }
    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(float rating) {
        this.rating = rating;
    }
}