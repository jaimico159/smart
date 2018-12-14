package Builder;

import java.util.List;

import org.json.JSONObject;


public class JsonBuilder<T> implements Builder<T> {
	protected Json<T> json;

	public JsonBuilder() {
		this.json = new Json<T>();
	}

	
	@Override
	public void buildObjectJson() {
		json.setJson(new JSONObject());
	}

	@Override
	public Json<T> getJson() {
		return this.json;
		
	}
	public void buildList(List<T> lista) {
		json.setLista(lista);
	}



}
