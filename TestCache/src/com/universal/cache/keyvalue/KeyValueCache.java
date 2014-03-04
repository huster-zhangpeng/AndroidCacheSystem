package com.universal.cache.keyvalue;

public abstract class KeyValueCache implements IKeyValueCache {

	protected KeyValueCache delegate = null;
	
	public KeyValueCache(){}
	
	public KeyValueCache(KeyValueCache delegate) {
		this.delegate = delegate;
	}
	
	public final boolean isExist(String key) {
		if(isSelfExist(key)) {
			return true;
		} else if(delegate != null && delegate.isExist(key)) {
			put(key, delegate.get(key));
			return true;
		}
		return false;
	}
	
	public final void put(String key, Item value) {
		selfPut(key, value);
		if(delegate != null)
			delegate.put(key, value);
	}
	
	protected abstract void selfPut(String key, Item value);
	protected abstract boolean isSelfExist(String key);
}
