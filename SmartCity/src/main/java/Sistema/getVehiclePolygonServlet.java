package Sistema;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import Builder.RealVehicleJson;
import Modules.Polygon.buildPolygon2;
import structure.Location;
import structure.Vehicle;
import utilities.LocationUtilities;
import utilities.PolygonUtilities;
import utilities.VehicleUtilities;

@WebServlet(
		name = "getVehiclePolygon",
		urlPatterns = {"/getRealTimeCars"}
		)
@SuppressWarnings( "serial" )

public class getVehiclePolygonServlet extends HttpServlet{
 
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		VehicleUtilities vehicleRetriever = new VehicleUtilities();
		PrintWriter writer = resp.getWriter();
		List<Vehicle> vehicles = vehicleRetriever.loadVehicle();
        
        RealVehicleJson wrapper = new RealVehicleJson(vehicles);
        wrapper.build();
        
        resp.setCharacterEncoding("UTF-8");
	    writer.print(wrapper.getJson());
	    writer.flush();
       
	}
}

