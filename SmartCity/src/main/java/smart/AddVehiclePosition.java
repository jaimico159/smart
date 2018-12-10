package smart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.Key;

import structure.Location;
import structure.Point;
import structure.Polygon;
import structure.Vehicle;
import utilities.LocationUtilities;
import utilities.PolygonUtilities;
import utilities.VehicleUtilities;

/**
 * Servlet implementation class AddVehiclePosition
 */
@WebServlet("/AddVehiclePosition")
public class AddVehiclePosition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddVehiclePosition() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Location location = new Location();
		String key = request.getParameter("carKey");
		String speed = request.getParameter("speed");
		String direction = request.getParameter("direction");
		String position = request.getParameter("position");
		String[] coordenada = position.split(",");
		double lat = Double.parseDouble(coordenada[0]);
		double lng = Double.parseDouble(coordenada[1]);
		
		location.setDatetime2(new Date());
		location.setSpeed(Double.parseDouble(speed));
		location.setDirection(Double.parseDouble(direction));
		location.setLatitude(lat);
		location.setLongitude(lng);
		Key<Vehicle> clave = Key.create(Vehicle.class, Long.parseLong(key));
		location.setVehicle(clave);
			
		LocationUtilities persister = new LocationUtilities();
		persister.createLocation(location);
		
	}

}
