package com.universal.cache.keyvalue;

public interface IKeyValueCache {

	public boolean isExist(String key);
	
	public Item get(String key);
	
	public void put(String key, Item value);
	
	public void delete(String key);
}
