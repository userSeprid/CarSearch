package carsearch;

import carsearch.enums.specifications.FuelType;
import carsearch.enums.typeOfCar.BodyType;
import carsearch.enums.typeOfCar.Brand;

import java.util.ArrayList;
import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
       WebPageParsingLogic parsingLogic = new WebPageParsingLogic();
       ArrayList<BodyType> bodyArgs = new ArrayList<>(Arrays.asList(BodyType.CABRIOLET, BodyType.COUPE));
       ArrayList<Brand> markArgs = new ArrayList<>(Arrays.asList(Brand.BMW, Brand.DODGE));
        ArrayList<FuelType> fuelTypes = new ArrayList<>(Arrays.asList(FuelType.DIESEL, FuelType.PETROL));
       parsingLogic.init(markArgs, fuelTypes, bodyArgs);
    }
}
