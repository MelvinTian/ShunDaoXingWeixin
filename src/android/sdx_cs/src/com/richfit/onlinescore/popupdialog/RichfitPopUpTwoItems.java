package com.richfit.onlinescore.popupdialog;

import com.richfit.onlinescore.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Intents.Insert;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * TODO 统一的弹出框， 内容包含两个Textview选项 下方包含一个按钮
 * @作者：李子彦
 * @日期： 2014年12月11日-上午11:23:59
 */
public class RichfitPopUpTwoItems
{

	private AlertDialog dlg; // 菜单的窗体
	private Window window;
	private ImageView imageTip; // 图标
	private TextView txtTipOne; // 第一个textview
	private LinearLayout btnTipOne;
	private TextView txtTipTwo; // 第二个textview
	private LinearLayout btnTipTwo;
	private LinearLayout btnOK;
	private TextView txtOK;

	private int iImageTipID = 0;
	private Bitmap bitmapImageTip = null;
	private String strTipOne;
	private String strTipTwo;
	private String strOK = "确定";
	private OnClickListener onClickListenerBtn;
	private OnClickListener onClickListenerTipOne;
	private OnClickListener onClickListenerTipTwo;

	public RichfitPopUpTwoItems(Context context)
	{
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
		dlg.setCanceledOnTouchOutside(false);
		window = dlg.getWindow();
		window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		window.setContentView(R.layout.popup_dialog_two_items);
		WindowManager.LayoutParams lp = window.getAttributes();
		lp.alpha = 1f;
		lp.dimAmount = 0f;
		lp.gravity = Gravity.CENTER_HORIZONTAL;
		lp.screenOrientation = Gravity.CENTER_HORIZONTAL;
		window.setAttributes(lp);
		imageTip = (ImageView) window.findViewById(R.id.imageTip);
		txtTipOne = (TextView) window.findViewById(R.id.txtTipOne);
		txtTipTwo = (TextView) window.findViewById(R.id.txtTipTwo);
		btnTipOne = (LinearLayout) window.findViewById(R.id.btnTipOne);
		btnTipTwo = (LinearLayout) window.findViewById(R.id.btnTipTwo);
		btnOK = (LinearLayout) window.findViewById(R.id.btnRight);
		txtOK = (TextView) window.findViewById(R.id.txtRight);
		txtTipOne.setText(strTipOne);
		txtTipTwo.setText(strTipTwo);
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
		txtOK.setText(strOK);
		btnOK.setOnClickListener(onClickListenerBtn);
		btnTipOne.setOnClickListener(onClickListenerTipOne);
		btnTipTwo.setOnClickListener(onClickListenerTipTwo);
	}

	/**
	 * 设置图标
	 * @param id
	 */
	public RichfitPopUpTwoItems setImageTip(int resID)
	{
		this.iImageTipID = resID;
		return this;
	}

	/**
	 * 设置图标
	 * @param id
	 */
	public RichfitPopUpTwoItems setImageTip(Bitmap bm)
	{
		this.bitmapImageTip = bm;
		return this;
	}

	/**
	 * 显示内容
	 * @param content
	 */
	public RichfitPopUpTwoItems setContent(String sOne, String sTwo)
	{
		this.strTipOne = sOne;
		this.strTipTwo = sTwo;
		return this;
	}

	public RichfitPopUpTwoItems setTipOne(CharSequence text, OnClickListener listener)
	{
		this.strTipOne = text.toString();
		if (listener == null)
		{
			onClickListenerTipOne = new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					close();
				}
			};
		}
		else
		{
			onClickListenerTipOne = listener;
		}
		return this;
	}

	public RichfitPopUpTwoItems setTipTwo(CharSequence text, OnClickListener listener)
	{
		this.strTipTwo = text.toString();
		if (listener == null)
		{
			onClickListenerTipTwo = new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					close();
				}
			};
		}
		else
		{
			onClickListenerTipTwo = listener;
		}
		return this;
	}

	/**
	 * 取消按钮
	 * @param text
	 * @param listener
	 */
	public RichfitPopUpTwoItems setNegativeButton(CharSequence text, OnClickListener listener)
	{
		strOK = text.toString();
		if (listener == null)
		{
			onClickListenerBtn = new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					close();
				}
			};
		}
		else
		{
			onClickListenerBtn = listener;
		}
		return this;
	}
}
