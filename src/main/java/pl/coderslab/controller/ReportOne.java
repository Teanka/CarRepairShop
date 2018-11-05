package pl.coderslab.controller;

import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Employee;
import pl.coderslab.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/reportone")
public class ReportOne extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
        if (startDate.isAfter(endDate)) {
            response.getWriter().append("Data początkowa musi być przed datą końcową!");
            response.getWriter().append("<br><a href=\"/reportsHour\">Spróbuj jeszcze raz</a>");
        } else {
            List<Employee> employeeList = EmployeeDao.findAll();
            Map<String, Integer> hours = new HashMap<>();
            if(employeeList!=null&&!employeeList.isEmpty()) {
                for (Employee employee : employeeList) {
                    int sum = 0;
                    List<Order> ordersList = OrderDao.findByEmployeeId(employee.getId());
                    if(ordersList!= null && !ordersList.isEmpty()) {
                        for (Order order : ordersList) {
                            if (order.getStartDate().isBefore(endDate) && order.getStartDate().isAfter(startDate)) {
                                sum += order.getHoursTotal();
                            }
                        }
                        hours.put(employee.getFirstName() + " " + employee.getLastName(), sum);
                    }
                }
            }
            request.setAttribute("hours", hours);
            doGet(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        request.getRequestDispatcher("/reportone.jsp").forward(request, response);
    }
}
