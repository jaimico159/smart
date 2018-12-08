package utilities;
import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;

import structure.Location;
import structure.Polygon;
import structure.Vehicle;

public class PolygonUtilities {
	

	public PolygonUtilities() {
		
	}
	
	public  void createPolygon(Polygon polygon) {
		ObjectifyService.register(Polygon.class);
		ObjectifyService.begin();
	 
	     ofy().save().entity(polygon).now();
	                    
	 }
	
	public  List<Object> loadPolygon() {
		ObjectifyService.register(Polygon.class);
		List<Object> polygon = ofy().load().list();

		return polygon;
	}
	
	public  Polygon loadOnePolygon(String polygon) {
		ObjectifyService.register(Polygon.class);
				
		//Iterable<Polygon> poligon = ofy().load().type(Polygon.class).filter("name", polygon);
		Polygon c = ofy().load().type(Polygon.class).id("5770237022568448").now();
		return c;
	}

}
