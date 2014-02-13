package com.android.adlib;

import net.youmi.android.diy.DiyManager;
import net.youmi.android.offers.OffersManager;
import net.youmi.android.offers.PointsManager;
import net.youmi.android.smart.SmartBannerManager;import net.youmi.android.spot.SpotManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.provider.Settings.System;
import android.util.Log;

public class AD {

	public static boolean haveOffers(final Context context) {
		checkSpend(context);
		int pointsBalance = PointsManager.getInstance(context).queryPoints();// 鏌ヨ绉垎浣欓
		// PointsManager.getInstance(this).spendPoints(PointsManager.getInstance(this).queryPoints());
		Log.d("info", "pointsBalance " + pointsBalance);
		if (pointsBalance < 1) {
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage(
					"需要 " + String.valueOf(1) + " 积分，您当前积分是 "
							+ String.valueOf(pointsBalance))
					.setCancelable(false)
					.setPositiveButton("获取积分",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {

									OffersManager.getInstance(context)
											.showOffersWall();
									dialog.cancel();
								}
							})
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
			AlertDialog alert = builder.create();
			alert.show();

			return false;
		}
		return true;

	}

	public static void showOffersWall(Context context) {
		OffersManager.getInstance(context).showOffersWall();
	}

	public static void showChaBo(Context context) {
		SpotManager.getInstance(context).showSpotAds(context);
	}

	public static void showRecommandWall(Context context) {
		DiyManager.showRecommendWall(context);
	}

	public static void spendalloffers(Context context) {
		// (() PointsManager.getInstance(context)).queryPoints();
		PointsManager.getInstance(context).spendPoints(
				PointsManager.getInstance(context).queryPoints());
	}

	public static void checkSpend(Context context) {
		long current = java.lang.System.currentTimeMillis();
		SharedPreferences sp = context.getSharedPreferences("myset", 0);
		long first = sp.getLong("first", 0);
		if (0 == first) {
			sp.edit().putLong("first", current).commit();
		} else {
			// if(current > first && (current - first) > )
			Log.d("crack", "used : " + (current - first));
			if (first > current
					|| (current - first) > 8 * 24 * 60 * 60 * 1000L) {
				spendalloffers(context);
				
			}
			sp.edit().putLong("first", current).commit();
		}
	}
	private long CHABO_INTERVAL = 5 * 60 * 1000L;	private boolean show = true;		public void setChaboInterval(long interval) {		CHABO_INTERVAL = interval;	}	public void setShowFlag(boolean flag) {		show = flag;	}	
	class ChaboThread extends Thread {
		Context context;
		public ChaboThread(Context context) {
			super();
			this.context = context;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			// super.run();
			while (isrun) {
				try {
					Thread.sleep(CHABO_INTERVAL);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				if(show) {
//					showChaBo(context);					SmartBannerManager.show(this.context);				}else  {					show = true;				}
			}
		}

	}

	public ChaboThread chabo = null;
	public static boolean isrun = false;
	public static AD adinstance = new AD();
	
	

	public void chabostart(Context context) {
//		Log.d(tag, msg)
		isrun = true;
		if(null == adinstance.chabo) {
			chabo = new ChaboThread(context);
			chabo.start();
		} else {
			
		}
//		else if(!chabo.isAlive()) {
//			
//		}
	}

	public static AD i() {
		return adinstance;
	}
	public  void chabostop(Context context) {
		isrun = false;
		if (null != chabo) {
			
		}
	}

}
