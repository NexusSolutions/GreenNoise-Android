package com.hongji.custom;

public class SoundInfo {
	public String	mFileName;
	public String	mThumbName;
	public String	mTitle;
	public String   mAudioName;
	public boolean	mFlgClick;
	
	public SoundInfo(String filename, String thumb, String title, String audio) {
		mFileName 	= filename;
		mThumbName 	= thumb;
		mTitle		= title;
		mAudioName 	= audio;
		mFlgClick	= false;
	}
}
