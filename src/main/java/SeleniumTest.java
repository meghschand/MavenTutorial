import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;


public class SeleniumTest {

    @Test
    public void Cinemacheck() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lenovo\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.imdb.com/chart/top/");


        for (int i = 0; i < 3; i++) {
        List<WebElement> movieNamesList = driver.findElements(By.xpath("//td[@class='titleColumn']/a"));
        List<WebElement> movieRatingList = driver.findElements(By.xpath("//td[@class='ratingColumn imdbRating']"));
        List<WebElement> movieReleaseyearList = driver.findElements(By.xpath("//span[@class='secondaryInfo']"));
//        List<String> notMatchingDetails = new ArrayList<String>();

            //236. Before Sunset (2004) 8.5

            String movieName=movieNamesList.get(i).getText();
            String movieRating=movieRatingList.get(i).getText();
            String movieReleaseYear=movieReleaseyearList.get(i).getText();
            System.out.println(movieName.concat("  ").concat(movieReleaseYear).concat("  ").concat(movieRating));
            Thread.sleep(5000);


            //todo:fetch movie details page
            //todo:get movie name, rating, date of release and reviews from movie details page
            //todo:compare each element from that of listing screen, if not matched, then add moviename to notMatchingDetails

            movieNamesList.get(i).click();
            Thread.sleep(5000);
            WebElement titlename=driver.findElement(By.xpath("//div[@class='title_wrapper']/h1/span[not([@id='titleYear'])]"));
            String titleIndName=titlename.getText();
            WebElement cinemaRating=driver.findElement(By.xpath("//span[@itemprop='ratingValue']"));
            String cinemaIndRating=cinemaRating.getText();
            WebElement titleYear=driver.findElement(By.xpath("//span[@id='titleYear']"));
            String titleIndYear=titleYear.getText();

            try {
                Assert.assertEquals(movieReleaseYear,titleIndYear);
                Assert.assertEquals(movieName,titleIndName);
                Assert.assertEquals(movieRating, cinemaIndRating);
            }
            catch(Exception e)
            {
                System.out.println("Mismatch");
            }
            Thread.sleep(2000);
            driver.navigate().back();
            Thread.sleep(2000);


        }
//        Assert.assertEquals(0,notMatchingDetails.size());
    }
}
