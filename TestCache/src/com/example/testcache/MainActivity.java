package com.example.testcache;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SharedPreferences preferences = getSharedPreferences("test", Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		
		editor.putInt("aaa", 189);
		
		String str = preferences.getString("aaa", "null");
		
		((TextView)findViewById(R.id.content)).setText(str);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
