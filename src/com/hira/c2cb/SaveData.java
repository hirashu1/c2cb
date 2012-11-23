package com.hira.c2cb;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class SaveData {
	private static final String TAG = "C2CB_SaveData";
	
	private EditTextData mTitle = null;
	private EditTextData mData = null;
	private MainActivity mActivity = null;

	SaveData(MainActivity activity) {
		int prefId = 0;
		mActivity = activity;
		//preferenceからデータ数を取得
		int prefCnt = 1;//TODO:getPreferenceDataCount();
		for (int cnt = 0;cnt<prefCnt;cnt++) {
			mTitle = new EditTextData(prefId++);
			mData = new EditTextData(prefId++);
		}
	}
	
	public String getTitle() {
		return mTitle.getString();
	}
	
	public String getData() {
		return mData.getString();
	}
		
//	public void setTitle(String str) {
//		mTitle.setString(str);
//	}

//	public void setData(String str) {
//		mData.setString(str);
//	}

	private class EditTextData {
//		private String mString = null;
		private final int mPrefId;
		private TextChangeWatcher mEditWatcher;
		
		EditTextData(int prefId) {
			Log.v(TAG, "EditTextData prefId = " + prefId);
			mPrefId = prefId;
			mEditWatcher = new TextChangeWatcher(prefId);
		}

		private String getString() {
//			if (mString != null) {
//				return mString;
//			}
			return getPreference(mPrefId);
		}

//		private void setString(String str) {
//			mString = str;
//		}

		private void addTextChangedListener(EditText et) {
	        et.addTextChangedListener(mEditWatcher);
		}
	
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
	
	public void setTitleEditText(EditText title) {
		mTitle.addTextChangedListener(title);
	}
	public void setDataEditText(EditText data) {
		mData.addTextChangedListener(data);
	}
	
	public void savePreference(int prefId, String data) {
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
}
