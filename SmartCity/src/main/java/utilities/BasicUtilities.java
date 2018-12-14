package utilities;

import java.util.List;

public interface BasicUtilities<T> extends Utilities<T> {
	public List<T> getList();
	public long getNumberOfElements();
	public T getFirst();
	public T getLast();
	
}
