package System;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Factories.objectFactory;
import structure.Point;
import structure.Polygon;
import structure.Vehicle;
import utilities.PolygonDAO;
import utilities.VehicleDAO;

/**
 * Servlet implementation class AddVehicle
 */
@WebServlet("/AddVehicle")
public class AddVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public objectFactory factory = new objectFactory();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddVehicle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//Vehicle car = factory.makeVehicle();
		Vehicle car = new Vehicle();
		String name = request.getParameter("name");
		car.setName(name);
		System.out.println(car.getName());
		//VehicleUtilities persister = factory.makeVehicleUtilities();
		VehicleDAO persister = new VehicleDAO();
		persister.save(car);
	}

}
