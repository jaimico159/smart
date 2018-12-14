package Builder;
import java.util.List;

public interface Builder<T> {
	public void buildList(List<T> lista);
	public void buildObjectJson();
	public Json getJson();

}
