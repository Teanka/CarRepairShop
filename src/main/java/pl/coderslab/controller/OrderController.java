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

@WebServlet("/orders")
public class OrderController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        if (request.getParameter("received") != null && request.getParameter("planned")!=null&&request.getParameter("employeeId")!=null) {
            Order order = new Order();
            order.setReceived(LocalDate.parse(request.getParameter("received")));
            order.setPlannedRepairDate(LocalDate.parse(request.getParameter("planned")));
            if(request.getParameter("start")!=null && request.getParameter("start").length()>0) {
                order.setStartDate(LocalDate.parse(request.getParameter("start")));
            }
            if(request.getParameter("end")!=null && request.getParameter("end").length()>0) {
                order.setEndDate(LocalDate.parse(request.getParameter("end")));
            }
            if(request.getParameter("employeeId")!=null) {
                order.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
            }
            order.setOrderDescription(request.getParameter("orderDescription"));
            order.setRepairDescription(request.getParameter("repairDescription"));
            if (request.getParameter("status") != null ) {
                order.setStatus(request.getParameter("status"));
            }
            if(request.getParameter("customerCost")!=null&& !request.getParameter("customerCost").isEmpty()){
                order.setCustomerCost(Double.parseDouble(request.getParameter("customerCost")));
            }
            if(request.getParameter("partsCost")!=null &&!request.getParameter("partsCost").isEmpty()){
                order.setPartsCost(Double.parseDouble(request.getParameter("partsCost")));
            }
            if(request.getParameter("hours")!=null &&!request.getParameter("hours").isEmpty()){
                order.setVehicleId(Integer.parseInt(request.getParameter("hours")));
            }
            OrderDao.getInstance().saveToDB(order);

        } else if (request.getParameter("idDel") != null && Integer.parseInt(request.getParameter("idDel")) > 0) {
            Order order = OrderDao.findById(Integer.parseInt(request.getParameter("idDel")));
            if (order != null) {
                OrderDao.delete(order);
            }
        } else if (request.getParameter("idEdit") != null && Integer.parseInt(request.getParameter("idEdit")) > 0) {
            Order order = new Order();
            order.setId(Integer.parseInt(request.getParameter("idEdit")));
            order.setReceived(LocalDate.parse(request.getParameter("receivedEd")));
            order.setPlannedRepairDate(LocalDate.parse(request.getParameter("plannedEd")));
            if(request.getParameter("startEd")!=null && request.getParameter("startEd").length()>0) {
                order.setStartDate(LocalDate.parse(request.getParameter("startEd")));
            }
            if(request.getParameter("endEd")!=null && request.getParameter("endEd").length()>0) {
                order.setEndDate(LocalDate.parse(request.getParameter("endEd")));
            }
            if(request.getParameter("employeeIdEd")!=null) {
                order.setEmployeeId(Integer.parseInt(request.getParameter("employeeIdEd")));
            }
            order.setOrderDescription(request.getParameter("orderDescriptionEd"));
            order.setRepairDescription(request.getParameter("repairDescriptionEd"));
            if (request.getParameter("statusEd") != null ) {
                order.setStatus(request.getParameter("statusEd"));
            }
            if(request.getParameter("customerCostEd")!=null){
                order.setCustomerCost(Double.parseDouble(request.getParameter("customerCostEd")));
            }
            if(request.getParameter("partsCostEd")!=null){
                order.setPartsCost(Double.parseDouble(request.getParameter("partsCostEd")));
            }
            if(request.getParameter("hoursEd")!=null){
                order.setVehicleId(Integer.parseInt(request.getParameter("hoursEd")));
            }
            OrderDao.getInstance().edit(order);
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getAttribute("ordersList")==null){
            List<Order> ordersList = OrderDao.findAll();
            request.setAttribute("ordersList", ordersList);
        }
        request.getRequestDispatcher("/order.jsp").forward(request, response);
    }
}
