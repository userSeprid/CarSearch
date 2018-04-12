package carsearch;

import carsearch.enums.specifications.FuelType;
import carsearch.enums.typeOfCar.BodyType;
import carsearch.enums.typeOfCar.Brand;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.ArrayList;


public class WebPageParsingLogic {

    private WebDriver driver;

    WebPageParsingLogic() {
        driver = new HtmlUnitDriver();
        driver.get("https://auto.ria.com/");
        WebElement advansedSearch = driver.findElement(By.cssSelector("a.ext-end"));
        advansedSearch.click();
        System.out.println(driver.getTitle());
    }

    public void init(ArrayList<Brand> carMark, ArrayList<FuelType> fuelType, ArrayList<BodyType> bodyTypes) {
        setMark(carMark);
        setFuelType(fuelType);
        setBodyType(bodyTypes);
        analiseData();
    }

    private void setMark(ArrayList<Brand> carMark) {
        for (Brand mark :
                carMark) {
            WebElement element = driver.findElement(By.id(mark.getName()));
            element.click();
        }
    }

    private void setFuelType(ArrayList<FuelType> fuelType) {
        for (FuelType type :
                fuelType) {
            WebElement element = driver.findElement(By.id(type.getType()));
            element.click();
        }

    }

    private void setBodyType(ArrayList<BodyType> bodyTypes) {
        for (BodyType type :
                bodyTypes) {
            WebElement element = driver.findElement(By.id(type.getId()));
            element.click();
        }
    }

    private void analiseData() {

    }
}
