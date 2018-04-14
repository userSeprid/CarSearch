package carsearch;

public class Car {
    private String header;
    private String price;
    private String details;
    private String comment;

    public Car(String header, String price, String details, String comment) {
        this.header = header;
        this.price = price;
        this.details = details;
        this.comment = comment;
    }

    public Car() {
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Car{" +
                "header='" + header + '\'' +
                ", price = " + price +
                ", details='" + details + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
