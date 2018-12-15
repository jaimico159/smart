package utilities;

import static com.googlecode.objectify.ObjectifyService.ofy;



import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;

import structure.Location;
import structure.Vehicle;

public class VehicleDAO implements BasicDAO<Vehicle>{
	
	public VehicleDAO() {	
		ObjectifyService.register(Vehicle.class);
		ObjectifyService.begin();
	}
	
	@Override
	public Key<Vehicle> save(Vehicle vehicle) {
		return ofy().save().entity(vehicle).now();                
	}
	
	@Override
	public Vehicle get(Key<Vehicle> clave) {
		return ofy().load().key(clave).now();
	}
	
	@Override
	public Key<Vehicle> update(Vehicle vehicle) {
		return ofy().save().entity(vehicle).now();
	}
	
	@Override
	public List<Vehicle> getList() {
		ObjectifyService.register(Vehicle.class);
		ObjectifyService.begin();

		return ofy().load().type(Vehicle.class).list();
	}

	@Override
	public void delete(Key<Vehicle> key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getNumberOfElements() {
		// TODO Auto-generated method stub
		return ofy().load().type(Location.class).count();
	}

	@Override
	public Vehicle getFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vehicle getLast() {
		// TODO Auto-generated method stub
		return null;
	}
}
