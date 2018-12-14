package API;
import java.io.IOException;
import Builder.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import Builder.JsonBuilder;
import Builder.PolygonJson;
import Builder.RealVehicleJson;
import Modules.Polygon.buildPolygon2;
import structure.Location;
import structure.Polygon;
import structure.Vehicle;
import utilities.LocationUtilities;
import utilities.PointOfInterestUtilities;
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
		List<Vehicle> vehicles = vehicleRetriever.getList();
        Json<Vehicle> json = new Json<Vehicle>();
        try {
        
			JsonBuilder<Vehicle> VehicleReal = new JsonBuilder<Vehicle>();
		
			RealVehicleJson wrapper = new RealVehicleJson(VehicleReal);
			wrapper.build(vehicles);
			json =  wrapper.getJson();
		} catch (Exception e) {
			System.out.println("la lista de vehiculos es nula!");
		}
        
        resp.setCharacterEncoding("UTF-8");
	    writer.print(json.getJson());
	    writer.flush();
       
	}
}

