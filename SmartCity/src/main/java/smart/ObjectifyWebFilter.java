package smart;

import javax.servlet.annotation.WebFilter;

import javax.swing.text.html.HTMLDocument.Iterator;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.Key;
//import com.google.appengine.repackaged.com.google.type.Date;
import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;

import structure.Interesting_Zone;
import structure.Point;
import structure.Vehicle;
import structure.Location;
import structure.Polygon;
import utilities.InterestingZoneUtilities;
import utilities.LocationUtilities;
import utilities.PointUtilities;
import utilities.PolygonUtilities;
import utilities.VehicleUtilities;

/**
 * Filter required by Objectify to clean up any thread-local transaction contexts and pending
 * asynchronous operations that remain at the end of a request.
 */
@WebFilter(urlPatterns = {"/*"})
public class ObjectifyWebFilter extends ObjectifyFilter {
	private PointUtilities Cpoint;
	private InterestingZoneUtilities Czone;
	private VehicleUtilities Cvehicle;
	private LocationUtilities Clocation;
	private PolygonUtilities Cpolygon;
	public ObjectifyWebFilter() {
		Cpoint = new PointUtilities();
		Czone = new InterestingZoneUtilities();
		Cvehicle = new VehicleUtilities();
		Clocation = new LocationUtilities();
		Cpolygon = new PolygonUtilities();
	}

	public void createPoint(double latitude, double longitude, int order) {
		Point point = new Point();
		point.setLatitude(latitude);
		point.setLongitude(longitude);
		point.setOrder(1);
		this.Cpoint.createPoint(point);
	
	}
	public void createVehicle(String name) {
		Vehicle vehicle = new Vehicle();
		vehicle.setName(name);
		Cvehicle.createVehicle(vehicle);
	}
	public void createLocation(String _vehicle,double latitude, double longitude, double speed, double direction ) {
		Date fecha = new Date();
		Location location = new Location();
		Vehicle vehicle = new Vehicle();
		vehicle.setName(_vehicle);
		location.setDatetime2(fecha);
		location.setLongitude(longitude);
		location.setLatitude(latitude);
		location.setDirection(direction);
		location.setSpeed(speed);
		Clocation.createLocation(vehicle, location);
		
	}
	
	public void createZone(String name, String text, double latitude, double longitude) {
		Interesting_Zone zone = new Interesting_Zone();
		zone.setDescription(text);
		zone.setLatitude(latitude);
		zone.setLongitude(longitude);
		zone.setName(name);
		this.Czone.createZone(zone);
	
	}
	
	public void createPolygon(String name, String text, int status ) {
		Polygon polygon = new Polygon();
		List<Point> points = new ArrayList();
		points.add(new Point(16.97274101999902,-33.57421875,1));
		points.add(new Point(11.178401873711785,-26.894531249999996,2));
		points.add(new Point(13.410994034321702,-1.23046875,3));
		points.add(new Point(22.917922936146045,16.69921875,4));
		points.add(new Point(35.02999636902566,-0.52734375,5));
		points.add(new Point(38.54816542304656,-20.91796875,6));
		points.add(new Point(16.97274101999902,-33.57421875,7));
		polygon.setName(name);
		polygon.setDescription(text);
		polygon.setStatus(1);
		polygon.setPoints(points);
		Cpolygon.createPolygon(polygon);
		
	
	
	}
	
	
	public List<Point> loadPointers(){
	List<Object>lista = Cpoint.loadPoint();
	List<Point> Points = new ArrayList<Point>();
	for(int i=0;i<lista.size();i++) {
		Points.add((Point)(lista.get(i)));
	}
	return Points;
	}
	
	public List<Polygon> loadPolygon(){
		List<Object> polygon = Cpolygon.loadPolygon();
		List<Polygon> poly = new ArrayList<Polygon>();

		 for (Object i: polygon) {
			 if(i instanceof Polygon) {
			 poly.add((Polygon)i);}
		    }
		 
		 return poly;
	}
	
	public List<Location> loadHistorialVehicle(String vehicle){
		Vehicle vehiculo = new Vehicle();
		vehiculo.setName(vehicle);
	Iterable<Location>lista = Clocation.loadHistorical(vehiculo);
	List<Location> locations = new ArrayList<Location>();
	
	 for (Object i : lista) {
	        locations.add((Location)i);
	    }
	return locations;
	}
	
	
}