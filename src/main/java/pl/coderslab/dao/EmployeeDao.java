package pl.coderslab.dao;

import pl.coderslab.model.Employee;
import pl.coderslab.service.DbService;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private static EmployeeDao instance;

    public static EmployeeDao getInstance(){
        if(instance==null){
            instance = new EmployeeDao();
        }
        return instance;
    }
    public static void save(List<String> params) {
        if (params.get(0) != null && !params.get(0).isEmpty()) {
            try {
                String id = params.get(0);
                params.remove(0);
                params.add(id);
                DbService.executeUpdate("UPDATE employees SET first_name=?, last_name =?, address = ?, phone = ?, note = ?, man_hour = ? WHERE id=?", params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                params.remove(0);
                DbService.insertIntoDatabase("INSERT INTO employees(first_name, last_name, address, phone, note, man_hour) VALUES (?, ?, ?, ?, ?, ?)", params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void edit( Employee employee) {
        try (Connection connection = DbUtil.getConn()) {
            String sql = "UPDATE employees SET first_name=?, last_name =?, address = ?, phone = ?, note = ?, man_hour = ? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getAddress());
            preparedStatement.setString(4, employee.getPhone());
            preparedStatement.setString(5, employee.getNote());
            preparedStatement.setInt(6, employee.getManHour());
            preparedStatement.setInt(7, employee.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public int saveToDB( Employee employee) {
            try (Connection connection = DbUtil.getConn()) {
                String sql = "INSERT INTO employees(first_name, last_name, address, phone, note, man_hour) VALUES (?, ?, ?, ?, ?, ?)";
                String[] generatedColumns = {"ID"};
                PreparedStatement preparedStatement = connection.prepareStatement(sql, generatedColumns);
                preparedStatement.setString(1, employee.getFirstName());
                preparedStatement.setString(2, employee.getLastName());
                preparedStatement.setString(3, employee.getAddress());
                preparedStatement.setString(4, employee.getPhone());
                preparedStatement.setString(5, employee.getNote());
                preparedStatement.setInt(6, employee.getManHour());
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    employee.setId(rs.getInt(1));
                }
                if (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return -1;
        }



    public static Employee findById(int id) {
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        List<Employee> list = prepareEmployees("SELECT * FROM employees WHERE id=?", params);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public static List<Employee> findAll() {
        return prepareEmployees("SELECT * FROM employees", null);
    }


    public static List<Employee> findLastItems(int limit) {


        return prepareEmployees("SELECT * FROM employees ORDER BY id DESC LIMIT " + limit, null);
    }

    public static void delete(Employee employee) {
        List<String> params = new ArrayList<>();
        if(employee!=null) {
            params.add(String.valueOf(employee.getId()));
        }
        try {
            DbService.executeUpdate("DELETE FROM employees WHERE id=?", params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private static List<Employee> prepareEmployees(String q, List<String> params) {
        List<Employee> employees = null;
        try {
            List<String[]> list = DbService.getData(q, params);
            employees = new ArrayList<>();
            for (String[] item : list) {
                Employee employeeItem = new Employee();
                employeeItem.setId(Integer.parseInt(item[0]));
                employeeItem.setFirstName(item[1]);
                employeeItem.setLastName(item[2]);
                employeeItem.setAddress(item[3]);
                employeeItem.setPhone(item[4]);
                employeeItem.setNote(item[5]);
                employeeItem.setManHour(Integer.parseInt(item[6]));
                employees.add(employeeItem);
            }
            return employees;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
//    Aplikacja ma mieć następujące możliwości:
//
//    dodawanie pracownika,
//    przeglądanie pracowników,
//    usuwanie pracownika,
//    edycję pracownika,
//    TODO przeglądanie zleceń danego pracownika.
}
