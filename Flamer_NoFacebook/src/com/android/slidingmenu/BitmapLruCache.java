package com.android.slidingmenu;

import android.graphics.Bitmap;
import android.provider.MediaStore.Images.ImageColumns;
import android.support.v4.util.LruCache;


public class BitmapLruCache extends LruCache<String, Bitmap> implements
		ImageColumns {

	public static int getDefaultLruCacheSize() {
		final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
		final int cacheSize = maxMemory / 8;
		return cacheSize;
	}

	public BitmapLruCache() {
		this(getDefaultLruCacheSize());
	}

	public BitmapLruCache(int sizeInKiloBytes) {
		super(sizeInKiloBytes);
	}

	@Override
	protected int sizeOf(String key, Bitmap value) {
		return value.getRowBytes() * value.getHeight() / 1024;
	}

	public Bitmap getBitmap(String url) {
		return get(url);
	}

	
	public void putBitmap(String url, Bitmap bitmap) {
		put(url, bitmap);
	}

}