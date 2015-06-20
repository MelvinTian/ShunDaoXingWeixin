package com.richfit.onlinescore.utils;

import android.content.Context;
import android.widget.Toast;

public class MyToast {

	public static void showToast(Context context, String text, int duration) {
		Toast.makeText(context, text, duration).show() ;
	}

	public static final int LONG = Toast.LENGTH_LONG;
	public static final int SHORT = Toast.LENGTH_SHORT;
}
