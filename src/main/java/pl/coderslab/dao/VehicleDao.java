package pl.coderslab.dao;

import pl.coderslab.model.Vehicle;
import pl.coderslab.service.DbService;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    private static VehicleDao instance;

    public static VehicleDao getInstance() {
        if (instance == null) {
            instance = new VehicleDao();
        }
        return instance;
    }


    public int saveToDB(Vehicle vehicle) {
        try (Connection connection = DbUtil.getConn()) {

            String sql = "INSERT INTO vehicles(brand, model, production_date, registration_no, next_inspection, customer_id) VALUES (?, ?, ?, ?, ?, ?)";
            String[] generatedColumns = {"ID"};
            PreparedStatement preparedStatement = connection.prepareStatement(sql, generatedColumns);
            preparedStatement.setString(1, vehicle.getBrand());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setDate(3, java.sql.Date.valueOf(vehicle.getProductionDate()));
            preparedStatement.setString(4, vehicle.getRegistrationNo());
            preparedStatement.setDate(5, java.sql.Date.valueOf(vehicle.getNextInspection()));
            preparedStatement.setInt(6, vehicle.getCustomerId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                vehicle.setId(rs.getInt(1));
            }
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    public void edit(Vehicle vehicle) {
        try (Connection connection = DbUtil.getConn()) {

            String sql = "UPDATE vehicles SET brand=?, model =?, production_date =?, registration_no = ?, next_inspection = ?, customer_id = ? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, vehicle.getBrand());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setDate(3, java.sql.Date.valueOf(vehicle.getProductionDate()));
            preparedStatement.setString(4, vehicle.getRegistrationNo());
            preparedStatement.setDate(5, java.sql.Date.valueOf(vehicle.getNextInspection()));
            preparedStatement.setInt(6, vehicle.getCustomerId());
            preparedStatement.setInt(7, vehicle.getId());
            preparedStatement.executeUpdate();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }

    public static Vehicle findById(int id) {
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        List<Vehicle> list = prepareVehicles("SELECT * FROM vehicles WHERE id=?", params);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public static List<Vehicle> findByCustomerId(int customerId) {
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(customerId));
        List<Vehicle> list = prepareVehicles("SELECT * FROM vehicles WHERE customer_id=?", params);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    public static List<Vehicle> findAll() {
        return prepareVehicles("SELECT * FROM vehicles", null);
    }


    public static List<Vehicle> findLastItems(int limit) {


        return prepareVehicles("SELECT * FROM vehicles ORDER BY id DESC LIMIT " + limit, null);
    }

    public static void delete(Vehicle vehicle) {
        List<String> params = new ArrayList<>();
        if (vehicle != null) {
            params.add(String.valueOf(vehicle.getId()));
        }
        try {
            DbService.executeUpdate("DELETE FROM vehicles WHERE id=?", params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private static List<Vehicle> prepareVehicles(String q, List<String> params) {
        List<Vehicle> vehicles = null;
        try {
            List<String[]> list = DbService.getData(q, params);
            vehicles = new ArrayList<>();
            for (String[] item : list) {
                Vehicle vehicleItem = new Vehicle();
                vehicleItem.setId(Integer.parseInt(item[0]));
                vehicleItem.setBrand(item[1]);
                vehicleItem.setModel(item[2]);
                vehicleItem.setProductionDate(LocalDate.parse(item[3]));
                vehicleItem.setRegistrationNo(item[4]);
                vehicleItem.setNextInspection(LocalDate.parse(item[5]));
                vehicleItem.setCustomerId(Integer.parseInt(item[6]));
                vehicles.add(vehicleItem);
            }
            return vehicles;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

//    Aplikacja ma mieć następujące możliwości:
//
//    przeglądanie pojazdów,
//    dodawanie pojazdu,
//    usuwanie pojazdu,
//    edycję pojazdu,
//    przypisanie pojazdu do klienta.

//    loadAllByUserId,
//    loadById.

}
