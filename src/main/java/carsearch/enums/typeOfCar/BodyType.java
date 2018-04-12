package carsearch.enums.typeOfCar;

public enum BodyType {

    SEDAN("bodystyle-3"), OFFROAD_OR_CROSSOVER("bodystyle-5"), MINIVAN("bodystyle-8"), Hatchback("bodystyle-4"),
    WAGON("bodystyle-2"), COUPE("bodystyle-6"), CARAVAN("bodystyle-254"), CABRIOLET("bodystyle-7"), PICKUP("bodystyle-9"),
    ELEVATORBACK("bodystyle-307"), LIMOUSINE("bodystyle-252"), OTHER("bodystyle-28");

    BodyType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    private String id;
}
