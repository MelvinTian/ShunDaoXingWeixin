package com.richfit.onlinescore.popupdialog;

import java.util.Timer;
import java.util.TimerTask;

import android.R.integer;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.richfit.onlinescore.R;

/**
 * TODO 统一的弹出框，下方包含一个按钮
 * @作者： 李子彦
 * @日期： 2014年9月15日-下午3:25:26
 */
public class RichfitPopUpDialogSingleButton
{
	private AlertDialog dlg; // 菜单的窗体
	private Window window;
	private TextView txtTip; // 标题的控件
	private ImageView imageTip; // 图标
	private LinearLayout btnOK;
	private TextView txtOK;

	private int iImageTipID = 0;
	private Bitmap bitmapImageTip = null;
	private String strTip;
	private String strOK = "确定";
	private OnClickListener onClickListenerBtn;

	Timer timer;

	public RichfitPopUpDialogSingleButton(Context context)
	{
		this.dlg = new AlertDialog.Builder(context).create();
		this.dlg.setCancelable(false);
	}

	/**
	 * 关闭菜单
	 */
	public void close()
	{
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
		window.setContentView(R.layout.popup_dialog_single);
		WindowManager.LayoutParams lp = window.getAttributes();
		lp.alpha = 1f;
		lp.dimAmount = 0f;
		lp.gravity = Gravity.CENTER_HORIZONTAL;
		lp.screenOrientation = Gravity.CENTER_HORIZONTAL;
		window.setAttributes(lp);
		txtTip = (TextView) window.findViewById(R.id.txtTip);
		imageTip = (ImageView) window.findViewById(R.id.imageTip);
		btnOK = (LinearLayout) window.findViewById(R.id.btnRight);
		txtOK = (TextView) window.findViewById(R.id.txtRight);
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
		txtOK.setText(strOK);
		btnOK.setOnClickListener(onClickListenerBtn);
	}

	/**
	 * 设置图标
	 * @param id
	 */
	public RichfitPopUpDialogSingleButton setImageTip(int resID)
	{
		this.iImageTipID = resID;
		return this;
	}

	/**
	 * 设置图标
	 * @param id
	 */
	public RichfitPopUpDialogSingleButton setImageTip(Bitmap bm)
	{
		this.bitmapImageTip = bm;
		return this;
	}

	/**
	 * 显示内容
	 * @param content
	 */
	public RichfitPopUpDialogSingleButton setContent(String content)
	{
		this.strTip = content;
		return this;
	}

	public RichfitPopUpDialogSingleButton setContentDelay(String content, int time)
	{
		if (null != timer)
		{
			timer.cancel();
		}
		timer = new Timer();
		timer.schedule(task, time);
		this.strTip = content;
		return this;
	}

	/**
	 * 取消按钮
	 * @param text
	 * @param listener
	 */
	public RichfitPopUpDialogSingleButton setNegativeButton(CharSequence text, OnClickListener listener)
	{
		strOK = text.toString();
		if (listener == null)
		{
			onClickListenerBtn = new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != timer)
					{
						timer.cancel();
					}
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

	/**
	 * 取消按钮
	 * @param text
	 * @param listener
	 */
	public RichfitPopUpDialogSingleButton setNegativeButtonDelay(CharSequence text, final int time, OnClickListener listener)
	{
		strOK = text.toString();
		if (listener == null)
		{
			onClickListenerBtn = new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (null != timer)
					{
						timer.cancel();
					}
					timer = new Timer();
					timer.schedule(task, time);
				}
			};
		}
		else
		{
			onClickListenerBtn = listener;
		}
		return this;
	}

	private TimerTask task = new TimerTask()
	{

		public void run()
		{
			timer.cancel();
			close();
		}

	};

}
