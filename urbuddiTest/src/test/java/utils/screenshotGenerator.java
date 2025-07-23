
package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.employeeCreation;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class screenshotGenerator {
    private static final Logger logger = LoggerFactory.getLogger(screenshotGenerator.class);


    public static void takeScreenshot(WebDriver driver) {
        try {

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = "screenshot_" + timestamp + ".png";
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path destination = Paths.get("screenshots", fileName);
            Files.createDirectories(destination.getParent());
            Files.copy(screenshot.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved: " + destination.toAbsolutePath());
            byte[] fileContent = Files.readAllBytes(destination);
            Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(fileContent));

        } catch (IOException e) {
            logger.info("screenshot failed");
        }
    }
}
