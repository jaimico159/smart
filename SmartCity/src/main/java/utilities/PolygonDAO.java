package utilities;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;

import structure.Location;
import structure.Polygon;
import structure.Vehicle;

public class PolygonDAO implements BasicDAO<Polygon>{
	

	public PolygonDAO() {
		ObjectifyService.begin();
	}
	
	@Override
	public Key<Polygon> save(Polygon polygon) {
		ObjectifyService.register(Polygon.class);
		
	    return ofy().save().entity(polygon).now();         
	 }
	
	@Override
	public Polygon get(Key<Polygon> key) {
		ObjectifyService.register(Polygon.class);
		
		return ofy().load().key(key).now();		
	}

	@Override
	public Key<Polygon> update(Polygon entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Key<Polygon> key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public  List<Polygon> getList() {
		ObjectifyService.register(Polygon.class);
		List<Polygon> polygon = ofy().load().type(Polygon.class).list();

		return polygon;
	}


	@Override
	public long getNumberOfElements() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Polygon getFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polygon getLast() {
		// TODO Auto-generated method stub
		return null;
	}

}