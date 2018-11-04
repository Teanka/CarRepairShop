package pl.coderslab.model;

import java.sql.Timestamp;

public class Order {
    private int id;
    private Timestamp received;
    private Timestamp plannedRepairDate;
    private Timestamp startDate;
    private Timestamp endDate;
    private int employeeId;//foreign key
    private String orderDescription;
    private String repairDescription;
    private String status;
    private int vehicleId;//foreign key
    private double customerCost;
    private double partsCost;
    private int manHour;// z pola employee manHour
    private int hoursTotal;

    public Order() {
    }

    public Order(int id, Timestamp received, Timestamp plannedRepairDate, int employeeId, String orderDescription,
                 String status, int vehicleId) {
        this.id = id;
        this.received = received;
        this.plannedRepairDate = plannedRepairDate;
        this.employeeId = employeeId;
        this.orderDescription = orderDescription;
        this.status = status;
        this.vehicleId = vehicleId;
    }

    public Order(int id, Timestamp received, Timestamp plannedRepairDate, Timestamp startDate, int employeeId,
                 String orderDescription, String status, int vehicleId) {
        this.id = id;
        this.received = received;
        this.plannedRepairDate = plannedRepairDate;
        this.startDate = startDate;
        this.employeeId = employeeId;
        this.orderDescription = orderDescription;
        this.status = status;
        this.vehicleId = vehicleId;
    }

    public Order(int id, Timestamp received, Timestamp plannedRepairDate, Timestamp startDate, Timestamp endDate,
                 int employeeId, String orderDescription, String repairDescription, String status, int vehicleId,
                 double customerCost, double partsCost, int manHour, int hoursTotal) {
        this.id = id;
        this.received = received;
        this.plannedRepairDate = plannedRepairDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employeeId = employeeId;
        this.orderDescription = orderDescription;
        this.repairDescription = repairDescription;
        this.status = status;
        this.vehicleId = vehicleId;
        this.customerCost = customerCost;
        this.partsCost = partsCost;
        this.manHour = manHour;
        this.hoursTotal = hoursTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getReceived() {
        return received;
    }

    public void setReceived(Timestamp received) {
        this.received = received;
    }

    public Timestamp getPlannedRepairDate() {
        return plannedRepairDate;
    }

    public void setPlannedRepairDate(Timestamp plannedRepairDate) {
        this.plannedRepairDate = plannedRepairDate;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public String getRepairDescription() {
        return repairDescription;
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getCustomerCost() {
        return customerCost;
    }

    public void setCustomerCost(double customerCost) {
        this.customerCost = customerCost;
    }

    public double getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(double partsCost) {
        this.partsCost = partsCost;
    }

    public int getManHour() {
        return manHour;
    }

    public void setManHour(int manHour) {
        this.manHour = manHour;
    }

    public int getHoursTotal() {
        return hoursTotal;
    }

    public void setHoursTotal(int hoursTotal) {
        this.hoursTotal = hoursTotal;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", received=" + received +
                ", plannedRepairDate=" + plannedRepairDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", employeeId=" + employeeId +
                ", orderDescription='" + orderDescription + '\'' +
                ", repairDescription='" + repairDescription + '\'' +
                ", status=" + status +
                ", vehicleId=" + vehicleId +
                ", customerCost=" + customerCost +
                ", partsCost=" + partsCost +
                ", manHour=" + manHour +
                ", hoursTotal=" + hoursTotal +
                '}';
    }
}




//    Data przyjęcia do naprawy
//        Planowana data rozpoczęcia naprawy
//        Data rozpoczęcia do naprawy
//        Przypisany do naprawy pracownik czyli employee_id foreign key
//        Opis problemu
//        Opis naprawy
//        Status foreign key status_id
//        Pojazd którego dotyczy naprawa
//
//        Koszt naprawy dla klienta
//        Koszt wykorzystanych części
//        Koszt roboczogodziny (informacja przepisywana z kosztu roboczogodziny pracownika wykonującego naprawę)
//        Ilość roboczogodzin

