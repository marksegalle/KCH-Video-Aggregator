package com.marksegalle.finalproject;



import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

public class FinalProjectActivity extends TabActivity {

	DBHelper db = new DBHelper(this);

	Category_Classes cc;
	
	Intent intent;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);

		prepareVideos();

		Resources res = getResources();
		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;

		intent = new Intent().setClass(this, AllVideos.class);

		spec = tabHost
				.newTabSpec("artists")
				.setIndicator("All Videos",
						res.getDrawable(R.drawable.ic_tab_allvideos))
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, Categories.class);

		spec = tabHost
				.newTabSpec("categories")
				.setIndicator("Categories",
						res.getDrawable(R.drawable.ic_tab_categories))
				.setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);

	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.mainmenu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		
		switch (item.getItemId()) {


		case R.id.MENU_KCHW:
			intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.kchonline.ph"));
			startActivity(intent);
			return true;

		case R.id.MENU_ABOUT:
			intent = new Intent(FinalProjectActivity.this, About.class);
			startActivity(intent);
			return true;

		default:
			return false;
		}
	}


	public void prepareVideos() {

		for (int i = 0; i < cc.listCnL.length; i++) {
			db.open();
			long id;
			id = db.insertVideo(cc.listCnL[i], cc.listCnL_url[i]);
			db.close();
		}

		for (int i = 0; i < cc.listTE.length; i++) {
			db.open();
			long id;
			id = db.insertVideo(cc.listTE[i], cc.listTE_url[i]);
			db.close();
		}

		for (int i = 0; i < cc.listFLnE.length; i++) {
			db.open();
			long id;
			id = db.insertVideo(cc.listFLnE[i], cc.listFLnE_url[i]);
			db.close();
		}

		for (int i = 0; i < cc.listM.length; i++) {
			db.open();
			long id;
			id = db.insertVideo(cc.listM[i], cc.listM_url[i]);
			db.close();
		}

		for (int i = 0; i < cc.listSnT.length; i++) {
			db.open();
			long id;
			id = db.insertVideo(cc.listSnT[i], cc.listSnT_url[i]);
			db.close();
		}

		for (int i = 0; i < cc.listSSnV.length; i++) {
			db.open();
			long id;
			id = db.insertVideo(cc.listSSnV[i], cc.listSSnV_url[i]);
			db.close();
		}

		for (int i = 0; i < cc.listNnCA.length; i++) {
			db.open();
			long id;
			id = db.insertVideo(cc.listNnCA[i], cc.listNnCA_url[i]);
			db.close();
		}

	}

}