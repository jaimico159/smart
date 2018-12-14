package Builder;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Modules.Polygon.buildPolygon2;
import structure.Point;
import structure.PointOfInterest;
import structure.Polygon;
import structure.Vehicle;

public class ZonePolygonJson{
	
public JsonBuilder<PointOfInterest> IntJson;
	
	public ZonePolygonJson(JsonBuilder<PointOfInterest> json) {
		this.IntJson = json;
	}
	public Json<PointOfInterest> getJson() {
		return this.IntJson.getJson();
	}
    
	public void build(List<PointOfInterest> lista,  buildPolygon2 polygon) throws Exception{
		IntJson.buildList(lista);
		
        JSONArray arrayFinal = new JSONArray();
		for (PointOfInterest aux : IntJson.getJson().getLista()) {
			boolean verificador = false;			
			verificador = polygon.coordinate_is_inside_polygon(aux.getLongitude(), aux.getLatitude());
			JSONObject json = new JSONObject();
			JSONArray arrayCoordenadas = new JSONArray();
			if(verificador==true) {
				json.put("id", aux.getId());
				json.put("name",aux.getName());
				arrayCoordenadas.put(aux.getLongitude());
				arrayCoordenadas.put(aux.getLatitude());
				json.put("position",arrayCoordenadas );
				json.put("description", aux.getDescription());
			
				arrayFinal.put(json);
			}

		
		}
		IntJson.getJson().getJson().put("points", arrayFinal);
		
	}
	

}
