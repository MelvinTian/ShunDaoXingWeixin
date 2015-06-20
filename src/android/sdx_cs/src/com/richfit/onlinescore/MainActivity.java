/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.richfit.onlinescore;

import org.apache.cordova.CordovaActivity;
import org.apache.cordova.LOG;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;

import com.richfit.onlinescore.utils.CustomProgressDialog;

public class MainActivity extends CordovaActivity
{
	private static final String TAG = "MainActivity";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		super.init();
		// 加载提示框
		CustomProgressDialog.showCustomProgressDialog(this, null, "页面加载中,请稍候...");
		String uri = "file:///android_asset/www/index.html";
		Log.d(TAG, "url : " + uri);
		super.loadUrl(uri);
	}

	protected void onStart()
	{
		super.onStart();
	}

	protected void onResume()
	{
		super.onResume();
	};

	// 复写父类方法，用于重写布局增加标题栏
	@SuppressWarnings("deprecation")
	protected void createViews()
	{
		// This builds the view. We could probably get away with NOT having a
		// LinearLayout, but I like having a bucket!

		LOG.d(TAG, "CordovaActivity.createViews()");

		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();

		appView.setId(100);
		appView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1.0F));

		// Add web view but make it invisible while loading URL
		appView.setVisibility(View.INVISIBLE);

		// need to remove appView from any existing parent before invoking
		// root.addView(appView)
		ViewParent parent = appView.getParent();
		if ((parent != null) && (parent != root))
		{
			LOG.d(TAG, "removing appView from existing parent");
			ViewGroup parentGroup = (ViewGroup) parent;
			parentGroup.removeView(appView);
		}
		root.addView((View) appView);
		setContentView(root);

		int backgroundColor = preferences.getInteger("BackgroundColor", Color.BLACK);
		root.setBackgroundColor(backgroundColor);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent)
	{
		super.onActivityResult(requestCode, resultCode, intent);
	}

	/**
	 * @see org.apache.cordova.CordovaActivity#onDestroy()
	 */
	@Override
	public void onDestroy()
	{
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{

		if (event.getAction() == KeyEvent.ACTION_DOWN)
		{
			if (keyCode == KeyEvent.KEYCODE_BACK)
			{
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
}