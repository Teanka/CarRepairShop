package pl.coderslab.controller;

import pl.coderslab.dao.EmployeeDao;
import pl.coderslab.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/employee")
public class EmployeeController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        if (request.getParameter("firstName") != null && request.getParameter("firstName").length() > 0
                && request.getParameter("lastName") != null && request.getParameter("lastName").length() > 0
                && request.getParameter("address") != null && request.getParameter("address").length() > 0
                && request.getParameter("phone") != null && request.getParameter("phone").length() > 0) {
            Employee employee = new Employee();
            employee.setFirstName(request.getParameter("firstName"));
            employee.setLastName(request.getParameter("lastName"));
            employee.setAddress(request.getParameter("address"));
            employee.setPhone(request.getParameter("phone"));
            if (request.getParameter("note") != null && request.getParameter("note").length() > 0) {
                employee.setNote(request.getParameter("note"));
            }
            try {
                employee.setManHour(Integer.parseInt(request.getParameter("manHour")));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            EmployeeDao.getInstance().saveToDB(employee);

        } else if (request.getParameter("idDel") != null && Integer.parseInt(request.getParameter("idDel")) > 0) {
            Employee employee = EmployeeDao.findById(Integer.parseInt(request.getParameter("idDel")));
            if (employee != null) {
                EmployeeDao.delete(employee);
            }
        } else if (request.getParameter("idEdit") != null && Integer.parseInt(request.getParameter("idEdit")) > 0) {
            Employee employee = new Employee();
            employee.setId(Integer.parseInt(request.getParameter("idEdit")));
            employee.setFirstName(request.getParameter("firstNameEd"));
            employee.setLastName(request.getParameter("lastNameEd"));
            employee.setAddress(request.getParameter("addressEd"));
            employee.setPhone(request.getParameter("phoneEd"));
            if (request.getParameter("noteEd") != null && request.getParameter("noteEd").length() > 0) {
                employee.setNote(request.getParameter("noteEd"));
            }
            try {
                employee.setManHour(Integer.parseInt(request.getParameter("manHourEd")));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            EmployeeDao.getInstance().edit(employee);
        }
//        request.getRequestDispatcher("/employee").forward(request,response);
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Employee> employees = EmployeeDao.findLastItems(2);
        List<Employee> employees = EmployeeDao.findAll();
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/employee.jsp").forward(request, response);
    }
}
