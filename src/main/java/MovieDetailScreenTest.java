import java.util.ArrayList;
import java.util.List;

import Model.Movie;
import Model.MovieDetails;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
/**
 * The type Selenium test.
 */
public class MovieDetailScreenTest {
    /**
     * The Listing movies.
     */
    private List<Movie> listingMovies = new ArrayList<Movie>();

    private static boolean setUpCompleted = false;
    /**
     * Sets up.
     *
     * @throws InterruptedException the interrupted exception
     */
    @Before
    public void setUp() throws InterruptedException {
        if (!setUpCompleted) {
            System.setProperty("webdriver.chrome.driver",
                    "C:\\Users\\lenovo\\Downloads\\chromedriver_win32\\chromedriver.exe");
            final WebDriver driver = new ChromeDriver();
            driver.get("https://www.imdb.com/chart/top/");
            for (int rank = 0; rank < 50; rank++) {
                final WebElement movieName =
                        driver.findElements(By.xpath("//td[@class='titleColumn']/a")).get(rank);
                final WebElement movieRating =
                        driver.findElements(By.xpath("//td[@class='ratingColumn imdbRating']")).get(rank);
                final WebElement movieReleaseYear =
                        driver.findElements(By.xpath("//span[@class='secondaryInfo']")).get(rank);
                final Movie movie =
                        new Movie(rank + 1,
                                movieName.getText(),
                                Integer.parseInt(
                                        movieReleaseYear.getText()
                                                .replace("(", "")
                                                .replace(")", "")),
                                Float.parseFloat(movieRating.getText()));
                movieName.click();
                Thread.sleep(5000);
                final WebElement movieNameInDetails =
                        driver.findElement(By.xpath("//div[@class='title_wrapper']/h1"));
                final WebElement cinemaRating =
                        driver.findElement(By.xpath("//span[@itemprop='ratingValue']"));
                final WebElement titleYear = driver.findElement(By.xpath("//span[@id='titleYear']"));
                final MovieDetails movieDetails =
                        new MovieDetails(rank + 1,
                                movieNameInDetails.getText().split("\\(")[0].trim(),
                                Integer.parseInt(titleYear.getText()
                                        .replace("(", "")
                                        .replace(")", "")),
                                Float.parseFloat(cinemaRating.getText()));
                movie.setMovieDetails(movieDetails);
                listingMovies.add(movie);
                Thread.sleep(2000);
                driver.navigate().back();
                Thread.sleep(2000);
            }
            driver.quit();
            setUpCompleted = true;
        }
    }
    /**
     * Model.Movie name in details test.
     */
    @Test
    public void movieNameInDetailsTest() {
        final List<Movie> notMatchedMovieNames = new ArrayList<Movie>();
        for (int rank = 0; rank < listingMovies.size(); rank++) {
            final Movie movie = listingMovies.get(rank);
            if (!movie.getName().equals(movie.getMovieDetails().getName())) {
                notMatchedMovieNames.add(movie);
                System.out.println(
                        movie.getName()
                                .concat(" Model.Movie name mismatch in <- listing and details screen -> ")
                                .concat(movie.getMovieDetails().getName()));
            }
        }
        Assert.assertEquals(0, notMatchedMovieNames.size());
    }
    /**
     * Model.Movie ratings in details test.
     */
    @Test
    public void movieRatingsInDetailsTest() {
        final List<Movie> notMatchedMovieRatings = new ArrayList<Movie>();
        for (int rank = 0; rank < listingMovies.size(); rank++) {
            final Movie movie = listingMovies.get(rank);
            if (movie.getRating() != movie.getMovieDetails().getRating()) {
                notMatchedMovieRatings.add(movie);
                System.out.println(
                        movie.getName()
                                .concat(" ".concat(String.valueOf(movie.getRating())))
                                .concat(" Model.Movie ratings mismatch in <- listing and details screen -> ")
                                .concat(String.valueOf(movie.getMovieDetails().getRating())));
            }
        }
        Assert.assertEquals(0, notMatchedMovieRatings.size());
    }
    /**
     * Model.Movie year in details test.
     */
    @Test
    public void movieYearInDetailsTest() {
        final List<Movie> notMatchedMovieYear = new ArrayList<Movie>();
        for (int rank = 0; rank < listingMovies.size(); rank++) {
            final Movie movie = listingMovies.get(rank);
            if (movie.getYear() != movie.getMovieDetails().getYear()) {
                notMatchedMovieYear.add(movie);
                System.out.println(
                        movie.getName()
                                .concat(" ".concat(String.valueOf(movie.getYear())))
                                .concat(" Model.Movie ratings mismatch in <- listing and details screen -> ")
                                .concat(String.valueOf(movie.getMovieDetails().getYear())));
            }
        }
        Assert.assertEquals(0, notMatchedMovieYear.size());
    }
}
