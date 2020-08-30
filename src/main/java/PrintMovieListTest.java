import java.util.ArrayList;
import java.util.List;

import Model.Movie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
/**
 * The type Print movie list test.
 */
public class PrintMovieListTest {
    /**
     * The Listing movies.
     */
    private List<Movie> listingMovies = new ArrayList<Movie>();
    /**
     * Sets up.
     *
     */
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\lenovo\\Downloads\\chromedriver_win32\\chromedriver.exe");
        final WebDriver driver = new ChromeDriver();
        driver.get("https://www.imdb.com/chart/top/");
        final List<WebElement> movieName =
                driver.findElements(By.xpath("//td[@class='titleColumn']/a"));
        final List<WebElement> movieRating =
                driver.findElements(By.xpath("//td[@class='ratingColumn imdbRating']"));
        final List<WebElement> movieReleaseYear =
                driver.findElements(By.xpath("//span[@class='secondaryInfo']"));
        for (int rank = 0; rank < movieName.size(); rank++) {
            listingMovies.add(
                    new Movie(rank + 1,
                            movieName.get(rank).getText(),
                            Integer.parseInt(
                                    movieReleaseYear.get(rank).getText()
                                            .replace("(", "")
                                            .replace(")", "")),
                            Float.parseFloat(movieRating.get(rank).getText())));
        }
    }
    /**
     * Print movie list.
     */
    @Test
    public void printMovieList() {
        System.out.println("Rank MovieName   Rating   Year");
        for (int rank = 0; rank < 50; rank++) {
            Movie mv = listingMovies.get(rank);
            System.out.println(
                    "" + mv.getRank() + " " + mv.getName() + " " + mv.getRating() + " " + mv.getYear());
        }
        Assert.assertTrue(true);
    }
}
