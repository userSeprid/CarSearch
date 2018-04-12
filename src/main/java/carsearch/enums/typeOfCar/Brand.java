package carsearch.enums.typeOfCar;

public enum Brand {
    BMW("brand-18"), DODGE("brand-40");

    Brand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private String name;
}
