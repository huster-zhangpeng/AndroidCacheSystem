package com.universal.cache.file.impl;

import android.content.Context;
import android.os.Environment;

import com.universal.cache.file.FileCache;

public class SDCardPreferedCache extends FileCache {
	
	FileCache fileCache = null;
	
	public SDCardPreferedCache(Context context, String location){
		if(hasSDCard())
			fileCache = new ExternalFileCache(location, new InternalFileCache(context));
		else
			fileCache = new InternalFileCache(context);
	}
	
	private boolean hasSDCard() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	@Override
	protected boolean isSelfExist(String key) {
		return fileCache.isExist(key);
	}

	@Override
	public void delete(String key) {
		fileCache.delete(key);
	}

	@Override
	public void put(String key, byte[] value) {
		fileCache.put(key, value);
	}

	@Override
	public byte[] get(String key) {
		return fileCache.get(key);
	}

}
