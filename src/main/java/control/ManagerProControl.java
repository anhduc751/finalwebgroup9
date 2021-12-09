package control;

import dao.DAO;
import entity.*;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ManagerProControl
 */
@WebServlet("/Manager")
public class ManagerProControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAO managerDao;
       
   
    public ManagerProControl() {
        managerDao = new DAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		useraccount a = (useraccount) session.getAttribute("acc");
		int id = a.getuID();
		List<Product> list = managerDao.listProductBySellId(id);
		List<Category> listCategory = new ArrayList<Category>();
		listCategory = managerDao.listAllCategory();
        request.setAttribute("listCategory", listCategory);
		request.setAttribute("listP", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ManagerProduct.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
