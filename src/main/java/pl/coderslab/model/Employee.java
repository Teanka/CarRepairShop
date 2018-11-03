package pl.coderslab.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String note;
    private int manHour;

    public Employee(String firstName, String lastName, String address, String phone, String note, int manHour) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.note = note;
        this.manHour = manHour;
    }

    public Employee(){};

    public String getFirstName() {
        return firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getManHour() {
        return manHour;
    }

    public void setManHour(int manHour) {
        this.manHour = manHour;
    }


    public Employee convertListOfStringsToEmployee (List<String> fields){
        Employee employee = new Employee();
        String sId = fields.get(0);
        try {
            employee.setId(Integer.parseInt(fields.get(0)));
            employee.setFirstName(fields.get(1));
            employee.setLastName(fields.get(2));
            employee.setAddress(fields.get(3));
            employee.setPhone(fields.get(4));
            employee.setNote(fields.get(5));
            employee.setManHour(Integer.parseInt(fields.get(6)));
            return  employee;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<String> convertEmployeeToString (Employee employee) {
        List<String> fields = new ArrayList<>();
        fields.add(String.valueOf(id));
        fields.add(firstName);
        fields.add(lastName);
        fields.add(address);
        fields.add(phone);
        fields.add(note);
        fields.add(String.valueOf(manHour));
        return fields;
    }
}
