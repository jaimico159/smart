package Builder;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Modules.Polygon.buildPolygon2;
import structure.PointOfInterest;
import structure.Polygon;

public class InterestingZoneJson{
	public JsonBuilder<PointOfInterest> IntJson;
	
	public InterestingZoneJson(JsonBuilder<PointOfInterest> json) {
		this.IntJson = json;
	}
	public Json<PointOfInterest> getJson() {
		return this.IntJson.getJson();
	}
	
	public void build(List<PointOfInterest> lista) throws Exception {
		IntJson.buildList(lista);
		IntJson.buildObjectJson();
		JSONArray arrayFinal = new JSONArray();
		System.out.println("hola");
		for (PointOfInterest aux : IntJson.getJson().getLista()) {
			boolean verificador = true;
				
			JSONObject json = new JSONObject();
			JSONArray arrayCoordenadas = new JSONArray();
			if(verificador==true) {
				json.put("id", aux.getId());
				json.put("name",aux.getName());
				arrayCoordenadas.put(aux.getLatitude());
				arrayCoordenadas.put(aux.getLongitude());
				json.put("position",arrayCoordenadas );
				json.put("description", aux.getDescription());
			
				arrayFinal.put(json);
			}		
		}

		IntJson.getJson().getJson().put("points", arrayFinal);

		
	}

}
