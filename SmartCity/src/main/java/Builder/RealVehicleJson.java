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
import utilities.LocationUtilities;

public class RealVehicleJson {
	public JsonBuilder<Vehicle> RealtJson;

	public RealVehicleJson(JsonBuilder<Vehicle> json) {
		this.RealtJson = json;
	    	
	    }
	 public Json<Vehicle> getJson() {
			return this.RealtJson.getJson();
		}

	public void build(List<Vehicle> lista) throws Exception{
		RealtJson.buildList(lista);
		RealtJson.buildObjectJson();
		LocationUtilities locationRetriever = new LocationUtilities();
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