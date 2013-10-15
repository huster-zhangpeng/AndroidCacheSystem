package com.universal.cache.file;

public interface IFileCache {

	public void put(String key, byte[] value);
	
	public byte[] get(String key);
	
	public boolean isExist(String key);
	
	public void delete(String key);
}
