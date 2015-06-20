/*
 * Copyright (C) 2013,中油瑞飞, 移动业务部，企信开发小组
 * All Rights Reserved 
 * 描述： TODO 描述类的主要功能.   
 * 项目： 企信项目
 * 作者 ：Administrator
 * 日期“ 2014年9月10日-下午4:25:26
 *
 * 代码修改历史: 
 **********************************************************
 * 时间		       作者		          注释
 * 2014年9月10日	       Administrator		     	Create
 **********************************************************
 */
package com.richfit.onlinescore.popupdialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.richfit.onlinescore.R;

/**
 * TODO 统一的弹出框，下方包含两个按钮
 * @作者： Administrator
 * @日期： 2014年9月10日-下午4:25:26
 */
public class RichfitPopUpDialogWhiteBG
{
	private AlertDialog dlg; // 菜单的窗体
	private Window window;
	private TextView txtTip; // 标题的控件
	private ImageView imageTip; // 图标
	private LinearLayout btnLeft;
	private LinearLayout btnRight;
	private TextView txtLeft;
	private TextView txtRight;

	private int iImageTipID = 0;
	private Bitmap bitmapImageTip = null;
	private String strTip;
	private String strLeft = "确定";
	private String strRight = "取消";
	private OnClickListener onClickListenerLeft;
	private OnClickListener onClickListenerRight;

	public RichfitPopUpDialogWhiteBG(Context context)
	{
		// TODO Auto-generated constructor stub
		this.dlg = new AlertDialog.Builder(context).create();
	}

	/**
	 * 关闭菜单
	 */
	public void close()
	{
		dlg.cancel();
		dlg.dismiss();
	}

	/**
	 * 显示菜单
	 */
	public void show()
	{
		dlg.show();
		dlg.setCanceledOnTouchOutside(true);
		window = dlg.getWindow();
		window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		window.setContentView(R.layout.popup_dialog_white);
		window.setBackgroundDrawableResource(R.color.black_transparent);
		window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		WindowManager.LayoutParams lp = window.getAttributes();
		lp.alpha = 1f;
		lp.dimAmount = 0f;
		lp.gravity = Gravity.CENTER_HORIZONTAL;
		lp.screenOrientation = Gravity.CENTER_HORIZONTAL;
		window.setAttributes(lp);
		txtTip = (TextView) window.findViewById(R.id.txtTip);
		imageTip = (ImageView) window.findViewById(R.id.imageTip);
		btnLeft = (LinearLayout) window.findViewById(R.id.btnLeft);
		btnRight = (LinearLayout) window.findViewById(R.id.btnRight);
		txtLeft = (TextView) window.findViewById(R.id.txtLeft);
		txtRight = (TextView) window.findViewById(R.id.txtRight);
		txtTip.setText(strTip);
		if (iImageTipID == 0 && bitmapImageTip == null)
		{
			imageTip.setVisibility(View.GONE);
		}
		else if (iImageTipID == 0)
		{
			imageTip.setImageBitmap(bitmapImageTip);
		}
		else
		{
			imageTip.setImageResource(iImageTipID);
		}
		txtLeft.setText(strLeft);
		btnLeft.setOnClickListener(onClickListenerLeft);
		txtRight.setText(strRight);
		btnRight.setOnClickListener(onClickListenerRight);
	}

	/**
	 * 设置图标
	 * @param id
	 */
	public RichfitPopUpDialogWhiteBG setImageTip(int resID)
	{
		this.iImageTipID = resID;
		return this;
	}

	/**
	 * 设置图标
	 * @param id
	 */
	public RichfitPopUpDialogWhiteBG setImageTip(Bitmap bm)
	{
		this.bitmapImageTip = bm;
		return this;
	}

	/**
	 * 显示内容
	 * @param content
	 */
	public RichfitPopUpDialogWhiteBG setContent(String content)
	{
		this.strTip = content;
		return this;
	}

	/**
	 * 确认按钮
	 * @param text
	 * @param listener
	 */
	public RichfitPopUpDialogWhiteBG setPositiveButton(CharSequence text, OnClickListener listener)
	{
		strLeft = text.toString();
		if (listener == null)
		{
			onClickListenerLeft = new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					close();
				}
			};
		}
		else
		{
			onClickListenerLeft = listener;
		}
		return this;
	}

	/**
	 * 取消按钮
	 * @param text
	 * @param listener
	 */
	public RichfitPopUpDialogWhiteBG setNegativeButton(CharSequence text, OnClickListener listener)
	{
		strRight = text.toString();
		if (listener == null)
		{
			onClickListenerRight = new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					close();
				}
			};
		}
		else
		{
			onClickListenerRight = listener;
		}
		return this;
	}
}
