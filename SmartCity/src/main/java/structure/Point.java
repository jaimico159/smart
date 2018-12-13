package structure;




import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


@Entity
public class Point implements IStructure{
	@Id Long id;
	double latitude;
	double longitude;
	int order;
  
	public Point(double latitude, double longitude, int order){
		this.latitude= latitude;
		this.longitude=longitude;
		this.order=order;
		
	}
	public Point() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}

}