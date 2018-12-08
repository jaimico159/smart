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
		LocationUtilities Clocation = new LocationUtilities();
		VehicleUtilities Cvehicles = new VehicleUtilities();
		//aqui van las fechas de inicio y fin de la peticion
		//String fechaInicio = req.getParameter("fechaInicio");
		//String fechaFin = req.getParameter("fechaFin");
		String fechaInicio = "02/12/2018";
		String fechaFin = "08/12/2018";
		Date inicio = new Date();;
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
			List<Object> vehicles = Cvehicles.loadVehicle();
			for(Object i: vehicles) {
				
		
				if(i instanceof Vehicle) {
					JSONArray arrayFinal = new JSONArray();
					JSONObject provisional = new JSONObject();
					Iterable<Location>vehicleHis = Clocation.loadHistorical((Vehicle)i);
					 provisional.put("id",((Vehicle)i).getName() );
					for(Object j: vehicleHis) {
						if(j instanceof Location) {
							
							Date date = ((Location)j).getDatetime2();
						
							JSONArray arrayCoordenada = new JSONArray();
							if(date.before(fin)) {
								if(inicio.before(date)) {
						   
					       
					        arrayCoordenada.put(((Location)j).getLongitude());
					        arrayCoordenada.put(((Location)j).getLatitude());
					     
					        
					       arrayFinal.put(arrayCoordenada);
								}
						    	
					        }
						
						}
					}
					//System.out.println(local.get(0).getLatitude());
					//System.out.println(local.get(0).getLongitude());
					  
					    
				provisional.put("history",arrayFinal);
				arrayEntrega.put(provisional);
				}
				
			}
			entrega.put("cars", arrayEntrega);
		
		
    //aqui va la escritura en JSON
		
		 resp.setCharacterEncoding("UTF-8");
	      writer.print(entrega);
	      writer.flush();
       
	}
}
