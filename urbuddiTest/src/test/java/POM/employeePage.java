package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static utils.randomGenerators.Randomnumber;
import static utils.randomGenerators.generateRandomEmail;

public class employeePage {
    WebDriver driver;

    public employeePage(WebDriver driver) {
        this.driver = driver;
    }

    public void to_Add_employee(String a) {
        var ele = driver.findElement(By.xpath("//button[normalize-space(text()) = '"+a+"']"));
        ele.click();
    }
    public void reqiredDetails(String a) {

        String[] fieldArray = a.split(",");
        for (String field : fieldArray) {


            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            switch (field) {
                case "firstName":

                    WebElement firstNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='" + field + "']")));
                    firstNameInput.sendKeys("veera");
                    System.out.println("FILLING FIRSTNAME");
                    break;
                case "lastName":

                    WebElement lastName = driver.findElement(By.xpath("//input[@name='" + field + "']"));
                    System.out.println("FILLING lastname");
                    lastName.sendKeys("swamy");
                    break;
                case "id":
                    WebElement id = driver.findElement(By.xpath("//input[@name='" + field + "']"));
                    System.out.println("filling id");
                    id.sendKeys(""+Randomnumber()+"");
                    break;
                case "email":
                    WebElement email = driver.findElement(By.xpath("//input[@name='" + field + "']"));
                    System.out.println("filling email");
                    email.sendKeys(""+generateRandomEmail()+"");
                    break;
                case "role":
                    WebElement role = driver.findElement(By.xpath("//select[@id='" + field + "']"));
                    System.out.println("selecting role");
                    role.click();
                    var employee = driver.findElement(By.xpath("//option[contains(text(),'Employee')]"));

                    employee.click();
                    break;
                case "password":
                    WebElement password = driver.findElement(By.xpath("//input[@name='" + field + "']"));
                    System.out.println("enter password");
                    password.sendKeys("veeraswamy.762@");
                    break;
                case "dob":
                    WebElement dob = driver.findElement(By.xpath("//input[@name='" + field + "']"));
                    System.out.println("enter dob");
                    dob.sendKeys("26022000");
                    break;
                case "joiningDate":
                    WebElement joiningDate = driver.findElement(By.xpath("//input[@name='" + field + "']"));
                    System.out.println("enter doj");
                    joiningDate.sendKeys("26022020");
                    break;
                case "qualifications":
                    WebElement qualifications = driver.findElement(By.xpath("//select[@id='" + field + "']"));
                    System.out.println("selecting role");
                    qualifications.click();
                    var qualification= driver.findElement(By.xpath("//option[contains(text(),'B.Tech')]"));
                    qualification.click();
                    break;
                case "gender":
                    var gender = driver.findElement(By.xpath("//select[@id='" + field + "']"));
                    System.out.println("selecting role");
                    gender.click();
                    var male = driver.findElement(By.xpath("//option[contains(text(),'Male')]"));
                    male.click();
                    break;
                case "bloodGroup":
                    var bloodGroup = driver.findElement(By.xpath("//select[@id='" + field + "']"));
                    System.out.println("selecting role");
                    bloodGroup.click();
                    var bloodGroups = driver.findElement(By.xpath("//option[contains(text(),'A+')]"));
                    bloodGroups.click();
                    break;
                case "reportingTo":
                    var reportingTo = driver.findElement(By.xpath("//select[@id='" + field + "']"));
                    System.out.println("selecting role");
                    reportingTo.click();
                    var reportingToWhom = driver.findElement(By.xpath("//option[contains(text(),'arunteja.challa@optimworks.com')]"));
                    reportingToWhom.click();
                    break;
                case "salary":
                    WebElement salary = driver.findElement(By.xpath("//input[@name='" + field + "']"));
                    System.out.println("enter salary");
                    salary.sendKeys("120000");
                    break;
                case "department":
                    WebElement department = driver.findElement(By.xpath("//input[@name='" + field + "']"));
                    System.out.println("enter department");
                    department.sendKeys("QA employee");
                    break;
                case "mobileNumber":
                    WebElement mobileNumber = driver.findElement(By.xpath("//input[@name='" + field + "']"));
                    System.out.println("enter mobile");
                    mobileNumber.sendKeys("9442574328");
                    break;
                case "designation":
                    WebElement designation = driver.findElement(By.xpath("//input[@name='" + field + "']"));
                    System.out.println("enter designation");
                    designation.sendKeys("SOFTWARE TEST ENGINEER");
                    break;
                case "location":
                    WebElement location = driver.findElement(By.xpath("//input[@name='" + field + "']"));
                    System.out.println("enter location");
                    location.sendKeys("Hyderabad");
                    break;
                case "Certificates":

                    var certify = driver.findElement(By.xpath("//button[normalize-space(text())='" + field + "']"));
                    certify.click();
                    var certificates = driver.findElement(By.xpath("//label[@for='10th']"));
                    certificates.click();
                    System.out.println("10th submitted");
                    break;
            }
        }
    }
    public void clickOnAddAfterFilling(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement add = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(10));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", add);
        WebElement saved = waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Saved Successfully')]")));
        String prompt_appeared=saved.getText();
        Assert.assertEquals(prompt_appeared,"Saved Successfully");
        System.out.println("user created successful!");
    }
    public void ActiveEmployeesPage() {

    }
    public void ReleasedEmployeesPage() {

    }
}