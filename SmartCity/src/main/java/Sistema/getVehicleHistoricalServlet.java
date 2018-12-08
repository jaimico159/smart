package Sistema;


import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.appengine.repackaged.com.google.gson.Gson;
import structure.Location;
import structure.Vehicle;
import utilities.LocationUtilities;



@WebServlet(
		name = "vehicleHistorical",
		urlPatterns = {"/getHistory"}
		)
@SuppressWarnings( "serial" )
public class getVehicleHistoricalServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		LocationUtilities Clocation = new LocationUtilities();
		//String vehicle = req.getParameter("vehicle");
		String vehicle = "auto1";
		Gson gson = new Gson();
		String cadenaJson = "";
		PrintWriter writer = resp.getWriter();
		
		   
	        JSONObject entrega = new JSONObject();
			JSONArray array = new JSONArray();
		Vehicle vehiculo = new Vehicle();
		vehiculo.setName(vehicle);
		Iterable<Location>lista = Clocation.loadHistorical(vehiculo);
		//List<Location> locations = new ArrayList<Location>();
		
		 for (Object i : lista) {
			 JSONObject principal = new JSONObject();
		      //  locations.add((Location)i);
		        cadenaJson+= gson.toJson((Location)i);
		        principal.put("id", ((Location)i).getId());
		        principal.put("direction", ((Location)i).getDirection());
		        principal.put("latitude", ((Location)i).getLatitude());
		        principal.put("longitude", ((Location)i).getLongitude());
		        principal.put("speed", ((Location)i).getSpeed());
		        principal.put("date", ((Location)i).getDatetime2());
		        array.put(principal);
		    }
		
    //aqui va la escritura en JSON
		 entrega.put("locations", array);
		 resp.setCharacterEncoding("UTF-8");
	      writer.print(entrega);
	      writer.flush();
       
	}
}
