package utilities;
import static com.googlecode.objectify.ObjectifyService.ofy;


import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;

import structure.PointOfInterest;
import structure.Location;
import structure.Vehicle;

public class LocationUtilities {
	

	public LocationUtilities() {
		
	}
	
	public  void createLocation(Vehicle vehicle, Location location) {
		Key<Vehicle> key_vehicle = Key.create(Vehicle.class, vehicle.getName()); 
		location.setVehicle(key_vehicle);
		ObjectifyService.register(Location.class);
		ObjectifyService.begin();
	 
	     ofy().save().entity(location).now();
	                    
	 }
	
	public  void createLocation(Location location) {
		ObjectifyService.register(Location.class);
		ObjectifyService.begin();
	 
	    ofy().save().entity(location).now();
	                    
	 }
	
	public  Iterable<Location> loadHistorical(Vehicle vehicle) {
		ObjectifyService.register(Location.class);
		ObjectifyService.register(Vehicle.class);
		Iterable<Location> subordinates = ofy().load().type(Location.class).filter("id", vehicle);

		return subordinates;
	}
	
	public  List<Object> loadRealList(){
		ObjectifyService.register(Location.class);
		List<Object> locations = ofy().load().list();

		return locations;
		
	}

}
