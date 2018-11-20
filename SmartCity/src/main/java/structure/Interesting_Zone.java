package structure;

import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Interesting_Zone {
	@Id Long id;
	@Index String name;
	Text description;
	@Index double latitude;
	@Index double longitude;

}
