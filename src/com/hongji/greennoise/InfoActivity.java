package com.hongji.greennoise;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class InfoActivity extends Activity implements OnClickListener {
	
	Button	mBtnClose;
	Button	mBtnFacebook;
	Button	mBtnWebsite;
	Button	mBtnInstagram;
	
	private View	mLandscapeView;
	private View	mPortraitView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    // TODO Auto-generated method stub
//	    setContentView(R.layout.activity_info);
	    mPortraitView = getLayoutInflater().inflate(R.layout.activity_info, null);
	    mLandscapeView = getLayoutInflater().inflate(R.layout.activity_info_landscape, null);
	    
	    if (getRotation(this)) {
	    	setContentView(mPortraitView);
	    } else {
	    	setContentView(mLandscapeView);
	    }
	    
	    initView();
	}
	
	public void initView() {
		mBtnClose = (Button)findViewById(R.id.id_info_btn_close);
	    mBtnClose.setOnClickListener(this);
	    mBtnFacebook = (Button)findViewById(R.id.id_info_btn_facebook);
	    mBtnFacebook.setOnClickListener(this);
	    mBtnWebsite = (Button)findViewById(R.id.id_info_btn_website);
	    mBtnWebsite.setOnClickListener(this);
	    mBtnInstagram = (Button)findViewById(R.id.id_info_btn_instagram);
	    mBtnInstagram.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		if (v.getId() == R.id.id_info_btn_close) {
			finish();
		}
		else if (v.getId() == R.id.id_info_btn_facebook) {
			Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
            myWebLink.setData(Uri.parse("http://www.facebook.com/GreenNoiseapp"));
            startActivity(myWebLink);
		}
		else if (v.getId() == R.id.id_info_btn_website) {
			Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
            myWebLink.setData(Uri.parse("http://greennoise.cr/"));
            startActivity(myWebLink);
		}
		else if (v.getId() == R.id.id_info_btn_instagram) {
			Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
            myWebLink.setData(Uri.parse("http://www.instagram.com/greennoise"));
            startActivity(myWebLink);
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

	//porait: true, landscape: false
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
