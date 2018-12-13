package utilities;

import static com.googlecode.objectify.ObjectifyService.ofy;



import java.util.List;
import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;

import structure.PointOfInterest;

public class PointOfInterestUtilities {


	public PointOfInterestUtilities() {
		
	}
	
	public  void createPointOfInterest(PointOfInterest zone) {
		ObjectifyService.register(PointOfInterest.class);
		
	     ofy().save().entity(zone).now();
	                    
	 }
	
	public  List<PointOfInterest> loadPointOfInterest() {
		ObjectifyService.register(PointOfInterest.class);
		ObjectifyService.begin();
		List<PointOfInterest> points = ofy().load().type(PointOfInterest.class).list();
		return points;
	}

}
