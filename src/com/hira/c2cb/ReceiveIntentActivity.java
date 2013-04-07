package com.hira.c2cb;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class ReceiveIntentActivity extends Activity {

	private static final String TAG = "C2CB_ReceiveIntentActivity";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		Log.v(TAG, "onCreate() Begin");
		super.onCreate(savedInstanceState);
		createIntentReceiver();
    }
	private void createIntentReceiver() {
		Intent intent = getIntent();
		String action = intent.getAction();
		if (Intent.ACTION_SEND.equals(action)) {
		  Bundle extras = intent.getExtras();
		  if (extras != null) {

//			  String practice_name = getIntent().getStringExtra("practice_menu");
//			  Toast.makeText(this, "practice_menu="+practice_name, Toast.LENGTH_LONG).show();
			  CharSequence ext = extras.getCharSequence(Intent.EXTRA_TEXT);
			  Toast.makeText(this, "set Clipborad to \""+ ext.toString() + "\"", Toast.LENGTH_LONG).show();
		      ClipboardManager cm = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
			  Util.setClipBoard(cm, ext.toString());
		  }
		}
		this.finish();
	}
}
