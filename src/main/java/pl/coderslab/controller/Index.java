package pl.coderslab.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class Index extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
//        try{
//            ArrayList<Order> currentRepairs = OrderDao.loadAllRepairsByStatusId(DbUtil.getConn(),3);
//            request.setAttribute("currentRepairs", currentRepairs);
            request.getRequestDispatcher("/index.jsp").forward(request,response);
//        }
//        catch(SQLException e){
//            e.printStackTrace();
//        }
    }
}
