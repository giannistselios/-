package net.product.dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import net.product.model.Prdct;

public class PrdctDao {

	public int ProductSubmit(Prdct product) throws ClassNotFoundException{
		String InsertProductsSQL = "INSERT INTO product" + " (Barcode, name, colour, description) VALUES " + " (?, ?, ?, ?);";
		String CheckBarcode = "SELECT * FROM product WHERE Barcode='" + Prdct.getBarcode() +"'";
		int result = 0;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
			Connection connectionn;
			try {
				connectionn = DriverManager.getConnection("jdbc:mysql://localhost:3306/products?useSSL=false&useTimeZone=true&serverTimezone=UTC", "root", "123456789!@#$%^&*(");
				Statement ST = connectionn.createStatement();
			    ResultSet RS = ST.executeQuery(CheckBarcode);
			        
			    if(RS.next()) {
			    	result = 1;
			    	return result;
			    }
			    else {
				    PreparedStatement PS = connectionn.prepareStatement(InsertProductsSQL);    
					PS.setString(1, Prdct.getBarcode());
					PS.setString(2, Prdct.getName());
					PS.setString(3, Prdct.getColour());
					PS.setString(4, Prdct.getDescription());
							
				    result = PS.executeUpdate();
				    result = 0;
				    ST.close();
				    connectionn.close();
			    }
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		return result;
	}
	
	public void ProductPrinter(PrintWriter out) {
		//out.println("<%@ page language=\"java\" contentType=\"text/html; charset=ISO-8859-1\" pageEncoding=\"ISO-8859-1\"%>");
		out.println("<!DOCTYPE html>");
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title> Product Database </title>");
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<div align=\"center\">");
	    out.println("<h1>DATABASE OF OUR PRODUCTS</h1>");
	    out.println("<table style=\"with: 80%\">");
	    
	    try {
	        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/products?useSSL=false&useTimeZone=true&serverTimezone=UTC", "root", "123456789!@#$%^&*(");
	        Statement ST = ((java.sql.Connection) con).createStatement();
	        ResultSet rs;

	        rs = ST.executeQuery("SELECT * FROM product");
	        while ( rs.next() ) {
	        	out.println("<tr>");
	            out.println("<td>"+ rs.getString("Barcode") + " " + rs.getString("name") + " " + rs.getString("colour") + " " + rs.getString("description") + "</td>");
	            out.println("</tr>");
	        }
	        con.close();
	        ST.close();
	    } catch (Exception e) {
	        System.err.println("Got an exception! ");
	        System.err.println(e.getMessage());
	    }
	    //out.println("<form action = \"<%= request.getContextPath() %>/add?action=backtofirst\" method = \"post\">");
	    //out.println("<input type=\"submit\" value=\"Go back to add a product!\"/></form>");
	    out.println("</table>");
	    out.println("</div>");
	    out.println("</body>");
	    out.println("</html>");
	    out.close();
		
	}
}
