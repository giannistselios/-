package Util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.management.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

import Model.Prdct;
//import antlr.collections.List;

/**
 * Servlet implementation class PrdctServletJPA
 */
@WebServlet("/PrdctServletJPA")
public class PrdctServletJPA extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Session HibernateUtil = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrdctServletJPA() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			out.println("<!DOCTYPE html>");
		    out.println("<html>");
		    out.println("<head>");
		    out.println("<title> Product Database </title>");
		    out.println("</head>");
		    out.println("<body>");
		    out.println("<div align=\"center\">");
		    out.println("<h1>DATABASE OF OUR PRODUCTS</h1>");
		    out.println("<table style=\"with: 80%\">");
			
			SessionFactory factory = new Configuration().configure()
					.addAnnotatedClass(Prdct.class)
					.buildSessionFactory();
			
			org.hibernate.Transaction trans = null;
			List<Prdct> list = null;
			
			try(Session session = factory.openSession()){
				trans = session.beginTransaction();
				list = session.createQuery("from Prdct").getResultList();
				trans.commit();
			}catch(Exception e){
				e.printStackTrace();
			}
			for(int i = 0; i < list.size(); i++) {
				out.println("<tr>");
	            out.println("<td>"+ list.get(i).getBarcode() + " " + list.get(i).getName() + " " + list.get(i).getColour() + " " + list.get(i).getDescription() + "</td>");
	            out.println("</tr>");
	        }
			out.println("</table>");
		    out.println("</div>");
		    out.println("</body>");
		    out.println("</html>");
		    out.close();
			factory.close();
		}
		else {
			String Barcode = request.getParameter("Barcode");
			String name = request.getParameter("name");
			String colour = request.getParameter("colour");
			String description = request.getParameter("description");
			
			try {
				Prdct newPrdct = new Prdct(name, Barcode, colour, description);
				

				SessionFactory factory = new Configuration().configure()
										.addAnnotatedClass(Prdct.class)
										.buildSessionFactory();
				
				Session session = factory.getCurrentSession();
				session.beginTransaction();
				
				session.save(newPrdct);
				try {
					// commit the transaction
					session.getTransaction().commit();
				}catch(javax.persistence.PersistenceException exc) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/ThirdPage.jsp");
					dispatcher.forward(request, response);
					factory.close();
					return;
				}
				
				factory.close();
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/SeccondPage.jsp");
				dispatcher.forward(request, response);
			}catch(Exception e){
				System.out.println("An error occurred while connecting MySQL databse");
			}
		}
	}
}
