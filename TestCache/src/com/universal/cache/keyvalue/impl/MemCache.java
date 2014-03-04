package com.universal.cache.keyvalue.impl;

import java.util.Hashtable;
import java.util.Map;

import com.universal.cache.keyvalue.Item;
import com.universal.cache.keyvalue.KeyValueCache;

public class MemCache extends KeyValueCache {
	
	private static Map<String, String> cacheMap = new Hashtable<String, String>();

	public MemCache(){};
	
	public MemCache(KeyValueCache delegate) {
		super(delegate);
	}

	@Override
	public void delete(String key) {
		cacheMap.remove(key);
	}

	@Override
	protected boolean isSelfExist(String key) {
		return cacheMap.containsKey(key);
	}

	@Override
	public Item get(String key) {
		return Item.valueOf(cacheMap.get(key));
	}

	@Override
	public void selfPut(String key, Item value) {
		cacheMap.put(key, value.getOriginalValue());
	}

}
