package utils;

import POM.dashboardPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.Comparator;

public class testRailExcel {
    private static final Logger logger = LoggerFactory.getLogger(testRailExcel.class);
    WebDriver driver;
    public testRailExcel(WebDriver driver){
        this.driver = driver;}
    public void testRailExcelToMail(int a) throws InterruptedException {

        driver.get("https://veeraswamyk2602.testrail.io/index.php?/runs/view/"+a+"");

        driver.findElement(By.id("name")).sendKeys("veeraswamy.kalluri@optimworks.com");
        driver.findElement(By.id("password")).sendKeys("kVSWAMY@2602DOB");
        driver.findElement(By.id("button_primary")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement exportBtn = wait.until(ExpectedConditions.elementToBeClickable(By.className("icon-export")));
        exportBtn.click();
        WebElement excelOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='exportDropdown']/child::ul/child::li[5]")));
        excelOption.click();
        WebElement excelExportOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("exportSubmit")));
        excelExportOption.click();
        Thread.sleep(5000); // or wait until file exists

        driver.quit();


    }
    public static String getLatestExcelFilePath(String folderPath) {
        File dir = new File(folderPath);

        File[] files = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".xlsx"));

        if (files == null || files.length == 0) {
            return null;
        }

        File latestFile = Arrays.stream(files)
                .max(Comparator.comparingLong(File::lastModified))
                .orElse(null);

        return latestFile != null ? latestFile.getAbsolutePath() : null;
    }


}
