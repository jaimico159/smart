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

import com.googlecode.objectify.Key;

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
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//esta clase retorna el historico de todos los vehiculos en un JSON
		resp.setContentType("application/json");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		LocationUtilities locationRetriever = new LocationUtilities();
		VehicleUtilities vehiclesRetriever = new VehicleUtilities();
		
		VehicleUtilities Cvehicles = new VehicleUtilities();
		//aqui van las fechas de inicio y fin de la peticion
		//String fechaInicio = req.getParameter("fechaInicio");
		//String fechaFin = req.getParameter("fechaFin");
		String fechaInicio = "02/12/2018";
		String fechaFin = "31/12/2020";
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
		JSONArray arrayEntrega = new JSONArray();
		List<Vehicle> vehicles = vehiclesRetriever.loadVehicle();
		for(Vehicle i: vehicles) {
			JSONArray arrayFinal = new JSONArray();
			JSONObject provisional = new JSONObject();
			Key<Vehicle> aux = Key.create(Vehicle.class, i.getId());
			System.out.println(aux.getRaw());
			System.out.println(aux.getId());
			List<Location> history = locationRetriever.loadHistorical(aux);
			provisional.put("id",i.getId());
			for(Location j: history) {
				Date date = j.getDatetime2();
				JSONArray arrayCoordenada = new JSONArray();
				if(date.before(fin)) {
					if(inicio.before(date)) {				  
				        arrayCoordenada.put(j.getLatitude());
				        
				        arrayCoordenada.put(j.getLongitude());
				        arrayFinal.put(arrayCoordenada);
					}
		        }
			}
			provisional.put("history",arrayFinal);
			arrayEntrega.put(provisional);
		}
		entrega.put("cars", arrayEntrega);
		//aqui va la escritura en JSON
	
		resp.setCharacterEncoding("UTF-8");
        writer.print(entrega);
        writer.flush();
     
		List<Vehicle> vehicles = Cvehicles.loadVehicle();
        
		VehicleHistorical json = new VehicleHistorical(vehicles, inicio, fin);
	
	  resp.setCharacterEncoding("UTF-8");
      writer.print(json.getJson());
      writer.flush();
   
	}
}
