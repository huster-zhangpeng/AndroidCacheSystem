package com.universal.cache.keyvalue.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.universal.cache.keyvalue.Item;
import com.universal.cache.keyvalue.KeyValueCache;

public class PreferencesCache extends KeyValueCache {
	private final static String DEFAULT_STRING = "none";
	
	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;
	
	public PreferencesCache(Context context, String location){
		this(context, location, null);
	}
	
	public PreferencesCache(Context context, String location, KeyValueCache delegate) {
		super(delegate);
		preferences = context.getSharedPreferences(location, Context.MODE_PRIVATE);
		editor = preferences.edit();
	}

	@Override
	protected boolean isSelfExist(String key) {
		return preferences.contains(key);
	}

	@Override
	public void delete(String key) {
		editor.remove(key).commit();
	}

	public Item get(String key){
		return Item.valueOf(preferences.getString(key, DEFAULT_STRING));
	}
	
	
	public void selfPut(String key, Item value){
		editor.putString(key, value.getOriginalValue());
		editor.commit();
	}

}
