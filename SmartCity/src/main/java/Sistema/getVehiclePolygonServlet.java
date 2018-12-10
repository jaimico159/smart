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

import structure.Location;
import structure.Vehicle;
import utilities.buildPolygon2;
import utilities.LocationUtilities;
import utilities.PolygonUtilities;
import utilities.VehicleUtilities;

@WebServlet(
		name = "getVehiclePolygon",
		urlPatterns = {"/getRealVehiclePolygon"}
		)
@SuppressWarnings( "serial" )

public class getVehiclePolygonServlet extends HttpServlet{
 
   private List<structure.Point> puntos;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//esta clase retorna el json con los vehiculos en tiempo real de un poligono
		resp.setContentType("application/json");
		PolygonUtilities Cpolygon = new PolygonUtilities();
		//aca recibe el id del poligono
		//String idPolygon=req.getParameter("id");
		String idPolygon = "5770237022568448";
		
		VehicleUtilities Cvehicle = new VehicleUtilities();
		buildPolygon2 buildPolygon = new buildPolygon2();

		PrintWriter writer = resp.getWriter();
	

		LocationUtilities Clocation = new LocationUtilities();
	
		structure.Polygon lista = Cpolygon.loadOnePolygon(idPolygon);
	
		puntos = lista.getPoints();
	
		 System.out.println(puntos.size());
		 for (Object i: puntos) {
			 if(i instanceof structure.Point ) {
				
				 buildPolygon.addPoint(((structure.Point)i).getLongitude()+","+((structure.Point)i).getLatitude());
			 }
		 }
		 buildPolygon.makePolygon();
	
		 
		List<Vehicle> vehicles = Cvehicle.loadVehicle();
        JSONObject entrega = new JSONObject();
        JSONArray arrayFinal = new JSONArray();
		
		
		for(Vehicle i: vehicles) {
			boolean verificador = true;
			List<Location> local = new ArrayList<Location>();
			if(i instanceof Vehicle) {
				Iterable<Location>vehicleHis = Clocation.loadHistorical((Vehicle)i);
				for(Object j: vehicleHis) {
					if(j instanceof Location) {
					local.add(0,(Location)j);}
				}
				//System.out.println(local.get(0).getLatitude());
				//System.out.println(local.get(0).getLongitude());
				
				verificador = buildPolygon.coordinate_is_inside_polygon(local.get(0).getLongitude(), local.get(0).getLatitude());
				System.out.println(local.get(0).getLatitude()+", "+ local.get(0).getLongitude());
				System.out.println(verificador);
				JSONObject json = new JSONObject();
				JSONArray array = new JSONArray();
				if(verificador == true) {
					json.put("id", local.get(0).getId());
					array.put(local.get(0).getLongitude());
					array.put(local.get(0).getLatitude());
					json.put("speed", local.get(0).getSpeed());
					
					json.put("position", array);
					arrayFinal.put(json);
				}
			
				
			}
		}
		 //principal.put("Cars",array);
		entrega.put("cars", arrayFinal);
		 resp.setCharacterEncoding("UTF-8");
	      writer.print(entrega);
	      writer.flush();
		
    //aqui va la escritura en JSON
       
	}
}

