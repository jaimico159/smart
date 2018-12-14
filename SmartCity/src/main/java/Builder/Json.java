package Builder;

import org.json.JSONObject;

import Modules.Polygon.buildPolygon2;

import java.util.List;
public class Json<T>{
	
	 private JSONObject entrega;
	 private buildPolygon2 polygon;
	 private List<T> lista;
	 
	 public Json() {
	
	 }
	 public void setJson(JSONObject entrega) {
		 this.entrega = entrega;
	 }
	 public JSONObject getJson() {
		 return entrega;
	 }
	 public void setPolygon(buildPolygon2 polygon) {
		 this.polygon = polygon;
	 }
	 public buildPolygon2 getPolygon() {
		 return polygon;
	 }
	public List<T> getLista() {
		return lista;
	}
	public void setLista(List<T> lista) {
		this.lista = lista;
	}
	 

}
