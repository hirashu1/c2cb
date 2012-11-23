package com.hira.c2cb;

import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String TAG = "C2CB_MainActivity";
	private SaveData mData;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateCurrentClipBoardShown();

        creteCopyButton();
        registEditTextListner();
        showPrefData();
    }
	
	private void showPrefData() {
        EditText title = (EditText) this.findViewById(R.id.title);
        EditText data = (EditText) this.findViewById(R.id.text_data);
        title.setText(mData.getTitle());
        data.setText(mData.getData());
	}

    private void registEditTextListner() {
        mData = new SaveData(this);
        EditText title = (EditText) this.findViewById(R.id.title);
        EditText data = (EditText) this.findViewById(R.id.text_data);
        mData.setTitleEditText(title);
        mData.setDataEditText(data);
    }
    
    private void creteCopyButton() {
	    Button btn = (Button) this.findViewById(R.id.copy_button);
	    btn.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	            String str = getEditText();
	            setClipBoard(str);
	            updateCurrentClipBoardShown();
	        }
	    });
    }
    
    private String getEditText() {
        EditText et = (EditText) this.findViewById(R.id.text_data);
        //Log.v(TAG,"copy to clipbord " + et.getText().toString());
        return et.getText().toString();
    }

    private void setClipBoard(String str) {
        if (str == null) {
                return;
        }
        //クリップボードに格納するItemを作成
        ClipData.Item item = new ClipData.Item(str);

        //MIMETYPEの作成
        String[] mimeType = new String[1];
        mimeType[0] = ClipDescription.MIMETYPE_TEXT_PLAIN;

        //クリップボードに格納するClipDataオブジェクトの作成
        ClipData cd = new ClipData(new ClipDescription("text_data", mimeType), item);

        //クリップボードにデータを格納
        ClipboardManager cm = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                cm.setPrimaryClip(cd);
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
        if(clip != null){
            ClipData.Item item = clip.getItemAt(0);
            return item.getText().toString();
        } else {
            return null;
        }
    }

    //TextBoxに文字列を表示する
    private void showTextView(String str) {
       TextView tv = (TextView) this.findViewById(R.id.now_data);
       tv.setText(str);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
