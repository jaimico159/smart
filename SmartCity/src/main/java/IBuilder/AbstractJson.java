package IBuilder;

import org.json.JSONObject;

import utilities.buildPolygon2;

public abstract class AbstractJson implements IBuilder {
	
	 protected JSONObject entrega = new JSONObject();
	 protected buildPolygon2 polygon = new buildPolygon2();
	 
	 public void setJson(JSONObject entrega) {
		 this.entrega = entrega;
	 }
	 public JSONObject getJson() {
		 return entrega;
	 }
	 

}
