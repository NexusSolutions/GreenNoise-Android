package com.hongji.greennoise;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class CustomButton extends Activity {

	public Button    mBtn;
	public ImageView mImg;
	public boolean   mFlgClick;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    // TODO Auto-generated method stub
	    setContentView(R.layout.activity_custombutton);
	    
	    mFlgClick = false;
	    
	    mBtn = (Button)findViewById(R.id.id_cb_button);
	    mImg = (ImageView)findViewById(R.id.id_cb_img);
	    
	    mBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mFlgClick) {
					mFlgClick = false;
					mImg.setImageResource(R.drawable.display11);
				} else {
					mFlgClick = true;
					mImg.setImageResource(0);
				}
				
			}
		});    
	    
	}

}
