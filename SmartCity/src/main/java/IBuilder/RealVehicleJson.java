package IBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import structure.Location;
import structure.Vehicle;
import utilities.LocationUtilities;
import utilities.buildPolygon2;

public class RealVehicleJson extends AbstractJson {
	public List<Vehicle> vehicles;

	public LocationUtilities Clocation;
	public RealVehicleJson(List<Vehicle> vehicles, buildPolygon2 polygon) {
		this.vehicles = new ArrayList<Vehicle>();
	    	this.vehicles = vehicles;
	    	Clocation = new LocationUtilities();
	    	this.polygon = polygon;
	    	
	    }
	public RealVehicleJson(List<Vehicle> vehicles) {
    	
    	this.vehicles = new ArrayList<Vehicle>();
    	this.vehicles = vehicles;
    	Clocation = new LocationUtilities();
    }

	@Override
	public void build() {
		
		  JSONArray arrayFinal = new JSONArray();
			
			
			for(Vehicle vehicle: vehicles) {
				boolean verificador = true;
				
					//System.out.println(local.get(0).getLatitude());
					//System.out.println(local.get(0).getLongitude());
					
					//verificador = buildPolygon.coordinate_is_inside_polygon(local.get(0).getLongitude(), local.get(0).getLatitude());
					JSONObject json = new JSONObject();
					JSONArray array = new JSONArray();
					    Location aux = Clocation.getLastLocation(vehicle.getLastLocation());
					    
						json.put("id", vehicle.getId());
						array.put(aux.getLatitude());
						array.put(aux.getLongitude());
						json.put("speed", aux.getSpeed());
						
						json.put("position", array);
						arrayFinal.put(json);
					
				
					
				}
			
			 //principal.put("Cars",array);
			entrega.put("cars", arrayFinal);
		
	}

}