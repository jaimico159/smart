																																																																																																																package structure;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Location {
	@Id Long id;
	@Index Key<Vehicle> vehicle;
	@Index Date datetime2;
	@Index double latitude;
	@Index double longitude;
	@Index double speed;
	@Index double direction;
	public Location() {
		ObjectifyFactory f = new ObjectifyFactory();
	    Key<Location> key = f.allocateId(Location.class);
	    this.setId(key.getId());
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Key<Vehicle> getVehicle() {
		return vehicle;
	}
	public void setVehicle(Key<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}
	public Date getDatetime2() {
		return datetime2;
	}
	public void setDatetime2(Date datetime2) {
		this.datetime2 = datetime2;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getDirection() {
		return direction;
	}
	public void setDirection(double direction) {
		this.direction = direction;
	}
}
