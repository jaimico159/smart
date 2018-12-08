package structure;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Vehicle {
	@Id String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
