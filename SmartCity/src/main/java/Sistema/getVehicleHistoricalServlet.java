package Sistema;


import java.io.IOException;


import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import IBuilder.VehicleHistorical;
import structure.Location;
import structure.Vehicle;
import utilities.LocationUtilities;
import utilities.VehicleUtilities;



@WebServlet(
		name = "vehicleHistorical",
		urlPatterns = {"/getHistory"}
		)
@SuppressWarnings( "serial" )
public class getVehicleHistoricalServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//esta clase retorna el historico de todos los vehiculos en un JSON
		resp.setContentType("application/json");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		VehicleUtilities Cvehicles = new VehicleUtilities();
		//aqui van las fechas de inicio y fin de la peticion
		//String fechaInicio = req.getParameter("fechaInicio");
		//String fechaFin = req.getParameter("fechaFin");
		String fechaInicio = "02/12/2018";
		String fechaFin = "08/12/2018";
		Date inicio = new Date();
		try {
			inicio = formatter.parse(fechaInicio);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date fin = new Date();
		try {
			fin = formatter.parse(fechaFin);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrintWriter writer = resp.getWriter(); 
        JSONObject entrega = new JSONObject();
     
		List<Vehicle> vehicles = Cvehicles.loadVehicle();
        
		VehicleHistorical json = new VehicleHistorical(vehicles, inicio, fin);
	
	  resp.setCharacterEncoding("UTF-8");
      writer.print(json.getJson());
      writer.flush();
   
	}
}
