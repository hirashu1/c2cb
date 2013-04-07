package com.hira.c2cb;

import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String TAG = "C2CB_MainActivity";
//	private SaveData mData;

	private static final int DEFAULT_BOXS_COUNT = 3;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		Log.v(TAG, "onCreate() Begin");
		super.onCreate(savedInstanceState);
		initialize();
        showLayoutBox();
//        setContentView(R.layout.activity_main);
        updateCurrentClipBoardShown();

//        registListner();
//        showPrefData();
    }

	private void initialize() {
		mLayout = new LinearLayout(this);
		mLayout.setOrientation(LinearLayout.VERTICAL);
		setContentView(mLayout);

		initializeMember();

	}


	
	private DataBoxManager mDataBoxManager = null;
	private void initializeMember() {
		mDataBoxManager = new DataBoxManager(this, mLayout);
	}
	
	private TextView mClipBoardData = null;
	private Button mAddBtn = null;
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
	private LinearLayout mLayout = null;
	
	private void showLayoutBox() {

		//show "Now clipboard data"
		mClipBoardData = new TextView(this);
		mLayout.addView(mClipBoardData, Util.createLinearLayoutParam(WC, WC));
		
		// Create DataBox
		initilaizeDataBox();
		
		//show "Add" Button
		mAddBtn = new Button(this);
		mAddBtn.setText("Additional");
		mLayout.addView(mAddBtn, Util.createLinearLayoutParam(WC, WC));
		registListner();
	}

	private void initilaizeDataBox(){
		//read DataBox count from Preferences
		// TODO:get
		int count = DEFAULT_BOXS_COUNT;
		
		// Create DataBox loop
		mDataBoxManager.createDataBoxs(count);
		
    }

    private void registListner() {
//        registCopyBtnEvntListner();
        registAddBtnEvntListner();
//        registEditTextListner();		
	}
/*	
    private void registEditTextListner() {
        mData = new SaveData(this);
        EditText title = (EditText) this.findViewById(R.id.title);
//        EditText data = (EditText) this.findViewById(R.id.text_data);
        mData.setTitleEditText(title);
//        mData.setDataEditText(data);
    }
*/
/*    
    private void registCopyBtnEvntListner() {
	    Button btn = (Button) this.findViewById(R.id.copy_button);
	    btn.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	            String str = getEditText();
	            setClipBoard(str);
	            updateCurrentClipBoardShown();
	        }
	    });
    }
*/
    private void registAddBtnEvntListner() {
//	    Button btn = (Button) this.findViewById(R.id.add_button);
    	if (mAddBtn == null) {
    		Log.w(TAG,"mAddBtn is null");
    	}
    	mAddBtn.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	addSaveDataBlock();
	        }
	    });
    }

    private void addSaveDataBlock() {
    	// Blockを作成する
    	// CopyButton / Title / Data を作成する
    	// AddButtonを下にずらす。
    	// 表示する
    }
/*    
    private String getEditText() {
        EditText et = (EditText) this.findViewById(R.id.text_data);
        //Log.v(TAG,"copy to clipbord " + et.getText().toString());
        return et.getText().toString();
    }
*/
    protected void updateCurrentClipboard(String str) {
        ClipboardManager cm = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
    	Util.setClipBoard(cm, str);
    	updateCurrentClipBoardShown();
    }
    


    // 現在のクリップボードの内容を表示する
    private void updateCurrentClipBoardShown() {
        String str = getCurrentClipBoardText();
        showTextView(str);
    }
    
    // 現在のクリップボードの文字列を取得する
    private String getCurrentClipBoardText() {
        ClipboardManager cm = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = cm.getPrimaryClip();
        if (clip != null) {
            ClipData.Item item = clip.getItemAt(0);
            return item.getText().toString();
        } else {
            return null;
        }
    }

    //TextBoxに文字列を表示する
    private void showTextView(String str) {
//       TextView tv = (TextView) this.findViewById(R.id.now_data);
//       tv.setText(str);
    	if (mClipBoardData == null) {
    		Log.w(TAG, "mClipBoardData is null");
    		return;
    	}
    	if (str == null) {
    		str = "no data";
    	}
       mClipBoardData.setText(str);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
