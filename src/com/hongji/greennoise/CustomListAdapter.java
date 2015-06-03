package com.hongji.greennoise;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.*;

import com.hongji.custom.*;


public class CustomListAdapter extends BaseAdapter implements OnClickListener {
	private LayoutInflater inflater;
    private ArrayList<SoundInfo> data;
    private ImageView	mCurSelView;
    public  HomeActivity mParentView;
        
    public CustomListAdapter(Context context) {
	    this.inflater = LayoutInflater.from(context);
	    this.data = new ArrayList<SoundInfo>();
	    mCurSelView = null;
    }
    
    public CustomListAdapter(Context context, ArrayList<SoundInfo> dataDisplay){
	    this.inflater = LayoutInflater.from(context);
	    this.data = new ArrayList<SoundInfo>();
	    for (int i = 0; i < dataDisplay.size(); i++)
	    	this.data.add(dataDisplay.get(i));
    }
    
    public void setData(ArrayList<SoundInfo> dataDisplay) {
    	this.data.clear();
    	for (int i = 0; i < dataDisplay.size(); i++)
    		this.data.add(dataDisplay.get(i));
    	this.notifyDataSetChanged();
    }

	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.data.get(position);
	}

	public long getItemId(int position) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if(position < getCount() && position >= 0 ){
            return position;
        }
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		SoundInfo info = (SoundInfo)getItem(position);
		
//		if (convertView == null) {
			convertView  = this.inflater.inflate(R.layout.activity_custombutton, null);
			
			Button mBtn = (Button)convertView.findViewById(R.id.id_cb_button);
			
			int display_id = 0;
			int thumb_id = 0;
			
			try {
				display_id = Integer.parseInt(info.mFileName);
				thumb_id = Integer.parseInt(info.mThumbName);
				
			} catch(NumberFormatException nfe) {
			   System.out.println("Could not parse " + nfe);
			} 
			
			mBtn.setBackgroundResource(thumb_id);
		    ImageView mImg = (ImageView)convertView.findViewById(R.id.id_cb_img);
		    
		    mImg.setTag(position);
		    mImg.setOnClickListener(this);
//		}
		
		return convertView;
	}
	
	public static Drawable LoadImageFromWebOperations(String url) {
		try {
			InputStream is = (InputStream)new URL(url).getContent();
			Drawable d = Drawable.createFromStream(is, "src name");
			return d;
		} catch (Exception e) {
			return null;
		}
	}
	
	public void onClick(View v) {
		if (v.getId() == R.id.id_cb_img) {
			if (Global.gCurSelView != null) {
//				SoundInfo curInfo = (SoundInfo)getItem((Integer)Global.gCurSelView.getTag());
//				curInfo.mFlgClick = false;
				
				Global.gCurSelView.setImageResource(0);
			}
			Global.gCurSelView = (ImageView)v;
			
			int idx = (Integer)v.getTag();
			SoundInfo info = (SoundInfo)getItem(idx);
			
//			if (info.mFlgClick) {
//				info.mFlgClick = false;
//				((ImageView)v).setImageResource(0);
				
//			} else {
//				info.mFlgClick = true;
				((ImageView)v).setImageResource(R.drawable.selected);
//			}
			
			mParentView.onImageClick(info);
		}
	}

}
