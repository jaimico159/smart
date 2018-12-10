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
		points.add(new Point(1.1,2.1,1));
		points.add(new Point(2.1,2.1,2));
		points.add(new Point(1.1,5.1,3));
		points.add(new Point(2.1,5.1,4));
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
		 return Cpolygon.loadPolygon();
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