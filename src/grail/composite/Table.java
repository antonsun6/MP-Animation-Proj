package grail.composite;

import tags301.Comp301Tags;
import util.annotations.Tags;

@Tags({Comp301Tags.TABLE})
public interface Table<V> {
	// associates key with value, returning last value associated with key
	  public void put(String key, V val);
	  // returns last value associated with key, or null if no association
	  public V get(String key);
}
