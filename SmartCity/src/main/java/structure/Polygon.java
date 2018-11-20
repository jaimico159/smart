package structure;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Polygon {
	@Id Long id;
	@Index String name;
	Text description;
	int status;
	List<Point> points = new ArrayList<Point>();
}
