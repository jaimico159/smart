package IBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import structure.Location;
import structure.Polygon;
import structure.Vehicle;
import utilities.LocationUtilities;
import utilities.buildPolygon2;

public class VehicleHistorical extends AbstractJson {
	public Iterable<Vehicle> vehicles;
	public Date inicio;
	public Date fin;
	
	public VehicleHistorical(List<Vehicle> vehicles, Date inicio, Date fin) {
		this.vehicles = new ArrayList<Vehicle>();
	    	this.vehicles = vehicles;
	    	this.inicio = inicio;
	    	this.fin = fin;
	
	    }

	@Override
	public void build() {
		
		LocationUtilities Clocation = new LocationUtilities();
		JSONArray arrayEntrega = new JSONArray();
		for(Vehicle i: vehicles) {
			JSONArray arrayFinal = new JSONArray();
			JSONObject provisional = new JSONObject();
			Iterable<Location>vehicleHis = Clocation.loadHistorical((Vehicle)i);
			provisional.put("id",((Vehicle)i).getName() );
			for(Object j: vehicleHis) {
				if(j instanceof Location) {	
					Date date = ((Location)j).getDatetime2();
					JSONArray arrayCoordenada = new JSONArray();
					if(date.before(fin)) {
						if(inicio.before(date)) {				       
					        arrayCoordenada.put(((Location)j).getLongitude());
					        arrayCoordenada.put(((Location)j).getLatitude());
					        arrayFinal.put(arrayCoordenada);
						}
			        }
				}
			}
			//System.out.println(local.get(0).getLatitude());
			//System.out.println(local.get(0).getLongitude());  
			provisional.put("history",arrayFinal);
			arrayEntrega.put(provisional);
		}
		entrega.put("cars", arrayEntrega);
		//aqui va la escritura en JSON
		
	}

}
