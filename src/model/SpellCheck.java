package model;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class SpellCheck {
	HashMap<String,String> dictionary;
	
	public SpellCheck(int size) {
		dictionary= new HashMap<>(size);
	}
	
	public void fillDictionary(String string) {
		dictionary.put(string,string);
	}
	
	public boolean isInDictionary(String string) {
		return dictionary.containsKey(string);
	}
}
