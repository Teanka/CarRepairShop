package pl.coderslab.controller;

import pl.coderslab.dao.CustomerDao;
import pl.coderslab.dao.VehicleDao;
import pl.coderslab.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/vehicle")
public class VehicleController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        if (request.getParameter("customerId") != null && Integer.parseInt(request.getParameter("customerId")) == 0) {
            List<Vehicle> customerVehicles = VehicleDao.findAll();
            request.setAttribute("customerVehicles", customerVehicles);
        } else if (request.getParameter("customerId") != null && Integer.parseInt(request.getParameter("customerId")) > 0) {
            List<Vehicle> customerVehicles = VehicleDao.findByCustomerId(Integer.parseInt(request.getParameter("customerId")));
            request.setAttribute("customerVehicles", customerVehicles);
        }else if (request.getParameter("brand") != null && request.getParameter("brand").length() > 0
                && request.getParameter("model") != null && request.getParameter("model").length() > 0) {
            Vehicle vehicle = new Vehicle();
            vehicle.setBrand(request.getParameter("brand"));
            vehicle.setModel(request.getParameter("model"));
            if (request.getParameter("productionDate") != null && request.getParameter("productionDate").length() > 0) {
                vehicle.setProductionDate(LocalDate.parse(request.getParameter("productionDate")));
            }
            vehicle.setRegistrationNo(request.getParameter("registrationNo"));
            if (request.getParameter("nextInspection") != null && request.getParameter("nextInspection").length() > 0) {
                vehicle.setNextInspection(LocalDate.parse(request.getParameter("nextInspection")));
            }
            if(request.getParameter("customerIdAdd")!=null&& CustomerDao.findById(Integer.parseInt(request.getParameter("customerIdAdd")))!= null){
                vehicle.setCustomerId(Integer.parseInt(request.getParameter("customerIdAdd")));
            }
            VehicleDao.getInstance().saveToDB(vehicle);

        } else if (request.getParameter("idDel") != null && Integer.parseInt(request.getParameter("idDel")) > 0) {
            Vehicle vehicle = VehicleDao.findById(Integer.parseInt(request.getParameter("idDel")));
            if (vehicle != null) {
                VehicleDao.delete(vehicle);
            }
        } else if (request.getParameter("idEdit") != null && Integer.parseInt(request.getParameter("idEdit")) > 0) {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(Integer.parseInt(request.getParameter("idEdit")));
            vehicle.setBrand(request.getParameter("brandEd"));
            vehicle.setModel(request.getParameter("modelEd"));
            if (request.getParameter("productionDateEd") != null && request.getParameter("productionDateEd").length() > 0) {
                vehicle.setProductionDate(LocalDate.parse(request.getParameter("productionDateEd")));
            }
            vehicle.setRegistrationNo(request.getParameter("registrationNoEd"));
            if (request.getParameter("nextInspectionEd") != null && request.getParameter("nextInspectionEd").length() > 0) {
                vehicle.setNextInspection(LocalDate.parse(request.getParameter("nextInspectionEd")));
            }
            if( CustomerDao.findById(Integer.parseInt(request.getParameter("customerIdEd")))!= null){
                vehicle.setCustomerId(Integer.parseInt(request.getParameter("customerIdEd")));
            }
            VehicleDao.getInstance().edit(vehicle);
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getAttribute("customerVehicles")==null){
            List<Vehicle> customerVehicles = new ArrayList<>();
            request.setAttribute("customerVehicles", customerVehicles);
        }
        request.getRequestDispatcher("/vehicle.jsp").forward(request, response);
    }
}
