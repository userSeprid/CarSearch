package carsearch;

import carsearch.enums.specifications.FuelType;
import carsearch.enums.typeOfCar.BodyType;
import carsearch.enums.typeOfCar.Brand;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class WebPageParsingLogic {

    public enum Test {

        BANNER("css:a.close.unlink");

        private String selector;

        Test(String selector) {
            this.selector = selector;
        }

        WebElement find(WebDriver driver) {
            String[] paths = this.selector.split(":");
            switch (paths[0]) {
                case "css:":
                    return driver.findElement(By.cssSelector(this.selector));
            }
        }
    }

    static {
        System.out.println(new File(".").getAbsoluteFile());
        System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\chromedriver.exe");
    }

    private WebDriver driver;

    WebPageParsingLogic() {
        driver = new ChromeDriver();
        driver.get("https://auto.ria.com/");
        WebElement adBanner = Test.BANNER.find();
        adBanner.click();
        WebElement advansedSearch = driver.findElement(By.cssSelector("a.ext-end"));
        advansedSearch.click();
    }

    public void init(ArrayList<Brand> carMark, ArrayList<FuelType> fuelType, ArrayList<BodyType> bodyTypes) {
        setBodyType(bodyTypes);
        setMark(carMark);
        setFuelType(fuelType);
        WebElement element = driver.findElement(By.cssSelector("button.button.middle"));
        element.click();
        analiseData();
    }

    private void setMark(ArrayList<Brand> carMark) {
        WebElement markInputField = driver.findElement(By.id("brandAutoComplete"));

        //Cancer code here
        markInputField.sendKeys("BMW");
        for (Brand mark :
                carMark) {
            // Here compiler give an exception - org.openqa.selenium.NoSuchElementException: Unable to locate element with ID: brand-16
            WebElement element = driver.findElement(By.cssSelector("li.rubric.bold[title='BMW']"));
            element.click();
        }
    }

    private void setFuelType(ArrayList<FuelType> fuelType) {
        WebElement conyainer = driver.findElement(By.id("engineBlock"));
        for (FuelType type : fuelType) {

            WebElement element = conyainer.findElement(By.cssSelector("label.radio-btn[for='" + type.getType() + "']"));
            element.click();
        }
    }

    private void setBodyType(ArrayList<BodyType> bodyTypes) {
        WebElement expandSection = driver.findElement(By.cssSelector("a.el-selected.open"));
        expandSection.click();
        for (BodyType type :
                bodyTypes) {

            //TODO: Remove this static shit. Make changes in enum-BodyType, replace id's by prepared selectors
            WebElement element = driver.findElement(By.cssSelector("i.icon-typecar-bu-kabriolet"));
            element.click();
        }
    }

    private void analiseData() {
        ArrayList<WebElement> carsRaw = getAllCarsOnPage();
        WebElement goToNextPageButn = driver.findElement(By.cssSelector("a.page-link.js-next.disabled"));
        System.out.println(goToNextPageButn.getLocation().getX() + " " + goToNextPageButn.getLocation().getY());

        //TODO: make possible to go on next page (if available)

        ArrayList<Car> cars = new ArrayList<>();
        for (WebElement element :
                carsRaw) {
            Car car = new Car();

            //TODO: sometimes setter not set proper value. Need fix
            car.setHeader(element.findElement(By.cssSelector("div.item.ticket-title")).getAttribute("title"));
            String carPrice = element.findElement(By.className("price-ticket")).getAttribute("data-main-price") + " " + element.findElement(By.className("price-ticket")).getAttribute("data-main-currency");
            car.setPrice(carPrice);
            car.setDetails(element.findElement(By.cssSelector("ul.unstyle.characteristic")).getText());
            //car.setComment(element.findElement(By.cssSelector("p.descriptions-ticket.show-desc")).getText());
            car.setComment("Degf");
            cars.add(car);
        }
        printData(cars);
    }

    private ArrayList<WebElement> getAllCarsOnPage() {
        ArrayList<WebElement> cars = (ArrayList<WebElement>) driver.findElements(By.cssSelector("section.ticket-item.new__ticket.t.paid"));
        cars.add(driver.findElement(By.cssSelector("div#newautoInformerTop.box-panel.newauto-rotator-top-carousel.newauto-rotator-top-search")));
        cars.addAll(driver.findElements(By.cssSelector("section.ticket-item.new__ticket.t")));
        return cars;
    }

    private void printData(List<Car> carList) {
        for (Car c : carList) {
            System.out.println(c.toString());
        }
    }
}
