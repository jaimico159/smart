																																																										package structure;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Vehicle {
	@Id Long id;
	@Index String name;
	@Index Key<Location> last_location;

	public Long getId() {
		return this.id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLastLocation(Key<Location> last_location) {
		this.last_location = last_location;
	}
	public Key<Location> getLastLocation() {
		return this.last_location;
	}
}
