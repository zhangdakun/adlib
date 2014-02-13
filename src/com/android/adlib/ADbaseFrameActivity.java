package com.android.adlib;

import com.umeng.analytics.MobclickAgent;

import net.youmi.android.offers.OffersManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

public class ADbaseFrameActivity extends FragmentActivity{
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

		AD.i().chabostart(this);

		MobclickAgent.onError(this);
		
//		MobclickAgent.
//		String info = new StringBuilder().append(Build.PRODUCT).append(":").append(Build.MODEL).toString();
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MobclickAgent.onPause(this);
	}
	protected void setChaboInterval(long interval) {
		AD.i().setChaboInterval(interval);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override

	protected void onDestroy() {

		// TODO Auto-generated method stub

		AD.i().chabostop(this);
		super.onDestroy();


	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		AD.i().setShowFlag(false);
		return super.onTouchEvent(event);
	}
}
