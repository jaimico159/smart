package API;
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
import utilities.LocationDAO;
import utilities.PolygonDAO;
import utilities.VehicleDAO;

@WebServlet(
		name = "getVehiclePolygon",
		urlPatterns = {"/getRealTimeCars"}
		)
@SuppressWarnings( "serial" )

public class getVehiclePolygonServlet extends HttpServlet{
 
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		VehicleDAO vehicleRetriever = new VehicleDAO();
		PrintWriter writer = resp.getWriter();
        RealVehicleJson wrapper = new RealVehicleJson(vehicleRetriever.getList());
        wrapper.build();
        resp.setCharacterEncoding("UTF-8");
	    writer.print(wrapper.getJson());
	    writer.flush();
       
	}
}

