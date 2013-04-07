package com.hira.c2cb;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Util {

	public static LinearLayout.LayoutParams createLinearLayoutParam(int w, int h){
        return new LinearLayout.LayoutParams(w, h);
    }

	public static RelativeLayout.LayoutParams createRelativeLayoutParam(int w, int h){
        return new RelativeLayout.LayoutParams(w, h);
    }
    public static void setClipBoard(ClipboardManager cm, String str) {
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
        cm.setPrimaryClip(cd);
    }
}
