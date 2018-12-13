package utilities;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;

import structure.Location;
import structure.Polygon;
import structure.Vehicle;

public class PolygonUtilities {
	

	public PolygonUtilities() {
		ObjectifyService.begin();
	}
	
	public  void createPolygon(Polygon polygon) {
		ObjectifyService.register(Polygon.class);
	     ofy().save().entity(polygon).now();
	                    
	 }
	
	public  List<Polygon> loadPolygon() {
		ObjectifyService.register(Polygon.class);
		ObjectifyService.begin();
		List<Polygon> polygon = ofy().load().type(Polygon.class).list();

		return polygon;
	}
	
	public  Polygon loadOnePolygon(String polygon) {
		ObjectifyService.register(Polygon.class);
		Long lng = Long.parseLong(polygon);
		Polygon c = ofy (). load (). key (Key.create (Polygon.class, lng)).now();
		return c;
	}

}
