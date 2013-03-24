package com.hira.c2cb;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DataBox /*extends RelativeLayout*/ {

	private static final String TAG = "C2CB_DataBox";
	private final MainActivity mActivity;
	private final View mView;
	private final int mCurrentId;

//	private EditTextData mTitle;
//	private EditTextData mData;
	private TextChangeWatcher mTitleWatcher;
	private TextChangeWatcher mDataWatcher;	
	
	public DataBox(MainActivity activity, View view, int index) {
		mActivity = activity;
		mCurrentId = index;
		mView = view;
		loadPreference();
		registWidgetListner(view);
	}

	private void loadPreference() {
		if (mView == null) {
			android.util.Log.e(TAG, "mView is null");			
			return;
		}
		String str = getPreference(getPrefId());
        EditText title = (EditText) mView.findViewById(R.id.title_text);
        title.setText(str);

        str = getPreference(getPrefId() + 1);
        EditText data = (EditText) mView.findViewById(R.id.data_text);
        data.setText(str);
	}
	
	private int getPrefId() {
		return mCurrentId << 8;
	}
		
	private void registWidgetListner(View view) {
		registCpyBtnListner((Button)view.findViewById(R.id.copy_btn));
		registListner();
	}
	
	private void registCpyBtnListner(Button btn) {
//	    Button btn = (Button) mLayout.findViewById(R.id.copy_btn);
	    btn.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	EditText eTxt = (EditText)mView.findViewById(R.id.data_text);
	        	SpannableStringBuilder sb = (SpannableStringBuilder)eTxt.getText();
	        	String str = sb.toString();
	        	android.util.Log.e(TAG,"copy text = " + str);
	        	mActivity.updateCurrentClipboard(str);
	        }
	    }); 
	}

	private void registListner() {
		int prefId = getPrefId();
		android.util.Log.d(TAG, "ID = " + mCurrentId + " prefId = " + Integer.toHexString(prefId));
        
		EditText title = (EditText) mView.findViewById(R.id.title_text);
		mTitleWatcher = new TextChangeWatcher(prefId);
		title.addTextChangedListener(mTitleWatcher);

		EditText data = (EditText) mView.findViewById(R.id.data_text);
		mDataWatcher = new TextChangeWatcher(prefId + 1);
		data.addTextChangedListener(mDataWatcher);
	}

	private class TextChangeWatcher implements TextWatcher {
		private final int mPrefId;
		TextChangeWatcher(int prefId) {
			mPrefId = prefId;
		}
        public void afterTextChanged(Editable arg) {
            //Log.d("onEditAfter", arg.toString());
        	savePreference(mPrefId, arg.toString());
        }
        // not used (from TextWatcher)
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        // not used (from TextWatcher)
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
	}    
    
	private void savePreference(int prefId, String data) {
		Log.v(TAG, "savePreference() ID = " + prefId + " str = " + data);
		SharedPreferences pref = mActivity.getPreferences(Context.MODE_PRIVATE);
		String prefKey = Integer.toString(prefId);
		Editor ed = pref.edit();
		ed.putString(prefKey, data);
		ed.apply();
	}
	
	public String getPreference(int prefId) {
		SharedPreferences pref = mActivity.getPreferences(Context.MODE_PRIVATE);
		String prefKey = Integer.toString(prefId);
		String str = pref.getString(prefKey, "null");
		Log.v(TAG, "getPreference() ID = " + prefId + " str = " + str);
		return str;
	}
	/*
	private RelativeLayout.LayoutParams getCpyButtonPosition() {
		RelativeLayout.LayoutParams pram = Util.createRelativeLayoutParam(WC, WC);
//		pram.addRule(ALIGN_PARENT_LEFT);
		return pram;
	}

	private RelativeLayout.LayoutParams getMenuButtonPosition() {
		RelativeLayout.LayoutParams pram = Util.createRelativeLayoutParam(WC, WC);
//		pram.addRule(ALIGN_PARENT_BOTTOM);
		pram.addRule(BELOW,1);
		return pram;
	}

	private RelativeLayout.LayoutParams getTitleTextPosition() {
		RelativeLayout.LayoutParams pram = Util.createRelativeLayoutParam(WC, WC);
		pram.addRule(ALIGN_PARENT_RIGHT);
		return pram;
	}
	
	private void createCopyButton() {
		mCpyBtn = new Button(mContext);
		mCpyBtn.setText("Copy");
		mCpyBtn.setId(1);
	}

	private void createMenuButton() {
		mMenuBtn = new Button(mContext);
		mMenuBtn.setText("Menu");		
		mCpyBtn.setId(2);
	}	

	private void createTitleText() {
		mTitleTxt = new TextView(mContext);
		mTitleTxt.setText("Title");
	}

	private void createEditText() {
		mEditTxt = new TextView(mContext);
		mEditTxt.setText("MainData");
	}

	private String getStringFromPref(int index) {
		// TODO Auto-generated method stub
		//		String str = getStringFromPref(index);

		String str = "test" + index;
		return str;
	}
*/

}
