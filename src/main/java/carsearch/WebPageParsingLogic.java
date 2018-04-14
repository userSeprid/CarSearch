package carsearch;

import carsearch.enums.specifications.FuelType;
import carsearch.enums.typeOfCar.BodyType;
import carsearch.enums.typeOfCar.Brand;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.ArrayList;
import java.util.List;


public class WebPageParsingLogic {

    private WebDriver driver;

    WebPageParsingLogic() {
        driver = new HtmlUnitDriver();
        driver.get("https://auto.ria.com/");
        WebElement advansedSearch = driver.findElement(By.cssSelector("a.ext-end"));
        advansedSearch.click();
    }

    public void init(ArrayList<Brand> carMark, ArrayList<FuelType> fuelType, ArrayList<BodyType> bodyTypes) {
        setMark(carMark);
        setFuelType(fuelType);
        setBodyType(bodyTypes);
        WebElement element = driver.findElement(By.className("button.middle"));
        element.click();
        analiseData();
    }

    private void setMark(ArrayList<Brand> carMark) {
        for (Brand mark :
                carMark) {
            // Here compiler give an exception - org.openqa.selenium.NoSuchElementException: Unable to locate element with ID: brand-16
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
        ArrayList<WebElement> carsRaw = getAllCarsOnPage();
        WebElement goToNextPageButn = driver.findElement(By.className("page-link.js-next"));
        while (goToNextPageButn.isEnabled()) {
            carsRaw.addAll(getAllCarsOnPage());
        }
        ArrayList<Car> cars = new ArrayList<>();
        for (WebElement element :
                carsRaw) {
            Car car = new Car();
            car.setHeader(element.findElement(By.className("item.ticket-title")).getAttribute("title"));
            String carPrice = element.findElement(By.className("price-ticket")).getAttribute("data-main-price") + " " +
                    element.findElement(By.className("price-ticket")).getAttribute("data-main-currency");
            car.setPrice(carPrice);
            car.setDetails(element.findElement(By.className("unstyle characteristic")).getText());
            car.setComment(element.findElement(By.className("descriptions-ticket")).getText());
        }

    }

    private ArrayList<WebElement> getAllCarsOnPage() {
        ArrayList<WebElement> cars = (ArrayList<WebElement>) driver.findElements(By.className("ticket-item.new__ticket.t.paid"));
        cars.add(driver.findElement(By.id("newautoInformerTop")));
        return cars;
    }

    private void printData(List<Car> carList) {
        for (Car c :
                carList) {
            System.out.println(c.toString());
        }
    }
}
