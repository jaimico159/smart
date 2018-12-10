package Sistema;

import java.io.IOException;


import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;


import structure.Vehicle;
import utilities.VehicleUtilities;;

@WebServlet(
		name="getVehicles",
		urlPatterns = {"/getCarInfo"}
)
@SuppressWarnings("serial")
public class getVehiclesServlet extends HttpServlet{
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		
		VehicleUtilities Cvehicle = new VehicleUtilities();
		List<Vehicle>vehicles = Cvehicle.loadVehicle();
		PrintWriter writer = resp.getWriter();
		JSONObject principal = new JSONObject();
		
		JSONArray array = new JSONArray();
		
		 for (Vehicle i: vehicles) {
			 JSONObject json = new JSONObject();
			 if(i instanceof Vehicle) {
				 json.put("name", ((Vehicle)i).getName());
				 array.put(json);
			 }
			  
		    }
		 principal.put("car",array);
		 resp.setCharacterEncoding("UTF-8");
	      writer.print(principal.toString());
	      writer.flush();
		//aqui va el JSON
		//agregar redireccion resp.sendRedirect("index.html");
	}
}