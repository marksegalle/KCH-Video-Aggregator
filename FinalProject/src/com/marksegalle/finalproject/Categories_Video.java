package com.marksegalle.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Categories_Video extends Activity implements OnItemClickListener {

	final DBHelper db = new DBHelper(this);

	Bundle extras;
	String identifier;

	private ListView lv;

	private String[] category;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.category_cnl);
		setupListView();

		TextView Category = (TextView) findViewById(R.id.txtCategory);

		extras = getIntent().getExtras();
		identifier = extras.getString("identifier");

		Category.setText(identifier.toString());

	}

	private void setupListView() {

		lv = (ListView) this.findViewById(R.id.list_categories_video);
		lv.setOnItemClickListener(this);

		extras = getIntent().getExtras();
		identifier = extras.getString("identifier");

		if (identifier.equals(Category_Classes.listItems[0].toString())) {

			lv.setAdapter(new ArrayAdapter<String>(this, R.layout.listcontent,
					R.id.list_content, Category_Classes.listCnL));

			category = Category_Classes.listCnL;

		} else if (identifier.equals(Category_Classes.listItems[0].toString())) {

			lv.setAdapter(new ArrayAdapter<String>(this, R.layout.listcontent,
					R.id.list_content, Category_Classes.listTE));

			category = Category_Classes.listTE;

		} else if (identifier.equals(Category_Classes.listItems[2].toString())) {

			lv.setAdapter(new ArrayAdapter<String>(this, R.layout.listcontent,
					R.id.list_content, Category_Classes.listFLnE));

			category = Category_Classes.listFLnE;

		} else if (identifier.equals(Category_Classes.listItems[3].toString())) {

			lv.setAdapter(new ArrayAdapter<String>(this, R.layout.listcontent,
					R.id.list_content, Category_Classes.listM));

			category = Category_Classes.listM;

		} else if (identifier.equals(Category_Classes.listItems[4].toString())) {

			lv.setAdapter(new ArrayAdapter<String>(this, R.layout.listcontent,
					R.id.list_content, Category_Classes.listSnT));

			category = Category_Classes.listSnT;

		} else if (identifier.equals(Category_Classes.listItems[5].toString())) {

			lv.setAdapter(new ArrayAdapter<String>(this, R.layout.listcontent,
					R.id.list_content, Category_Classes.listSSnV));

			category = Category_Classes.listSSnV;

		} else if (identifier.equals(Category_Classes.listItems[6].toString())) {

			lv.setAdapter(new ArrayAdapter<String>(this, R.layout.listcontent,
					R.id.list_content, Category_Classes.listNnCA));

			category = Category_Classes.listNnCA;

		}
	}

	public void onItemClick(AdapterView arg0, View v, int position, long arg3) {
		db.open();

		String video_path = "";

		for (int i = 0; i < category.length; i++) {

			if (category[position].toString() == category[i].toString()) {

				Cursor c = db.getVideo(category[i].toString());

				if (c.moveToFirst()) {

					video_path = c.getString(2);

				} else {
					Toast.makeText(
							getApplicationContext(),
							"Error retrieving video! Please contact the developer!",
							Toast.LENGTH_LONG).show();

				}

				Uri uri = Uri.parse(video_path);

				// With this line the Youtube application, if installed, will
				// launch immediately.
				// Without it you will be prompted with a list of the
				// application to choose.
				uri = Uri.parse("vnd.youtube:" + uri.getQueryParameter("v"));

				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);

			}
		}

		db.close();

	}

}
