package carsearch.enums.specifications;

public enum FuelType {
    PETROL("fuel-0"), DIESEL("fuel-1"), GAS("fuel-2"), GAS_OR_PETROL("fuel-3"), HYBRID("fuel-4"), ELECTRO("fuel-5"),
    OTHER("fuel-6"), METHANE_GAS("fuel-7"), PROPANE_BUTANE_GAS("fuel-8");

    FuelType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    private String type;
}
