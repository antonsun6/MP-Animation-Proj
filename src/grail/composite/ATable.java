package grail.composite;

import java.util.ArrayList;

import tags301.Comp301Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;

@StructurePattern(StructurePatternNames.MAP_PATTERN)
@Tags({Comp301Tags.TABLE})
public class ATable<V> implements Table<V>{

	private ArrayList<String> keys = new ArrayList<>();
	private ArrayList<V> values = new ArrayList<>();

	@Override
	public void put(String key, V val) {

		if ((key != null) && (val != null)) {
			if(keys.size()>0 && keys.indexOf(key)>=0) {
				values.set(keys.indexOf(key), val);
			} else {
				keys.add(key);
				values.add(val);
			}
		}
	}

	@Override
	public V get(String key) {

		int index = keys.indexOf(key);

		if(index >= 0) {
			return values.get(index);
		}
		return null;
	}


}
