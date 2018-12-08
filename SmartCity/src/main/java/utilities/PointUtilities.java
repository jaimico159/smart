package utilities;
import static com.googlecode.objectify.ObjectifyService.ofy;



import java.util.List;

import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;
import structure.Point;

public class PointUtilities {
	

	public PointUtilities() {
		
	}
	
	public  void createPoint(Point point) {
		ObjectifyService.register(Point.class);
		ObjectifyService.begin();
	 
	     ofy().save().entity(point).now();
	                    
	 }
	
	public  List<Object> loadPoint() {
		ObjectifyService.register(Point.class);
		List<Object> fetched = ofy().load().list();

		return fetched;
	}

}
