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
	
	protected abstract boolean isSelfExist(String key);
}
