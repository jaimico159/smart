package utilities;

import java.util.List;

public interface BasicDAO<T> extends DAO<T> {
	public List<T> getList();
	public long getNumberOfElements();
	public T getFirst();
	public T getLast();
	
}
