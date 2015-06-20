package com.richfit.onlinescore.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class AsyncImageLoader {

	private HashMap<String, SoftReference<Drawable>> imageCache;

	public AsyncImageLoader() {
		imageCache = new HashMap<String, SoftReference<Drawable>>();
	}

	public Drawable loadDrawable(final String imageUrl, final ImageCallback imageCallback) {
		if (imageCache.containsKey(imageUrl)) {
			SoftReference<Drawable> softReference = imageCache.get(imageUrl);
			Drawable drawable = softReference.get();
			if (drawable != null) {
				return drawable;
			}
		}
		final Handler handler = new Handler() {
			public void handleMessage(Message message) {
				if (message.obj != null)
				{
					imageCallback.imageLoaded((Drawable) message.obj, imageUrl);
				} else {
					imageCallback.imageLoaded(null, imageUrl);
				}
			}
		};
		new Thread() {
			public void run() {
				Drawable drawable = loadImageFromUrl(imageUrl);
				if (drawable != null)
				{
					imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
				}
				Message message = handler.obtainMessage(0, drawable);
				handler.sendMessage(message);
			}
		}.start();
		return null;
	}

	public static Drawable loadImageFromUrl(String url) {
		URL m;
		InputStream i = null;
		try {
			m = new URL(url);
			i = (InputStream) m.getContent();
		} catch (MalformedURLException e1) {
			Log.d("Load image error", e1.getMessage(), e1);
			i = null;
		} catch (IOException e) {
			Log.d("Load image error", e.getMessage(), e);
			i = null;
		}
		if (i != null)
		{
			Drawable d = Drawable.createFromStream(i, "src");
			return d;
		} else {
			return null;
		}
	}

	public interface ImageCallback {
		public void imageLoaded(Drawable imageDrawable, String imageUrl);
	}

}