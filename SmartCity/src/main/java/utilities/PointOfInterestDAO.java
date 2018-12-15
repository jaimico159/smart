package utilities;

import static com.googlecode.objectify.ObjectifyService.ofy;



import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;

import structure.PointOfInterest;

public class PointOfInterestDAO implements BasicDAO<PointOfInterest> {


	public PointOfInterestDAO() {
		ObjectifyService.begin();
	}
	
	@Override
	public Key<PointOfInterest> save(PointOfInterest zone) {
		ObjectifyService.register(PointOfInterest.class);
	    
		return ofy().save().entity(zone).now();	                    
	 }
	
	@Override
	public PointOfInterest get(Key<PointOfInterest> key) {
		
		return ofy().load().key(key).now();
	}

	@Override
	public Key<PointOfInterest> update(PointOfInterest entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Key<PointOfInterest> key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public  List<PointOfInterest> getList() {
		ObjectifyService.register(PointOfInterest.class);
		
		return ofy().load().type(PointOfInterest.class).list();
	}

	@Override
	public long getNumberOfElements() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PointOfInterest getFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PointOfInterest getLast() {
		// TODO Auto-generated method stub
		return null;
	}

}
