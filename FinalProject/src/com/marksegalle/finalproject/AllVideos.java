package com.marksegalle.finalproject;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AllVideos extends ListActivity implements OnItemClickListener {

	DBHelper db = new DBHelper(this);
	ListView lv;
	String video_path = "";

	String strVideoName = "";


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.all_videos);
		setupListView();

	}

	private void setupListView() {

		db.open();

		Cursor c = db.getAllVideos();
		startManagingCursor(c);

		// the desired columns to be bound
		String[] columns = new String[] { db.KEY_NAME };
		// the XML defined views which the data will be bound to
		int[] to = new int[] { R.id.list_content };

		// create the adapter using the cursor pointing to the
		// desired data as well as the layout information
		SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this,
				R.layout.listcontent, c, columns, to);

		// set this adapter as your ListActivity's adapter
		this.setListAdapter(mAdapter);

		db.close();

		lv = getListView();

		lv.setOnItemClickListener(this);

	}

	public void onItemClick(AdapterView parentView, View childView,
			int position, long id) {

		db.open();

		Cursor c = db.getVideo(position + 1);

		if (c.moveToFirst()) {

			video_path = c.getString(2);

		} else {
			Toast.makeText(getApplicationContext(), strVideoName,
					Toast.LENGTH_LONG).show();

		}

		Uri uri = Uri.parse(video_path);

		// With this line the Youtube application, if installed, will launch
		// immediately.
		// Without it you will be prompted with a list of the application to
		// choose.
		uri = Uri.parse("vnd.youtube:" + uri.getQueryParameter("v"));

		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);

		db.close();

	}

	

}
