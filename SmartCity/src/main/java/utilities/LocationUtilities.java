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

public class LocationUtilities implements BasicUtilities<Location>{
	
	public LocationUtilities() {
		ObjectifyService.begin();
	}
	
	@Override
	public Key<Location> save(Location location) {
		ObjectifyService.register(Location.class);
		
	    return ofy().save().entity(location).now();             
	 }
	
	@Override
	public Location get(Key<Location> lastLocation) {
		ObjectifyService.register(Location.class);
		return ofy().load().key(lastLocation).now();
	}
	
	@Override
	public Key<Location> update(Location location) {
		ObjectifyService.register(Location.class);
		return ofy().save().entity(location).now();
	}
	
	@Override
	public void delete(Key<Location> key) {
		ObjectifyService.register(Location.class);
		ofy().delete().key(key);
		
	}
	
	@Override
	public List<Location> getList() {
		ObjectifyService.register(Location.class);
		return ofy().load().type(Location.class).list();
		
	}

	@Override
	public long getNumberOfElements() {
		// TODO Auto-generated method stub
		return ofy().load().type(Location.class).count();
	}

	@Override
	public Location getFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location getLast() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Location> loadHistorical(Key<Vehicle> vehicle) {
		ObjectifyService.register(Location.class);
		ObjectifyService.register(Vehicle.class);
		ObjectifyService.begin();
		List<Location> subordinates = ofy().load().type(Location.class).filter("vehicle", vehicle).list();

		return subordinates;
	}

}
