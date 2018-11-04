package pl.coderslab.dao;

import pl.coderslab.model.Customer;
import pl.coderslab.service.DbService;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    private static CustomerDao instance;

    public static CustomerDao getInstance() {
        if (instance == null) {
            instance = new CustomerDao();
        }
        return instance;
    }

    public static void save(List<String> params) {
        if (params.get(0) != null && !params.get(0).isEmpty()) {
            try {
                String id = params.get(0);
                params.remove(0);
                params.add(id);
                DbService.executeUpdate("UPDATE customers SET first_name=?, last_name =?, birth_date = ? WHERE id=?", params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                params.remove(0);
                DbService.insertIntoDatabase("INSERT INTO customers(first_name, last_name, birth_date) VALUES (?, ?, ?)", params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public int saveToDB(Customer customer) {
        try (Connection connection = DbUtil.getConn()) {

            String sql = "INSERT INTO customers(first_name, last_name) VALUES (?, ?)";
            if (customer.getBirthDate() != null) {
                sql = "INSERT INTO customers(first_name, last_name, birth_date) VALUES (?, ?, ?)";
            }
            String[] generatedColumns = {"ID"};
            PreparedStatement preparedStatement = connection.prepareStatement(sql, generatedColumns);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            if (customer.getBirthDate() != null) {
                preparedStatement.setDate(3, java.sql.Date.valueOf(customer.getBirthDate()));
            }
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                customer.setId(rs.getInt(1));
            }
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void edit(Customer customer) {
        try (Connection connection = DbUtil.getConn()) {

            String sql = "UPDATE customers SET first_name=?, last_name =? WHERE id=?";
            if (customer.getBirthDate() != null) {
                sql = "UPDATE customers SET first_name=?, last_name =?, birth_date = ? WHERE id=?";
            }
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            if (customer.getBirthDate() != null) {
                preparedStatement.setDate(3, java.sql.Date.valueOf(customer.getBirthDate()));
                preparedStatement.setInt(4, customer.getId());
                preparedStatement.executeUpdate();
            } else {
                preparedStatement.setInt(3, customer.getId());
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Customer findById(int id) {
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        List<Customer> list = prepareCustomers("SELECT * FROM customers WHERE id=?", params);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public static Customer findByLastName(String lastName) {
        List<String> params = new ArrayList<>();
        params.add(lastName);
        List<Customer> list = prepareCustomers("SELECT * FROM customers WHERE last_name=?", params);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public static List<Customer> findAll() {
        return prepareCustomers("SELECT id, first_name, last_name, birth_date FROM customers", null);
    }


    public static List<Customer> findLastItems(int limit) {


        return prepareCustomers("SELECT * FROM customers ORDER BY id DESC LIMIT " + limit, null);
    }

    public static void delete(Customer customer) {
        List<String> params = new ArrayList<>();
        if (customer != null) {
            params.add(String.valueOf(customer.getId()));
        }
        try {
            DbService.executeUpdate("DELETE FROM customers WHERE id=?", params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private static List<Customer> prepareCustomers(String q, List<String> params) {
        List<Customer> customers = null;
        try {
            List<String[]> list = DbService.getData(q, params);
            customers = new ArrayList<>();
            for (String[] item : list) {
                Customer customerItem = new Customer();
                customerItem.setId(Integer.parseInt(item[0]));
                customerItem.setFirstName(item[1]);
                customerItem.setLastName(item[2]);
                if (item[3] != null) {
                    customerItem.setBirthDate(LocalDate.parse(item[3]));
                }
                customers.add(customerItem);
            }
            return customers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
//    Aplikacja ma mieć następujące możliwości:
//
//    przeglądanie klientów,
//    dodawanie klienta,
//    usuwanie klienta,
//    edycję klienta.
}
