package utilities;
import static com.googlecode.objectify.ObjectifyService.ofy;


import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;
import structure.Vehicle;

public class VehicleUtilities {
	
	public VehicleUtilities() {	}
	
	public  Key<Vehicle> createVehicle(Vehicle vehicle) {
		ObjectifyService.register(Vehicle.class);
		return ofy().save().entity(vehicle).now();                
	}
	
	public  List<Vehicle> loadVehicle() {
		ObjectifyService.register(Vehicle.class);
		List<Vehicle> vehicle = ofy().load().type(Vehicle.class).list();

		return vehicle;
	}

	public Vehicle getVehicle(Key<Vehicle> clave) {
		return ofy().load().key(clave).now();
	}
	
	public Key<Vehicle> updateVehicle(Vehicle vehicle) {
		return ofy().save().entity(vehicle).now();
	}

}
