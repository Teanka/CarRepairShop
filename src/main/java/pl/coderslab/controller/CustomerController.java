package pl.coderslab.controller;

import pl.coderslab.dao.CustomerDao;
import pl.coderslab.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/customer")
public class CustomerController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");

        if (request.getParameter("firstName") != null && request.getParameter("firstName").length() > 0
                && request.getParameter("lastName") != null && request.getParameter("lastName").length() > 0) {
            Customer customer = new Customer();
            customer.setFirstName(request.getParameter("firstName"));
            customer.setLastName(request.getParameter("lastName"));
            if (request.getParameter("birthDate") != null && request.getParameter("birthDate").length() > 0) {
                customer.setBirthDate(LocalDate.parse(request.getParameter("birthDate")));
            }
            CustomerDao.getInstance().saveToDB(customer);

        } else if (request.getParameter("idDel") != null && Integer.parseInt(request.getParameter("idDel")) > 0) {
            Customer customer = CustomerDao.findById(Integer.parseInt(request.getParameter("idDel")));
            if (customer != null) {
                CustomerDao.delete(customer);
            }
        } else if (request.getParameter("idEdit") != null && Integer.parseInt(request.getParameter("idEdit")) > 0) {
            Customer customer = new Customer();
            customer.setId(Integer.parseInt(request.getParameter("idEdit")));
            customer.setFirstName(request.getParameter("firstNameEd"));
            customer.setLastName(request.getParameter("lastNameEd"));
            if (request.getParameter("birthDateEd") != null && request.getParameter("birthDateEd").length() > 0) {
                customer.setBirthDate(LocalDate.parse(request.getParameter("birthDateEd")));
            }
            CustomerDao.getInstance().edit(customer);
        }
        doGet(request, response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = CustomerDao.findAll();

        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/customer.jsp").forward(request, response);
    }
}
