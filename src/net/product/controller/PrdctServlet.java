package net.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.product.dao.PrdctDao;
import net.product.model.Prdct;

/**
 * Servlet implementation class PrdctServlet
 */
@WebServlet("/add")
public class PrdctServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrdctDao productDao = new PrdctDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrdctServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/FirstPage.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("thirdpage")) {
			PrintWriter out = response.getWriter(); 
			response.setContentType("text/html");
			
			productDao.ProductPrinter(out);
		}
		else {
			String Barcode = request.getParameter("Barcode");
			String name = request.getParameter("name");
			String colour = request.getParameter("colour");
			String description = request.getParameter("description");
			int ValueCheck = 3;
			
			Prdct product = new Prdct();
			
			product.setBarcode(Barcode);
			product.setName(name);
			product.setColour(colour);
			product.setDescription(description);
			
			try {
				ValueCheck = productDao.ProductSubmit(product);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(ValueCheck == 0) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/SeccondPage.jsp");
				dispatcher.forward(request, response);
			}
			else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/ThirdPage.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
}
