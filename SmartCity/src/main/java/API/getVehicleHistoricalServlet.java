package API;

import java.io.IOException;

import Builder.*;
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

import Builder.JsonBuilder;
import Builder.RealVehicleJson;
import Builder.VehicleHistorical;
import Exception.registryError;
import structure.Location;
import structure.Vehicle;
import utilities.LocationUtilities;
import utilities.VehicleUtilities;

@WebServlet(name = "vehicleHistorical", urlPatterns = { "/getHistory" })
@SuppressWarnings("serial")
public class getVehicleHistoricalServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		LocationUtilities locationRetriever = new LocationUtilities();
		VehicleUtilities vehiclesRetriever = new VehicleUtilities();
		String fechaInicio = "02/12/2018";
		String fechaFin = "31/12/2020";
		Date inicio = new Date();
		try {
			inicio = formatter.parse(fechaInicio);
		} catch (ParseException e) {
			System.out.println("El formato de la fecha no es valido");
		}
		Date fin = new Date();
		try {
			fin = formatter.parse(fechaFin);
		} catch (ParseException e) {
			System.out.println("El formato de la fecha no es valido");
		}

		PrintWriter writer = resp.getWriter();
        Json<Vehicle> json = new Json<Vehicle>();
		try {
			if (fin.before(inicio)) {

				throw new registryError("La fecha fin no puede ser antes de la de inicio!");
			}

			JsonBuilder<Vehicle> VehicleHist = new JsonBuilder<Vehicle>();
			List<Vehicle> vehicles = vehiclesRetriever.getList();
			
			VehicleHistorical wrapper = new VehicleHistorical(VehicleHist);
			wrapper.build(vehicles, inicio,fin);
		
			json =  wrapper.getJson();
			resp.setCharacterEncoding("UTF-8");
			writer.print(wrapper.getJson().getJson());

		} catch (registryError e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("La lista vehicles es nula!");
		} finally {
			writer.flush();
		}

	}
}
