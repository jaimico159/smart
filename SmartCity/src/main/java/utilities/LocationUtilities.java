package utilities;
import static com.googlecode.objectify.ObjectifyService.ofy;


import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;

import structure.PointOfInterest;
import structure.Polygon;
import structure.Location;
import structure.Vehicle;

public class LocationUtilities {
	
	public LocationUtilities() {
		ObjectifyService.begin();
	}
	
	public Key<Location> createLocation(Location location) {
		ObjectifyService.register(Location.class);
	
	    return ofy().save().entity(location).now();
	                    
	 }
	
	public  Iterable<Location> loadHistorical(Vehicle vehicle) {
		ObjectifyService.register(Location.class);
		ObjectifyService.register(Vehicle.class);
		Iterable<Location> subordinates = ofy().load().type(Location.class).filterKey(vehicle);

		return subordinates;
	}
	
	public  Iterable<Location> loadLast(Vehicle vehicle) {
		ObjectifyService.register(Location.class);
		ObjectifyService.register(Vehicle.class);
		Iterable<Location> subordinates = ofy().load().type(Location.class).filterKey(vehicle);

		return subordinates;
	}
	
	public  List<Object> loadRealList(){
		ObjectifyService.register(Location.class);
		List<Object> locations = ofy().load().list();

		return locations;
		
	}
	public Location getLastLocation(Key<Location> clave) {
		ObjectifyService.register(Location.class);
		return ofy().load().key(clave).now();
	}

}
