package pl.coderslab.dao;

import pl.coderslab.model.Order;
import pl.coderslab.service.DbService;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private static OrderDao instance;

    public static OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDao();
        }
        return instance;
    }

    public int saveToDB(Order order) {
        try (Connection connection = DbUtil.getConn()) {

            int manHour = getManHour(order, connection);
            String sql = "INSERT INTO orders(received, planned_repair_date, start_date, end_date, employee_id, order_description, repair_description," +
                    "status, vehicle_id, customer_cost, parts_cost, man_hour, hours_total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String[] generatedColumns = {"ID"};
            PreparedStatement preparedStatement = connection.prepareStatement(sql, generatedColumns);
            preparedStatement.setTimestamp(1, order.getReceived());
            preparedStatement.setTimestamp(2, order.getPlannedRepairDate());
            preparedStatement.setTimestamp(3, order.getStartDate());
            preparedStatement.setTimestamp(4, order.getEndDate());
            preparedStatement.setInt(5, order.getEmployeeId());
            preparedStatement.setString(6, order.getOrderDescription());
            preparedStatement.setString(7, order.getRepairDescription());
            preparedStatement.setString(8, order.getStatus());
            preparedStatement.setInt(9, order.getVehicleId());
            preparedStatement.setDouble(10, order.getCustomerCost());
            preparedStatement.setDouble(11, order.getPartsCost());
            preparedStatement.setInt(12, manHour);
            preparedStatement.setInt(13, order.getHoursTotal());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                order.setId(rs.getInt(1));
            }
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void edit(Order order) {
        try (Connection connection = DbUtil.getConn()) {

            int manHour = getManHour(order,connection);
            String sql = "UPDATE orders SET received =?, planned_repair_date =?, start_date =?, end_date = ?, employee_id = ?, order_description = ?," +
                    "repair_description = ?, status = ?, vehicle_id = ?, customer_cost = ?, parts_cost = ?, man_hour = ?, hours_total = ? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setTimestamp(1, order.getReceived());
            preparedStatement.setTimestamp(2, order.getPlannedRepairDate());
            preparedStatement.setTimestamp(3, order.getStartDate());
            preparedStatement.setTimestamp(4, order.getEndDate());
            preparedStatement.setInt(5, order.getEmployeeId());
            preparedStatement.setString(6, order.getOrderDescription());
            preparedStatement.setString(7, order.getRepairDescription());
            preparedStatement.setString(8, order.getStatus());
            preparedStatement.setInt(9, order.getVehicleId());
            preparedStatement.setDouble(10, order.getCustomerCost());
            preparedStatement.setDouble(11, order.getPartsCost());
            preparedStatement.setInt(12, manHour);
            preparedStatement.setInt(13, order.getHoursTotal());
            preparedStatement.setInt(14, order.getId());
            preparedStatement.executeUpdate();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }

    private int getManHour(Order order, Connection connection) throws SQLException {
        String sql1 = "SELECT man_hour FROM employees where id=?";
        PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
        preparedStatement1.setInt(1, order.getEmployeeId());
        ResultSet resultSet = preparedStatement1.executeQuery();
        int manHour = 0;
        if (resultSet.next()) {
            manHour = resultSet.getInt(1);
        }
        return manHour;
    }

    public static Order findById(int id) {
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(id));
        List<Order> list = prepareOrders("SELECT * FROM orders WHERE id=?", params);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public static List<Order> findByCustomerId(int customerId) {
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(customerId));
        List<Order> list = prepareOrders("SELECT * FROM orders WHERE customer_id=?", params);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    public static List<Order> findByEmployeeId(int employeeId) {
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(employeeId));
        List<Order> list = prepareOrders("SELECT * FROM orders WHERE employee_id=?", params);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    public static List<Order> findAll() {
        return prepareOrders("SELECT * FROM orders", null);
    }


    public static List<Order> findLastItems(int limit) {


        return prepareOrders("SELECT * FROM orders ORDER BY id DESC LIMIT " + limit, null);
    }

    public static void delete(Order order) {
        List<String> params = new ArrayList<>();
        if (order != null) {
            params.add(String.valueOf(order.getId()));
        }
        try {
            DbService.executeUpdate("DELETE FROM orders WHERE id=?", params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private static List<Order> prepareOrders(String q, List<String> params) {
        List<Order> orders = null;
        try {
            List<String[]> list = DbService.getData(q, params);
            orders = new ArrayList<>();
            for (String[] item : list) {
                Order orderItem = new Order();
                orderItem.setId(Integer.parseInt(item[0]));
                orderItem.setReceived(Timestamp.valueOf(item[1]));
                orderItem.setPlannedRepairDate(Timestamp.valueOf(item[2]));
                orderItem.setStartDate(Timestamp.valueOf(item[3]));
                orderItem.setEndDate(Timestamp.valueOf(item[4]));
                orderItem.setEmployeeId(Integer.parseInt(item[5]));
                orderItem.setOrderDescription(item[6]);
                orderItem.setRepairDescription(item[7]);
//                if (Status.values[])
                orderItem.setStatus(item[8]);
                orderItem.setCustomerCost(Double.parseDouble(item[10]));
                orderItem.setPartsCost(Double.parseDouble(item[11]));
                orderItem.setManHour(Integer.parseInt(item[12]));
                orderItem.setHoursTotal(Integer.parseInt(item[13]));
                orders.add(orderItem);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

//    MySQL status enum('Przyjęty', 'Koszty_zatwierdzone', 'W_naprawie', 'Gotowy_do_odbioru', 'Rezygnacja')
//    dodawanie zlecenia,
//    usuwanie zlecenia,
//    edycję zlecenia.
//    zmianę statusu,
//    szczegóły zlecenia.
}
