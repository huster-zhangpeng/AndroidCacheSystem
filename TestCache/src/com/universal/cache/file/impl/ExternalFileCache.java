package com.universal.cache.file.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;

import com.universal.cache.file.FileCache;

public class ExternalFileCache extends FileCache {
	
	private String Location = "";
	
	public ExternalFileCache(String location) {
		this(location, null);
	}
	
	public ExternalFileCache(String location, FileCache delegate){
		super(delegate);
		String sdcardPath = Environment.getExternalStorageDirectory().getPath();		//  "/mnt/sdcard"
		Location = sdcardPath + File.separator + location;
		
		File dir = new File(Location);
		if(!dir.exists()){
			dir.mkdirs();    //create directories included missing parents
		}
	}

	@Override
	protected boolean isSelfExist(String key) {
		File file = new File(Location + key);
		return file.exists();
	}

	@Override
	public byte[] get(String key) {
		try {
			InputStream reader = new FileInputStream(Location + key);
			byte buf[] = new byte[reader.available()];
			reader.read(buf);
			reader.close();
			return buf;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void selfPut(String filename, byte[] content) {
		try {
			OutputStream writer = new FileOutputStream(Location + filename);
			writer.write(content);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String filename) {
		try {
			File file = new File(Location + filename);
			if (file.exists()) {
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
