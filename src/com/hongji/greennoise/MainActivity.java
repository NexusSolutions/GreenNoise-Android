package com.hongji.greennoise;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.*;

public class MainActivity extends Activity {

	VideoView	mVideoView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mVideoView = (VideoView)findViewById(R.id.id_main_videoView);
		mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
	    {
	        @Override
	        public void onCompletion(MediaPlayer mp)
	        {
	             //start Previous Activity here
	        	MainActivity.this.finish();
	        	Intent loginIntent = new Intent(MainActivity.this, HomeActivity.class);
				startActivity(loginIntent);

	        }
	    }); // video finish listener
		
		mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {

		    @Override
		    public boolean onError(MediaPlayer mp, int what, int extra) {
		        // Your code goes here
		    	MainActivity.this.finish();
		    	Intent loginIntent = new Intent(MainActivity.this, HomeActivity.class);
				startActivity(loginIntent);
		        return true;
		    }
		});
		
		MediaController mediaController = new MediaController (this);
	    mediaController.setAnchorView(mVideoView);
	    Uri video = Uri.parse("android.resource://com.hongji.greennoise/raw/appintrov3_960");
//	    mVideoView.setMediaController(mediaController);
	    mVideoView.setMediaController(null);
	    mVideoView.setVideoURI(video);
//	    mVideoView.start();
	    Intent loginIntent = new Intent(MainActivity.this, HomeActivity.class);
		startActivity(loginIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
