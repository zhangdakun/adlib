package com.android.adlib;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MenuItem;

public class ADbasePrefActivity extends PreferenceActivity {
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.ad_menu, menu);
		return true;
//		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		if(R.id.ad_recommnet == item.getItemId()) {
			AD.showChaBo(this);
		} else if(R.id.ad_other == item.getItemId()) {
			AD.showOffersWall(this);
		}

		return super.onMenuItemSelected(featureId, item);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		AD.i().chabostart(this);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		AD.i().chabostop(this);
	}
}
