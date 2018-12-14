package utilities;

import com.googlecode.objectify.Key;

public interface Utilities<T> {
	public Key<T> save(T entity);
	public T get(Key<T> key);
	public Key<T> update(T entity);
	public void delete(Key<T> key);
	
}
