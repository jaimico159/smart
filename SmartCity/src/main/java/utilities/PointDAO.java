package utilities;
import static com.googlecode.objectify.ObjectifyService.ofy;




import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;
import structure.Point;

public class PointDAO implements BasicDAO<Point> {
	

	public PointDAO() {
		ObjectifyService.begin();
	}
	
	@Override
	public Key<Point> save(Point point) {
		ObjectifyService.register(Point.class);
		
	    return ofy().save().entity(point).now();
	}

	@Override
	public Point get(Key<Point> key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Key<Point> update(Point entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Key<Point> key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public  List<Point> getList() {
		ObjectifyService.register(Point.class);
		
		return ofy().load().type(Point.class).list();
	}

	@Override
	public long getNumberOfElements() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point getFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point getLast() {
		// TODO Auto-generated method stub
		return null;
	}

}
