import java.util.ArrayList;
import java.util.Collections;
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
 * The type Sorting by ratings test.
 */
public class SortingByRatingsDescTest {
    /**
     * The Listing movies.
     */
    private List<Movie> listingByRating = new ArrayList<Movie>();
    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\lenovo\\Downloads\\chromedriver_win32\\chromedriver.exe");
        final WebDriver driver = new ChromeDriver();
        driver.get("https://www.imdb.com/chart/top/?sort=ir,desc&mode=simple&page=1");
        final List<WebElement> movieName =
                driver.findElements(By.xpath("//td[@class='titleColumn']/a"));
        final List<WebElement> movieRating =
                driver.findElements(By.xpath("//td[@class='ratingColumn imdbRating']"));
        final List<WebElement> movieReleaseYear =
                driver.findElements(By.xpath("//span[@class='secondaryInfo']"));
        for (int rank = 0; rank < movieName.size(); rank++) {
            listingByRating.add(
                    new Movie(rank + 1,
                            movieName.get(rank).getText(),
                            Integer.parseInt(
                                    movieReleaseYear.get(rank).getText()
                                            .replace("(", "")
                                            .replace(")", "")),
                            Float.parseFloat(movieRating.get(rank).getText())));
        }
        driver.quit();
    }
    /**
     * Rating sorting test.
     */
    @Test
    public void ratingSortingTest() {
        final List<Float> movieRatings = getSortedListByRatings();
        for (int rank = 0; rank < listingByRating.size(); rank++) {
            Assert.assertEquals(movieRatings.get(rank), listingByRating.get(rank).getRating(), 0.0);
        }
    }
    /**
     * Gets sorted list by ratings.
     *
     * @return the sorted list by ratings
     */
    private List<Float> getSortedListByRatings() {
        final List<Float> list = new ArrayList<Float>();
        for (int i = 0; i < listingByRating.size(); i++) {
            list.add(listingByRating.get(i).getRating());
        }
        Collections.sort(list, Collections.<Float>reverseOrder());
        return list;
    }
}