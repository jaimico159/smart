package utilities;
import static com.googlecode.objectify.ObjectifyService.ofy;


import java.util.List;

import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;
import structure.Vehicle;

public class VehicleUtilities {
	
	public VehicleUtilities() {	}
	
	public  void createVehicle(Vehicle vehicle) {
		ObjectifyService.register(Vehicle.class);
		ObjectifyService.begin(); 
	    ofy().save().entity(vehicle).now();                
	}
	
	public  List<Vehicle> loadVehicle() {
		ObjectifyService.register(Vehicle.class);
		List<Vehicle> vehicle = ofy().load().type(Vehicle.class).list();

		return vehicle;
	}

}
