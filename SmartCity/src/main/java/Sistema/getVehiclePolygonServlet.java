package Sistema;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import structure.Location;
import structure.Vehicle;
import utilities.buildPolygon2;
import utilities.LocationUtilities;
import utilities.PolygonUtilities;
import utilities.VehicleUtilities;

@WebServlet(
		name = "eqdasdasdas",
		urlPatterns = {"/qewqdwadasdd"}
		)
@SuppressWarnings( "serial" )

public class getVehiclePolygonServlet extends HttpServlet{
 
   private List<structure.Point> puntos;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		resp.setContentType("application/json");
		PolygonUtilities Cpolygon = new PolygonUtilities();
		//String idPolygon=req.getParameter("id");
		String idPolygon = "poligono2";
		
		VehicleUtilities Cvehicle = new VehicleUtilities();
		buildPolygon2 buildPolygon = new buildPolygon2();

		PrintWriter writer = resp.getWriter();
	

		LocationUtilities Clocation = new LocationUtilities();
		//Iterable<structure.Polygon>lista = Cpolygon.loadOnePolygon(idPolygon);
		structure.Polygon lista = Cpolygon.loadOnePolygon(idPolygon);
		System.out.println(lista.getName());
		puntos = lista.getPoints();
		System.out.println("hola");
		
		/*for (Object i : lista) {
			System.out.println(((structure.Polygon)i).getName());
		      //  locations.add((Location)i);
		       puntos = ((structure.Polygon) i).getPoints();
		    }*/
		 System.out.println(puntos.size());
		 for (Object i: puntos) {
			 if(i instanceof structure.Point ) {
				 System.out.println(((structure.Point)i).getLatitude()+","+((structure.Point)i).getLongitude());
				 buildPolygon.addPoint(((structure.Point)i).getLatitude()+","+((structure.Point)i).getLongitude());
			 }
		 }
		 buildPolygon.makePolygon();
	
		 
		List<Object> vehicles = Cvehicle.loadVehicle();
        JSONObject principal = new JSONObject();
        JSONObject entrega = new JSONObject();
		//JSONArray array = new JSONArray();
		
		
		for(Object i: vehicles) {
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
		
				verificador = buildPolygon.coordinate_is_inside_polygon(local.get(0).getLatitude(), local.get(0).getLongitude());
				System.out.println(local.get(0).getLatitude()+", "+ local.get(0).getLongitude());
				System.out.println(verificador);
				JSONObject json = new JSONObject();
				if(verificador == true) {
					json.put("id", local.get(0).getId());
					json.put("latitude", local.get(0).getLatitude());
					json.put("longitude", local.get(0).getLongitude());
					json.put("speed", local.get(0).getSpeed());
					json.put("date", local.get(0).getDatetime2());
					json.put("direction", local.get(0).getDirection());
					//array.put(json);
					principal.put(local.get(0).getVehicle().getName(),json);
				
				}
				
			}
		}
		 //principal.put("Cars",array);
		entrega.put("Cars", principal);
		 resp.setCharacterEncoding("UTF-8");
	      writer.print(entrega);
	      writer.flush();
		
    //aqui va la escritura en JSON
       
	}
}

