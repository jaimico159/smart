package structure;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Location {
	@Id Long id;
	@Index String vehicle_id;
	Date datetime2;
	@Index double latitude;
	@Index double longitude;
	@Index double speed;
	@Index double direction;

}
