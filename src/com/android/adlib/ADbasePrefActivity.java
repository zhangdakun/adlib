package com.android.adlib;

import com.umeng.analytics.MobclickAgent;import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Menu;import android.view.MotionEvent;
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
		AD.i().chabostart(this);		MobclickAgent.onError(this);
	}	@Override	protected void onPause() {		// TODO Auto-generated method stub		super.onPause();		MobclickAgent.onPause(this);	}	protected void setChaboInterval(long interval) {		AD.i().setChaboInterval(interval);	}	@Override	protected void onResume() {		// TODO Auto-generated method stub		super.onResume();		MobclickAgent.onResume(this);	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub		AD.i().chabostop(this);
		super.onDestroy();

	}	@Override	public boolean onTouchEvent(MotionEvent event) {		// TODO Auto-generated method stub		AD.i().setShowFlag(false);		return super.onTouchEvent(event);	}
}
