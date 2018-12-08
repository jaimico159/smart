package Sistema;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import structure.Vehicle;
import utilities.VehicleUtilities;;

@WebServlet(
		name="qw",
		urlPatterns = {"/qwww"}
)
@SuppressWarnings("serial")
public class addVehicleServlet extends HttpServlet{
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		VehicleUtilities Cvehicle = new VehicleUtilities();
		Vehicle vehicle = new Vehicle();
		vehicle.setName(name);
		Cvehicle.createVehicle(vehicle);
		
		//agregar redireccion resp.sendRedirect("index.html");
	}
}
