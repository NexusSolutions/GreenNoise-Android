package com.hongji.greennoise;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;


import com.flurry.android.FlurryAgent;
import com.hongji.custom.*;

public class HomeActivity extends Activity implements OnItemClickListener, OnClickListener {
	
	private GridView 		mGridViewAlert;
	private GridView		mGridViewTone;
	private LinearLayout	mLayoutBg;
	private ImageView		mImgBg;
	private CustomListAdapter mListAdapterAlert;
	private CustomListAdapter mListAdapterTone;
	private ArrayList<SoundInfo> dataListAlert;
	private ArrayList<SoundInfo> dataListTone;
	
	private Button			mBtnPlay;
	private Button			mBtnSet;
	private Button			mBtnSlide;
	
	private TextView		mTextTitle;
	private LinearLayout	mLayoutTitle;
	
	private SoundInfo		mCurSelInfo;
	private MediaPlayer 	mPlayer;
	
	private boolean			mFlgTitle;
	
	private LinearLayout	mLayoutPreBg;
	private LinearLayout	mLayoutPre;
	
	private View 			mLandscapeView;
	private View 			mPortraitView;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    // TODO Auto-generated method stub
//	    setContentView(R.layout.activity_home);
	    mPortraitView = getLayoutInflater().inflate(R.layout.activity_home, null);
	    mLandscapeView = getLayoutInflater().inflate(R.layout.activity_home_landscape, null);
	    
	    if (getRotation(this)) {
	    	setContentView(mPortraitView);
	    } else {
	    	setContentView(mLandscapeView);
	    }
	    
	    initView();
	}
	
	public void onStart()
	{
	   super.onStart();
	   FlurryAgent.onStartSession(this, "NGWRXMH4PGDS6KHS2K8K");
	   // your code
	}

	public void onStop()
	{
	   super.onStop();
	   FlurryAgent.onEndSession(this);
	   // your code
	}
	
	public void initView() {
		mLayoutPreBg = (LinearLayout)findViewById(R.id.id_home_layout_preBg);
	    mLayoutPreBg.setOnClickListener(this);
	    
	    mLayoutPre = (LinearLayout)findViewById(R.id.id_home_layout_pre);
	    
	    mLayoutBg = (LinearLayout)findViewById(R.id.id_home_layoutBg);
	    mLayoutBg.setOnClickListener(this);
	    mImgBg = (ImageView)findViewById(R.id.id_home_imgBg);
	    mImgBg.setOnClickListener(this);
	    mFlgTitle = true;
	    
	    mBtnPlay = (Button)findViewById(R.id.id_home_btn_play);
	    mBtnPlay.setOnClickListener(this);
	    mBtnSet = (Button)findViewById(R.id.id_home_btn_set);
	    mBtnSet.setOnClickListener(this);
	    mBtnSlide = (Button)findViewById(R.id.id_home_btn_slide);
	    mBtnSlide.setOnClickListener(this);
	    
	    mTextTitle = (TextView)findViewById(R.id.id_home_text_title);
	    mLayoutTitle = (LinearLayout)findViewById(R.id.id_home_layout_title);
	    
	    mPlayer = new MediaPlayer();
	    mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
	    {
	        @Override
	        public void onCompletion(MediaPlayer mp)
	        {
	             //start Previous Activity here
	        	mBtnPlay.setBackgroundResource(R.drawable.play);
	        	mPlayer.stop();
	        	mPlayer.reset();

	        }
	    }); // video finish listener
	        
	    mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {

		    @Override
		    public boolean onError(MediaPlayer mp, int what, int extra) {
		        // Your code goes here
		    	mPlayer.stop();
		    	mPlayer.reset();
		        return true;
		    }
		});
	    
	    dataListAlert = new ArrayList<SoundInfo>();
	    mGridViewAlert = (GridView)findViewById(R.id.id_home_gridView_alert);
	    mListAdapterAlert = new CustomListAdapter(getApplicationContext());
	    mListAdapterAlert.mParentView = this;
	    mGridViewAlert.setAdapter(mListAdapterAlert);
//	    mGridViewAlert.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	    mGridViewAlert.setOnItemClickListener(this);
	    
	    try {
	    	JSONArray jsonList = new JSONArray(Global.gAlerts);
		
	    	for (int i = 0; i < jsonList.length(); i++) {
	    		JSONObject detail = jsonList.getJSONObject(i);
	    		String display = detail.getString("display");
	    		String thumb = detail.getString("thumb");
	    		String title = detail.getString("title");
	    		String audio = detail.getString("audio");
	    		
	    		SoundInfo info = new SoundInfo(display, thumb, title, audio);
	    		dataListAlert.add(info);
	    	}
	    } catch (JSONException e) {
			e.printStackTrace();
		}	    
	    mListAdapterAlert.setData(dataListAlert);
	    
	    
	    dataListTone = new ArrayList<SoundInfo>();
	    mGridViewTone = (GridView)findViewById(R.id.id_home_gridView_tone);
	    mListAdapterTone = new CustomListAdapter(getApplicationContext());
	    mListAdapterTone.mParentView = this;
	    mGridViewTone.setAdapter(mListAdapterTone);
//	    mGridViewTone.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	    mGridViewTone.setOnItemClickListener(this);
	    
	    try {
	    	JSONArray jsonList = new JSONArray(Global.gTones);
		
	    	for (int i = 0; i < jsonList.length(); i++) {
	    		JSONObject detail = jsonList.getJSONObject(i);
	    		String display = detail.getString("display");
	    		String thumb = detail.getString("thumb");
	    		String title = detail.getString("title");
	    		String audio = detail.getString("audio");
	    		
	    		SoundInfo info = new SoundInfo(display, thumb, title, audio);
	    		dataListTone.add(info);
	    	}
	    } catch (JSONException e) {
			e.printStackTrace();
		}	    
	    mListAdapterTone.setData(dataListTone);
	}
	
	public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
		
	}
	
	public void onClick(View v) {
		if (v.getId() == R.id.id_home_btn_play) {
			//set up MediaPlayer    
		    try {
		    	if (mPlayer.isPlaying()) {
		    		mBtnPlay.setBackgroundResource(R.drawable.play);
		    		mPlayer.stop();
		    		mPlayer.reset();
		    		return;
		    	}
		    	else
		    	{
		    		mBtnPlay.setBackgroundResource(R.drawable.pause);
		    	}
		    	
		    	Uri audio = Uri.parse("android.resource://com.hongji.greennoise/raw/" + mCurSelInfo.mAudioName);
		    	Log.v("path", "android.resource://com.hongji.greennoise/raw/" + mCurSelInfo.mAudioName);

		    	mPlayer = MediaPlayer.create(this, Integer.parseInt(mCurSelInfo.mAudioName));
		    	
		    	mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
			    {
			        @Override
			        public void onCompletion(MediaPlayer mp)
			        {
			             //start Previous Activity here
			        	mBtnPlay.setBackgroundResource(R.drawable.play);
			        	mPlayer.stop();
			        	mPlayer.reset();

			        }
			    }); // video finish listener
			        
			    mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {

				    @Override
				    public boolean onError(MediaPlayer mp, int what, int extra) {
				        // Your code goes here
				    	mPlayer.stop();
				    	mPlayer.reset();
				        return true;
				    }
				});
			    
 		    	mPlayer.setLooping(false);
 		    	mPlayer.setVolume(1.0f, 1.0f);
		    	mPlayer.start();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		} 
		else if (v.getId() == R.id.id_home_btn_set) {
//				Uri audio = Uri.parse("android.resource://com.hongji.greennoise/raw/" + mCurSelInfo.mAudioName);
//	            RingtoneManager.setActualDefaultRingtoneUri(
//	                    getApplicationContext(), RingtoneManager.TYPE_RINGTONE,
//	                    audio);
//	            Log .i("TESTT", "Ringtone Set to Resource: "+ audio.toString());
//	            RingtoneManager.getRingtone(getApplicationContext(), audio)
//	                    .play();
			
			File newSoundFile = new File("/sdcard/", mCurSelInfo.mTitle + ".oog");
			Uri mUri = Uri.parse("android.resource://com.hongji.greennoise/raw/" + mCurSelInfo.mAudioName);
			ContentResolver mCr = getContentResolver();
			AssetFileDescriptor soundFile;
			try {
			       soundFile= mCr.openAssetFileDescriptor(mUri, "r");
			   } catch (FileNotFoundException e) {
			       soundFile=null;   
			   }

			   try {
			      byte[] readData = new byte[1024];
			      FileInputStream fis = soundFile.createInputStream();
			      FileOutputStream fos = new FileOutputStream(newSoundFile);
			      int i = fis.read(readData);

			      while (i != -1) {
			        fos.write(readData, 0, i);
			        i = fis.read(readData);
			      }

			      fos.close();
			   } catch (IOException io) {
//				   io.printStackTrace();
//				   return;
			   }
			   
			   //update ringtone
			   Uri uri1 = MediaStore.Audio.Media.getContentUriForPath(newSoundFile.getAbsolutePath());  
			   int roweffected = getContentResolver().delete(uri1,  
			          MediaStore.MediaColumns.DATA + "=\"" + newSoundFile.getAbsolutePath() + "\"",  
			          null);

			   if(roweffected>0){
			     //ringtone deleted
			   }
			   else{
			     //ringtone not deleted
			   }
	   
			   
			   //////////////////
			   
			   ContentValues values = new ContentValues();
			   values.put(MediaStore.MediaColumns.DATA, newSoundFile.getAbsolutePath());
			   values.put(MediaStore.MediaColumns.TITLE, mCurSelInfo.mTitle);
			   values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/oog");
			   values.put(MediaStore.MediaColumns.SIZE, newSoundFile.length());
			   values.put(MediaStore.Audio.Media.ARTIST, R.string.app_name);
			   values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
			   values.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
			   values.put(MediaStore.Audio.Media.IS_ALARM, true);
			   values.put(MediaStore.Audio.Media.IS_MUSIC, false);

			   Uri uri = MediaStore.Audio.Media.getContentUriForPath(newSoundFile.getAbsolutePath());
			   Uri newUri = mCr.insert(uri, values);


			   try {
			       RingtoneManager.setActualDefaultRingtoneUri(this, RingtoneManager.TYPE_RINGTONE, newUri);
			   } catch (Throwable t) {
			       Log.d("tag", "catch exception");
			       t.printStackTrace();
			       return;
			   }
			   
			   AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
				builder.setTitle("Information");
				builder.setMessage("Set ringtone, successfully!");
				builder.setPositiveButton("OK", null);
				builder.show();
		}
		else if (v.getId() == R.id.id_home_btn_slide) {
			Intent infoIntent = new Intent(HomeActivity.this, InfoActivity.class);
			startActivity(infoIntent);
		}
		else if (v.getId() == R.id.id_home_layoutBg) {
			if (mFlgTitle) {
				mFlgTitle = false;
				mLayoutTitle.setVisibility(View.INVISIBLE);
			} else {
				mFlgTitle = true;
				mLayoutTitle.setVisibility(View.VISIBLE);
			}
		}
		else if (v.getId() == R.id.id_home_imgBg) {
			if (mFlgTitle) {
				mFlgTitle = false;
				mLayoutTitle.setVisibility(View.INVISIBLE);
			} else {
				mFlgTitle = true;
				mLayoutTitle.setVisibility(View.VISIBLE);
			}
		} 
		else if (v.getId() == R.id.id_home_layout_preBg) {
			mLayoutPre.setVisibility(View.INVISIBLE);
		}
	}
	
	public void onImageClick(SoundInfo info) {
//		mLayoutBg.setBackgroundResource(Integer.parseInt(info.mFileName));
		mImgBg.setImageResource(Integer.parseInt(info.mFileName));
		mTextTitle.setText(info.mTitle);
		
		mCurSelInfo = info;
		
		if (mLayoutPreBg.isShown()) {
			mLayoutPreBg.setVisibility(View.INVISIBLE);
			mLayoutPre.setVisibility(View.INVISIBLE);
		}
	}
	
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);

	    // Checks the orientation of the screen
	    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//	        Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
	        setContentView(mLandscapeView);
	        initView();
	    } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
//	        Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
	        setContentView(mPortraitView);
	        initView();
	    }
	  }

	public boolean getRotation(Context context){
	    final int rotation = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getOrientation();
	           switch (rotation) {
	            case Surface.ROTATION_0:
	                return true;
	            case Surface.ROTATION_90:
	                return false;
	            case Surface.ROTATION_180:
	                return true;
	            default:
	            	return false;
	            }
	 }
}
