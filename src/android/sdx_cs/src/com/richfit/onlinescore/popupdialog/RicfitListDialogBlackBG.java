package com.richfit.onlinescore.popupdialog;

import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.richfit.onlinescore.R;
/**
 * listview的弹窗，背景为透明黑
 * TODO What the class does
 * @作者： SXQ
 * @日期： 2015年3月9日-下午9:06:36
 */
public class RicfitListDialogBlackBG
{
		private AlertDialog dlg;
		private Window window;
		private ListView listView;
		private Context context;
		private List<String> data;
		private OnItemClickListener onItemClickListener;
		
		public RicfitListDialogBlackBG(Context context,List<String> data)
		{
			this.context=context;
			this.data=data;
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
		
		public Dialog getInstance(){
			return this.dlg;
		}
		
		/**
		 * 显示菜单
		 */
		public void show(boolean isCenter)
		{
			dlg.show();
			dlg.setCanceledOnTouchOutside(true);
			window = dlg.getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
			window.setContentView(R.layout.listview_dialog_black);
			WindowManager.LayoutParams lp = window.getAttributes();
			lp.alpha = 1f;
			lp.dimAmount = 0f;
			lp.gravity = Gravity.CENTER_HORIZONTAL;
			lp.screenOrientation = Gravity.CENTER_HORIZONTAL;
			window.setAttributes(lp);
			listView=(ListView) window.findViewById(R.id.listview_dialog);
			ArrayAdapter<String> adapter=null;
			if(isCenter){
				adapter=new ArrayAdapter<String>(context, R.layout.listview_dialog_item_black,R.id.listview_dialog_item_text, data);
			}else{
				adapter=new ArrayAdapter<String>(context, R.layout.listview_dialog_item_black,R.id.listview_dialog_item_text, data);
			}
			listView.setAdapter(adapter);
		}
		
		/**
		 * list项的点击事件
		 * @param listener，调用者传入的监听
		 * @return 返回ListViewDialog的对象，让调用者持有该对象，从而获取用户点击的item项
		 */
		public RicfitListDialogBlackBG getSonforum(OnItemClickListener listener){
			if(listener==null){
				onItemClickListener=new OnItemClickListener()
				{

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id)
					{
						
						close();
					}
				};
			}else{
				onItemClickListener=listener;
			}
			listView.setOnItemClickListener(onItemClickListener);
			return this;
		}
}
