package utilities;

import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Key;

public interface AdvancedDAO<T> extends DAO<T>{
	public List<T> getByIds(List<Key<T>> ids );
	public List<T> getByBeforeDate(Date date);
	public List<T> getByAfterDate(Date date);
	public List<T> getByBetweenDates(Date from, Date to); 

}
