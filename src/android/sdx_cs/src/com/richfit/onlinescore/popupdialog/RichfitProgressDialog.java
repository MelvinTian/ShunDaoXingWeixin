/*
 * Copyright (C) 2013,中油瑞飞, 移动业务部，企信开发小组
 * All Rights Reserved 
 * 描述： TODO 描述类的主要功能.   
 * 项目： 企信项目
 * 作者 ：Administrator
 * 日期“ 2014年9月11日-下午2:33:54
 *
 * 代码修改历史: 
 **********************************************************
 * 时间		       作者		          注释
 * 2014年9月11日	       Administrator		     	Create
 **********************************************************
 */
package com.richfit.onlinescore.popupdialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import com.richfit.onlinescore.R;

/**
 * TODO 等待框，有个菊花在转那种，模态的
 * @作者： Administrator
 * @日期： 2014年9月11日-下午2:33:54
 */
public class RichfitProgressDialog extends ProgressDialog
{
	/**
	 * 定义提示文字
	 */
	private String strMessage = "";

	public RichfitProgressDialog(Context context, int theme)
	{
		super(context, theme);
	}

	public RichfitProgressDialog(Context context)
	{
		super(context);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress_dialog);
		setProgressStyle(ProgressDialog.STYLE_SPINNER);
		this.setOnShowListener(new OnShowListener()
		{
			@Override
			public void onShow(DialogInterface dialog)
			{
				TextView txtTip = (TextView) RichfitProgressDialog.this.findViewById(R.id.txtTip);
				txtTip.setText(strMessage);
			}
		});
	}

	/**
	 * 提示文字
	 * @see android.app.ProgressDialog#setMessage(java.lang.CharSequence)
	 */
	@Override
	public void setMessage(CharSequence message)
	{
		// TODO Auto-generated method stub
		this.strMessage = message.toString();
		super.setMessage(message);
	}
}
