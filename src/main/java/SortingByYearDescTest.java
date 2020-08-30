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
public class SortingByYearDescTest {
    /**
     * The Listing movies.
     */
    private List<Movie> listingByYear = new ArrayList<Movie>();
    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\lenovo\\Downloads\\chromedriver_win32\\chromedriver.exe");
        final WebDriver driver = new ChromeDriver();
        driver.get("https://www.imdb.com/chart/top/?sort=us,desc&mode=simple&page=1");
        final List<WebElement> movieName =
                driver.findElements(By.xpath("//td[@class='titleColumn']/a"));
        final List<WebElement> movieRating =
                driver.findElements(By.xpath("//td[@class='ratingColumn imdbRating']"));
        final List<WebElement> movieReleaseYear =
                driver.findElements(By.xpath("//span[@class='secondaryInfo']"));
        for (int rank = 0; rank < movieName.size(); rank++) {
            listingByYear.add(
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
        final List<Integer> movieRatings = getSortedListByRatings();
        for (int rank = 0; rank < listingByYear.size(); rank++) {
            Assert.assertTrue(listingByYear.get(rank).getYear() == movieRatings.get(rank));
        }
    }
    /**
     * Gets sorted list by ratings.
     *
     * @return the sorted list by ratings
     */
    private List<Integer> getSortedListByRatings() {
        final List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < listingByYear.size(); i++) {
            list.add(listingByYear.get(i).getYear());
        }
        Collections.sort(list, Collections.<Integer>reverseOrder());
        return list;
    }
}