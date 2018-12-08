package utilities;

import static com.googlecode.objectify.ObjectifyService.ofy;



import java.util.List;
import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;

import structure.Interesting_Zone;

public class InterestingZoneUtilities {


	public InterestingZoneUtilities() {
		
	}
	
	public  void createZone(Interesting_Zone zone) {
		ObjectifyService.register(Interesting_Zone.class);
		ObjectifyService.begin();
	 
	     ofy().save().entity(zone).now();
	                    
	 }
	
	public  List<Object> loadZone() {
		ObjectifyService.register(Interesting_Zone.class);
		List<Object> zones = ofy().load().list();

		return zones;
	}

}
