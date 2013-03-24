package com.hira.c2cb;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class DataBoxManager {
	
	private final MainActivity mActivity;
	private final LinearLayout mLayout;
	public DataBoxManager(MainActivity activity, LinearLayout layout) {
		mActivity = activity;
		mLayout = layout;
	}

	public void createDataBoxs(int cnt) {
		for (int i = 0;i<cnt;i++) {
//			DataBox dbox = new DataBox(mContext ,i);
			
//			RelativeLayout relLayout = new RelativeLayout(mContext);
			View view = LayoutInflater.from(mActivity).inflate(R.layout.box, null);
			view.setId(i);
			mLayout.addView(view);
			DataBox dbox = new DataBox(mActivity, view,i);		
		}
	}



	
	public void addDataBox() {
		// TODO:
	}

	public void delDataBox(int index) {
		// TODO:
	}

}
