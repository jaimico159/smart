package Builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Modules.Polygon.buildPolygon2;
import structure.Location;
import structure.PointOfInterest;
import structure.Polygon;
import structure.Vehicle;
import utilities.LocationDAO;

public class RealVehicleJson {
	public JsonBuilder<Vehicle> RealtJson;

	public LocationDAO Clocation;
	public RealVehicleJson(List<Vehicle> vehicles, buildPolygon2 polygon) {
		this.vehicles = new ArrayList<Vehicle>();
	    	this.vehicles = vehicles;
	    	Clocation = new LocationDAO();
	    	this.polygon = polygon;
	    	
	    }
	public RealVehicleJson(List<Vehicle> vehicles) {
    	
    	this.vehicles = new ArrayList<Vehicle>();
    	this.vehicles = vehicles;
    	Clocation = new LocationDAO();
    }

	@Override
	public void build() {
		
		LocationDAO locationRetriever = new LocationDAO();
        JSONArray arrayFinal = new JSONArray();
		
		for(Vehicle vehicle: RealtJson.getJson().getLista()) {
			Location location = locationRetriever.get(vehicle.getLastLocation());
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			
			json.put("id", location.getId());
			array.put(location.getLatitude());
			array.put(location.getLongitude());
			json.put("speed", location.getSpeed());
			
			json.put("position", array);
			arrayFinal.put(json);
				
			
		}
		RealtJson.getJson().getJson().put("cars", arrayFinal);
		
		
	}

}