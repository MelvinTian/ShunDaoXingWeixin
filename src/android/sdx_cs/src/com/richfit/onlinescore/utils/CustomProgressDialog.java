/* 
* Copyright (C) 2013,中油瑞飞, 
* All Rights Reserved 
* Description: 操作进度条.   
* 
* @author  AZer
* @date    2014-7-8-上午10:30:32
*
* 代码修改历史: 
**********************************************************
* 时间		       作者		          注释
* 2014-7-8	     孙东旭	     	Create
**********************************************************
*/
package com.richfit.onlinescore.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;

import com.richfit.onlinescore.popupdialog.RichfitProgressDialog;

/**
 * 进度条工具类
 * @author 孙东旭
 * @date   2014-7-8-上午10:30:32
 *
 */
public class CustomProgressDialog {
	private  static   RichfitProgressDialog  richfitProgressDialog = null;
	/**
	 * 结束当前进度条实例
	 */
	public  static  void   dismissNowDialog(){
		if(richfitProgressDialog!=null)
		{
			richfitProgressDialog.dismiss();
		}
		
	}
	/**
	 * 简单的展示旋转进度条
	 * @param context  上下文
	 * @param title    抬头
	 * @param showContent  显示内容
	 */
	public static  void showCustomProgressDialog(Context context ,String title,final String showContent){
		richfitProgressDialog  = new RichfitProgressDialog(context);
		richfitProgressDialog.setMessage(showContent);
		richfitProgressDialog.setCanceledOnTouchOutside(false);
		richfitProgressDialog.setCancelable(true);
		richfitProgressDialog.show();
	}
	

}
