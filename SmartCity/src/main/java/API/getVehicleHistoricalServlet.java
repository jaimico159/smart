package API;

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

import Builder.VehicleHistorical;
import structure.Location;
import structure.Vehicle;
import utilities.LocationDAO;
import utilities.VehicleDAO;

@WebServlet(
		name = "vehicleHistorical",
		urlPatterns = {"/getHistory"}
		)
@SuppressWarnings( "serial" )
public class getVehicleHistoricalServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		VehicleDAO vehiclesRetriever = new VehicleDAO();
		String fechaInicio = "02/12/2018";
		String fechaFin = "31/12/2020";
		Date inicio = new Date();
		try {
			inicio = formatter.parse(fechaInicio);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date fin = new Date();
		try {
			fin = formatter.parse(fechaFin);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		PrintWriter writer = resp.getWriter(); 
        
        List<Vehicle> vehicles = vehiclesRetriever.getList();
		
        VehicleHistorical wrapper = new VehicleHistorical(vehicles, inicio, fin);
        wrapper.build();
		resp.setCharacterEncoding("UTF-8");
        writer.print(wrapper.getJson());
		writer.flush();
   
	}
}
