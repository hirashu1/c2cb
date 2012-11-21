package com.hira.c2cb;

import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateCurrentClipBoardShown();

        Button btn = (Button) this.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                showToast(str);
                String str = getEditText();
                setClipBoard(str);
                updateCurrentClipBoardShown();
            }
        });
    }

    private String getEditText() {
        TextView et = (TextView) this.findViewById(R.id.nowData);
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

    private void updateCurrentClipBoardShown() {
        String str = getCurrentClipBoardText();
        showTextView(str);
    }
    
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

    private void showTextView(String str) {
       TextView tv = (TextView) this.findViewById(R.id.nowData);
       tv.setText(str);
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
