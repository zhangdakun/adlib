package com.android.adlib;

import com.android.adlib.FileDialog.ActionListener;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

//FileDialog.ActionListener;
public class SettingListener implements ActionListener {
	public static final String TAG = "SettingListener";
	
	private Handler handler;
	private String data;
	public SettingListener(Handler handler) {
		super();
		this.handler = handler;
	}
	@Override
	public void userAction(int action, String data) {
		// TODO Auto-generated method stub
//		EbenLog.d("action: " + action + ", data: " + data);
		setData(data);
		Message msg = new Message();
		Bundle bl = new Bundle();
		bl.putString("data", data);
		bl.putInt("aciton", action);
		msg.setData(bl);
//		msg.getData();
		handler.sendMessage(msg);
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

}
