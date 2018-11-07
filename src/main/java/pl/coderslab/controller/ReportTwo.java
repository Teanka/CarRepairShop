package pl.coderslab.controller;

import pl.coderslab.dao.OrderDao;
import pl.coderslab.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/reporttwo")
public class ReportTwo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
        if (startDate.isAfter(endDate)) {
            response.getWriter().append("Data początkowa musi być przed datą końcową!");
            response.getWriter().append("<br><a href=\"/reportsHour\">Spróbuj jeszcze raz</a>");
        } else {
            List<Order> orderList = OrderDao.findByStatus("Gotowy_do_odbioru");
            if(orderList!=null&&!orderList.isEmpty()) {
                double earnings = 0;
                double partsCost = 0;
                double employeeSalary = 0;
                double profit = 0;
                for (Order order : orderList) {
                    if (order.getEndDate().isAfter(startDate) && order.getEndDate().isBefore(endDate)) {
                        earnings += order.getCustomerCost();
                        partsCost += order.getPartsCost();
                        employeeSalary += order.getHoursTotal() * order.getManHour();
                        double together = (earnings-partsCost)-employeeSalary;
                        profit += together;
                    }
                }
                request.setAttribute("earnings", earnings);
                request.setAttribute("partsCost", partsCost);
                request.setAttribute("employeeSalary", employeeSalary);
                request.setAttribute("profit", profit);
                doGet(request,response);
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        request.getRequestDispatcher("/reporttwo.jsp").forward(request, response);
    }
}
