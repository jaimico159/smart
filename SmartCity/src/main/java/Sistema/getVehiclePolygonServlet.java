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

import IBuilder.RealVehicleJson;
import structure.Location;
import structure.Vehicle;
import utilities.buildPolygon2;
import utilities.LocationUtilities;
import utilities.PolygonUtilities;
import utilities.VehicleUtilities;

@WebServlet(
		name = "getVehiclePolygon",
		urlPatterns = {"/getRealTimeCars"}
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
		//String idPolygon = "5770237022568448";
		
		VehicleUtilities Cvehicle = new VehicleUtilities();
		//buildPolygon2 buildPolygon = new buildPolygon2();

		PrintWriter writer = resp.getWriter();
	

	
		//structure.Polygon lista = Cpolygon.loadOnePolygon(idPolygon);
	
		/*puntos = lista.getPoints();
	
		 System.out.println(puntos.size());
		 for (Object i: puntos) {
			 if(i instanceof structure.Point ) {
				
				 buildPolygon.addPoint(((structure.Point)i).getLongitude()+","+((structure.Point)i).getLatitude());
			 }
		 }
		 buildPolygon.makePolygon();*/
	
		
		
		List<Vehicle> vehicles = Cvehicle.loadVehicle();
		RealVehicleJson json = new RealVehicleJson(vehicles);
		json.build();
  
      
		 resp.setCharacterEncoding("UTF-8");
	      writer.print(json.getJson());
	      writer.flush();
		
    //aqui va la escritura en JSON
       
	}
}

