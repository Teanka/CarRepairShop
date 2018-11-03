package pl.coderslab.model;

import java.time.LocalDate;

public class Vehicle {
    private int id;
    private String brand;
    private String model;
    private LocalDate productionDate;
    private String registrationNo;
    private LocalDate nextInspection;
    private int customerId;

    public Vehicle(){}

    public Vehicle(String brand, String model, LocalDate productionDate, String registrationNo, LocalDate nextInspection, int customerId) {
        this.brand = brand;
        this.model = model;
        this.productionDate = productionDate;
        this.registrationNo = registrationNo;
        this.nextInspection = nextInspection;
        this.customerId = customerId;
    }

    public Vehicle(int id, String brand, String model, LocalDate productionDate, String registrationNo, LocalDate nextInspection, int customerId) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.productionDate = productionDate;
        this.registrationNo = registrationNo;
        this.nextInspection = nextInspection;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public LocalDate getNextInspection() {
        return nextInspection;
    }

    public void setNextInspection(LocalDate nextInspection) {
        this.nextInspection = nextInspection;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
