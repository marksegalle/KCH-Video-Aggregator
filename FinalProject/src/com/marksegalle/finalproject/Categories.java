package com.marksegalle.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Categories extends Activity implements OnItemClickListener {


	private ListView lv;

	public static String identifier = "";
	String video_path = "";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.categories);
		setupListView();

	}

	private void setupListView() {

		lv = (ListView) this.findViewById(R.id.list_categories);
		lv.setOnItemClickListener(this);
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.listcontent,
				R.id.list_content, Category_Classes.listItems));
	}

	public void onItemClick(AdapterView arg0, View v, int position, long arg3) {
		// TODO Auto-generated method stub

		Intent intent;
		
		intent = new Intent(this, Categories_Video.class);

		intent.putExtra("identifier", Category_Classes.listItems[position].toString());

		startActivity(intent);
	
	}



}
