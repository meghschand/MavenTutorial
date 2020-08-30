import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;


public class SeleniumTest {

    private List<Movie> listingMovies = new ArrayList<Movie>();
    private WebDriver driver = new ChromeDriver();

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lenovo\\Downloads\\chromedriver_win32\\chromedriver.exe");
    }

    @Before
    public void setUp() {
        driver.get("https://www.imdb.com/chart/top/");

        for (int rank = 0; rank < 50; rank++) {
            WebElement movieName = driver.findElements(By.xpath("//td[@class='titleColumn']/a")).get(rank);
            WebElement movieRating = driver.findElements(By.xpath("//td[@class='ratingColumn imdbRating']")).get(rank);
            WebElement movieReleaseYear = driver.findElements(By.xpath("//span[@class='secondaryInfo']")).get(rank);

            listingMovies.get(rank).getMovieWebElement().click();
            Thread.sleep(5000);
            WebElement titlename = driver.findElement(By.xpath("//div[@class='title_wrapper']/h1"));

            listingMovies.add(
                    new Movie(rank + 1,
                            movieName.getText(),
                            Integer.parseInt(movieReleaseYear.getText().replace("(", "").replace(")", "")),
                            Float.parseFloat(movieRating.getText()),
                            ));
        }
    }

    @Test
    public void printMovieList() {
        System.out.println("Rank MovieName   Rating   Year");
        for (int rank = 0; rank < 50; rank++) {
            Movie mv = listingMovies.get(rank);
            System.out.println("" + mv.getRank() + " " + mv.getName() + " " + mv.getRating() + " " + mv.getYear());
        }
        Assert.assertTrue(true);
    }

    @Test
    public void movieNameInDetailsTest() {
        List<Movie> notMatchedMovieNames = new ArrayList<Movie>();
        for (int rank = 0; rank < 2; rank++) {
            int attempts = 0;
            while (attempts < 2) {
                try {
                    listingMovies.get(rank).getMovieWebElement().click();
                    Thread.sleep(5000);
                    WebElement titlename = driver.findElement(By.xpath("//div[@class='title_wrapper']/h1"));
                    String titleIndName = titlename.getText().split("\\(")[0].trim();
                    if (!titleIndName.equals(listingMovies.get(rank).getName())) {
                        notMatchedMovieNames.add(listingMovies.get(rank));
                        System.out.println(listingMovies.get(rank).getName() + " is not matching with the name in listing screen");
                    }
                    Thread.sleep(2000);
                    driver.navigate().back();
                    Thread.sleep(2000);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Assert.assertEquals(0, notMatchedMovieNames.size());
    }

    @Test
    public void Cinemacheck() throws InterruptedException {


        for (int i = 0; i < 50; i++) {
            List<WebElement> movieNamesList = driver.findElements(By.xpath("//td[@class='titleColumn']/a"));
            List<WebElement> movieRatingList = driver.findElements(By.xpath("//td[@class='ratingColumn imdbRating']"));
            List<WebElement> movieReleaseyearList = driver.findElements(By.xpath("//span[@class='secondaryInfo']"));
//        List<String> notMatchingDetails = new ArrayList<String>();

            //236. Before Sunset (2004) 8.5

            String movieName = movieNamesList.get(i).getText();
            String movieRating = movieRatingList.get(i).getText();
            String movieReleaseYear = movieReleaseyearList.get(i).getText();
            System.out.println((i + 1) + "." + " " + movieName + "   " + movieReleaseYear + "  " + movieRating);
            Thread.sleep(5000);


            //todo:fetch movie details page
            //todo:get movie name, rating, date of release and reviews from movie details page
            //todo:compare each element from that of listing screen, if not matched, then add moviename to notMatchingDetails

            movieNamesList.get(i).click();
            Thread.sleep(5000);
            WebElement titlename = driver.findElement(By.xpath("//div[@class='title_wrapper']/h1"));
            String titleIndName = titlename.getText().split("\\(")[0].trim();
            WebElement cinemaRating = driver.findElement(By.xpath("//span[@itemprop='ratingValue']"));
            String cinemaIndRating = cinemaRating.getText();
            WebElement titleYear = driver.findElement(By.xpath("//span[@id='titleYear']"));
            String titleIndYear = titleYear.getText();

            try {
                Assert.assertEquals(movieReleaseYear, titleIndYear);
//                Assert.assertEquals(movieName,titleIndName);
//                Assert.assertEquals(movieRating, cinemaIndRating);
            } catch (Exception e) {
                System.out.println("Mismatch");
            }
            Thread.sleep(2000);
            driver.navigate().back();
            Thread.sleep(2000);


        }
//        Assert.assertEquals(0,notMatchingDetails.size());
    }
}
