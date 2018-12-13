package Builder;

import org.json.JSONObject;

import Modules.Polygon.buildPolygon2;

public abstract class AbstractJsonBuilder implements Builder {
	
	 protected JSONObject entrega = new JSONObject();
	 protected buildPolygon2 polygon = new buildPolygon2();
	 
	 public void setJson(JSONObject entrega) {
		 this.entrega = entrega;
	 }
	 public JSONObject getJson() {
		 return entrega;
	 }
	 

}
