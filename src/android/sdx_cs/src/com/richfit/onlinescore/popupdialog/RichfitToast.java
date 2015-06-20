/*
 * Copyright (C) 2013,中油瑞飞, 移动业务部，企信开发小组
 * All Rights Reserved 
 * 描述： TODO 描述类的主要功能.   
 * 项目： 企信项目
 * 作者 ：Administrator
 * 日期“ 2014年9月11日-下午3:35:45
 *
 * 代码修改历史: 
 **********************************************************
 * 时间		       作者		          注释
 * 2014年9月11日	       Administrator		     	Create
 **********************************************************
 */
package com.richfit.onlinescore.popupdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.richfit.onlinescore.R;
import com.richfit.onlinescore.utils.ByteCharacterSetConvert;

/**
 * TODO 提示框，可以设置时间
 * @作者： Administrator
 * @日期： 2014年9月11日-下午3:35:45
 */
public class RichfitToast
{
	/**
	 * 提示框，默认维持1000毫秒（一秒）
	 * @param context
	 * @param charSequence
	 */
	public static void show(Context context, CharSequence charSequence)
	{

		show(context, charSequence, 1000);
	}

	/**
	 * 提示框，自行设定秒数
	 * @param context
	 * @param charSequence
	 * @param millisecond 持续的毫秒数
	 */
	public static void show(Context context, CharSequence charSequence, int millisecond)
	{
		try
		{
			final AlertDialog dlg = new AlertDialog.Builder(context).create();
			dlg.show();
			dlg.setCanceledOnTouchOutside(false);
			Window window = dlg.getWindow();
			WindowManager.LayoutParams lp = window.getAttributes();
			lp.alpha = 1f;
			lp.dimAmount = 0f;
			lp.gravity = Gravity.CENTER_HORIZONTAL;
			lp.screenOrientation = Gravity.CENTER_HORIZONTAL;
			window.setAttributes(lp);
			DisplayMetrics dm = new DisplayMetrics();
			((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
			window.setContentView(R.layout.toast_dialog);
			TextView txtTip = (TextView) window.findViewById(R.id.txtTip);
			String txtString = charSequence.toString();
			if (!txtString.contains("\n"))
			{
				txtString = ByteCharacterSetConvert.ToSBC(txtString);
			}

			txtTip.setText(txtString);
			new Handler().postDelayed(new Runnable()
			{
				public void run()
				{
					dlg.dismiss();
				}
			}, millisecond);
		}
		catch (Exception ex)
		{
			Toast.makeText(context, charSequence, Toast.LENGTH_LONG).show();
		}
	}
}
