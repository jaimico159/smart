package Builder;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Modules.Polygon.buildPolygon2;
import structure.PointOfInterest;
import structure.Polygon;

public class InterestingZoneJson extends AbstractJsonBuilder {
	public List<PointOfInterest> zones;

    public InterestingZoneJson(List<PointOfInterest> zones) {
    	this.zones = new ArrayList<PointOfInterest>();
    	this.zones = zones;
    }

	@Override
	public void build() {
		JSONArray arrayFinal = new JSONArray();
		System.out.println("hola");
		for (PointOfInterest aux : zones) {
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

		entrega.put("points", arrayFinal);

		
	}

}
