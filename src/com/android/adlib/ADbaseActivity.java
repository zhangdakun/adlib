package com.android.adlib;

import net.youmi.android.offers.OffersManager;import net.youmi.android.smart.SmartBannerManager;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;import android.view.MotionEvent;
import android.view.MenuItem;
import com.umeng.analytics.MobclickAgent;

public class ADbaseActivity extends Activity {
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
		OffersManager.getInstance(this).onAppLaunch();
		AD.i().chabostart(this);        SmartBannerManager.init(this);        SmartBannerManager.show(this);        		MobclickAgent.onError(this);
	}	protected void setChaboInterval(long interval) {		AD.i().setChaboInterval(interval);	}
	@Override	protected void onResume() {		// TODO Auto-generated method stub		super.onResume();		MobclickAgent.onResume(this);	}	@Override	protected void onPause() {		// TODO Auto-generated method stub		super.onPause();		MobclickAgent.onPause(this);	}	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub		AD.i().chabostop(this);
		super.onDestroy();
		
	}	@Override	public boolean onTouchEvent(MotionEvent event) {		// TODO Auto-generated method stub		AD.i().setShowFlag(false);		return super.onTouchEvent(event);	}
	
}
