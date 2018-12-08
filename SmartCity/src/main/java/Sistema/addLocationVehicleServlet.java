package Sistema;


import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import structure.Location;
import structure.Vehicle;
import utilities.LocationUtilities;

@WebServlet(
		name="addLocation",
		urlPatterns = {"/addLocationVehicle"}
)
@SuppressWarnings("serial")
public class addLocationVehicleServlet extends HttpServlet{
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nameVehicle = req.getParameter("vehicle");
		String speed = req.getParameter("speed");
		String latitude = req.getParameter("latitude");
		String longitude = req.getParameter("longitude");
		String direction = req.getParameter("direction");
		
		LocationUtilities Clocation = new LocationUtilities();
		Date fecha = new Date();
		Location location = new Location();
		Vehicle vehicle = new Vehicle();
		vehicle.setName(nameVehicle);
		location.setDatetime2(fecha);
		location.setLongitude(Double.parseDouble(longitude));
		location.setLatitude(Double.parseDouble(latitude));
		location.setDirection(Double.parseDouble(direction));
		location.setSpeed(Double.parseDouble(speed));
		Clocation.createLocation(vehicle, location);
		
		//agregar redireccion resp.sendRedirect("index.html");
	}
}

