package com.universal.cache.keyvalue.impl;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.universal.cache.file.impl.ExternalFileCache;
import com.universal.cache.keyvalue.Item;
import com.universal.cache.keyvalue.KeyValueCache;

public class JSONCache extends KeyValueCache {

	private ExternalFileCache fileCache = null;
	private String filename = null;
	private JSONObject jsonObj = null;
	
	public JSONCache(String fileLocation) {
		this(fileLocation.substring(0, fileLocation.lastIndexOf('/')), 
				fileLocation.substring(fileLocation.lastIndexOf('/') + 1));
	};
	
	public JSONCache(String dir, String filename) {
		this(dir, filename, null);
	};
	
	public JSONCache(String dir, String filename, KeyValueCache delegate) {
		this(new ExternalFileCache(dir), filename, delegate);
	}
	
	public JSONCache(ExternalFileCache fileCache, String filename, KeyValueCache delegate) {
		super(delegate);
		this.filename = filename;
		this.fileCache = fileCache;
		if(!fileCache.isExist(filename))
			fileCache.put(filename, "".getBytes());
		try {
			jsonObj = new JSONObject(String.valueOf(fileCache.get(filename)));
		} catch(JSONException e) {
			Log.e("cache", e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public Item get(String key) {
		try {
			return Item.valueOf(jsonObj.getString(key));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Item.NULL;
	}

	@Override
	public void selfPut(String key, Item value) {
		try {
			jsonObj.put(key, value.getOriginalValue());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		commit();
	}

	@Override
	public void delete(String key) {
		jsonObj.remove(key);
		commit();
	}

	@Override
	protected boolean isSelfExist(String key) {
		return jsonObj != null && jsonObj.has(key);
	}
	
	private void commit(){
		fileCache.put(filename, jsonObj.toString().getBytes());
	}

}
