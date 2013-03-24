package com.hira.c2cb;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Util {

	public static LinearLayout.LayoutParams createLinearLayoutParam(int w, int h){
        return new LinearLayout.LayoutParams(w, h);
    }

	public static RelativeLayout.LayoutParams createRelativeLayoutParam(int w, int h){
        return new RelativeLayout.LayoutParams(w, h);
    }

}
