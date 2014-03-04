package com.universal.cache.file.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;

import com.universal.cache.file.FileCache;

public class InternalFileCache extends FileCache {
	private Context context = null;

	public InternalFileCache(Context context) {
		this.context = context;
	}
	
	public InternalFileCache(Context context, FileCache delegate){
		super(delegate);
		this.context = context;
	}

	@Override
	protected boolean isSelfExist(String key) {
		File file = context.getFileStreamPath(key);
		return file.exists();
	}

	@Override
	public byte[] get(String key) {
		try {
			FileInputStream fis = context.openFileInput(key);
			byte[] buf = new byte[fis.available()];
			fis.read(buf);
			fis.close();
			return buf;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void selfPut(String key, byte[] content) {
		try {
			FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
			fos.write(content);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			delete(key);
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String key) {
		try {
			if(isExist(key))
				context.deleteFile(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
