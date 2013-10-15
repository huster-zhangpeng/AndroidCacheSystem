package com.universal.cache.keyvalue;

public class Item {
	private static final char INT = '0';
	private static final char LONG = '1';
	private static final char FLOAT = '2';
	private static final char BOOLEAN = '3';
	private static final char STRING = '4';
	private static final String[] types = {"int", "long", "float", "boolean", "string"};
	
	private String value;
	
	public static Item NULL = new NullItem();
	
	public static Item valueOf(String value) {
		Item item = new Item();
		item.value = value;
		return item;
	}
	
	public Item(){}
	
	public Item(int value) {
		this.value = INT + '_' + String.valueOf(value);
	}
	
	public Item(long value) {
		this.value = LONG + '_' + String.valueOf(value);
	}
	
	public Item(float value) {
		this.value = FLOAT + '_' + String.valueOf(value);
	}
	
	public Item(Boolean value) {
		this.value = BOOLEAN + '_' + String.valueOf(value);
	}
	
	public Item(String value) {
		this.value = STRING + '_' + value;
	}
	
	public String getOriginalValue() {
		return value;
	}
	
	public int asInt() throws Exception {
		char ch = value.charAt(0);
		if(ch == INT) {
			return Integer.valueOf(value.substring(2));
		} else {
			throw new Exception("Can not convert " + types[ ch - '0' ] + " to int.");
		}
	}
	
	public long asLong() throws Exception {
		char ch = value.charAt(0);
		if(ch == LONG) {
			return Long.valueOf(value.substring(2));
		} else {
			throw new Exception("Can not convert " + types[ ch - '0' ] + " to long.");
		}
	}
	
	public float asFloat() throws Exception {
		char ch = value.charAt(0);
		if(ch == FLOAT) {
			return Float.valueOf(value.substring(2));
		} else {
			throw new Exception("Can not convert " + types[ ch - '0' ] + " to float.");
		}
	}
	
	public boolean asBoolean() throws Exception {
		char ch = value.charAt(0);
		if(ch == BOOLEAN) {
			return Boolean.valueOf(value.substring(2));
		} else {
			throw new Exception("Can not convert " + types[ ch - '0' ] + " to boolean.");
		}
	}
	
	public String asString() throws Exception {
		char ch = value.charAt(0);
		if(ch == LONG) {
			return value.substring(2);
		} else {
			throw new Exception("Can not convert " + types[ ch - '0' ] + " to String.");
		}
	}
	
	static class NullItem extends Item {
		public int asInt() throws Exception {
			throw new Exception("JSONCache: null error.");
		}
		
		public long asLong() throws Exception {
			throw new Exception("JSONCache: null error.");
		}
		
		public float asFloat() throws Exception {
			throw new Exception("JSONCache: null error.");
		}
		
		public boolean asBoolean() throws Exception {
			throw new Exception("JSONCache: null error.");
		}
		
		public String asString() throws Exception {
			throw new Exception("JSONCache: null error.");
		}
	}
}
