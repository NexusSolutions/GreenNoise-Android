package com.hongji.custom;

import android.content.*;
import java.util.*;
import android.content.res.Configuration;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;

import android.view.View.OnClickListener;
import android.view.animation.*;
import android.widget.ImageView;
import android.content.res.Resources;
import java.util.Locale;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import com.hongji.greennoise.R;

import android.util.DisplayMetrics;
import android.telephony.TelephonyManager;                                           
import android.content.Context;

public class Global {
		
	public static String	gAlerts = "[{display: " + R.drawable.display11 + ", thumb: " +  + R.drawable.thumb11 + ", title: \"Rain Forest Melody\"" + ", audio: " + R.raw.greenalert_001 + "}," +
			"{display: " + R.drawable.display12 + ", thumb: " +  + R.drawable.thumb12 + ", title: \"Margay Wisdom\"" + ", audio: " + R.raw.greenalert_002 + "}," +
			"{display: " + R.drawable.display13 + ", thumb: " +  + R.drawable.thumb13 + ", title: \"Calls in the Great Mountain\"" + ", audio: " + R.raw.greenalert_003 + "}," +
			"{display: " + R.drawable.display14 + ", thumb: " +  + R.drawable.thumb14 + ", title: \"The Great Toad\"" + ", audio: " + R.raw.greenalert_004 + "}," +
			"{display: " + R.drawable.display21 + ", thumb: " +  + R.drawable.thumb21 + ", title: \"Rolling Thunder\"" + ", audio: " + R.raw.greenalert_005 + "}," +
			"{display: " + R.drawable.display22 + ", thumb: " +  + R.drawable.thumb22 + ", title: \"Howler Awakening\"" + ", audio: " + R.raw.greenalert_006 + "}," +
			"{display: " + R.drawable.display23 + ", thumb: " +  + R.drawable.thumb23 + ", title: \"Jaguar King\"" + ", audio: " + R.raw.greenalert_007 + "}," +
			"{display: " + R.drawable.display24 + ", thumb: " +  + R.drawable.thumb24 + ", title: \"Joy in the Forest\"" + ", audio: " + R.raw.greenalert_008 + "}," +
			"{display: " + R.drawable.display31 + ", thumb: " +  + R.drawable.thumb31 + ", title: \"Prowling Puma\"" + ", audio: " + R.raw.greenalert_009 + "}," +
			"{display: " + R.drawable.display32 + ", thumb: " +  + R.drawable.thumb32 + ", title: \"Afternoon in the Marsh\"" + ", audio: " + R.raw.greenalert_010 + "}," +
			"{display: " + R.drawable.display33 + ", thumb: " +  + R.drawable.thumb33 + ", title: \"Wetland Magic\"" + ", audio: " + R.raw.greenalert_011 + "}," +
			"{display: " + R.drawable.display34 + ", thumb: " +  + R.drawable.thumb34 + ", title: \"Electric Cicada\"" + ", audio: " + R.raw.greenalert_012 + "}," +
			"{display: " + R.drawable.display41 + ", thumb: " +  + R.drawable.thumb41 + ", title: \"Tropical Song\"" + ", audio: " + R.raw.greenalert_013 + "}," +
			"{display: " + R.drawable.display42 + ", thumb: " +  + R.drawable.thumb42 + ", title: \"Big Cat Small Voice\"" + ", audio: " + R.raw.greenalert_014 + "}," +
			"{display: " + R.drawable.display43 + ", thumb: " +  + R.drawable.thumb43 + ", title: \"Nest the nest\"" + ", audio: " + R.raw.greenalert_015 + "}," +
			"{display: " + R.drawable.display44 + ", thumb: " +  + R.drawable.thumb44 + ", title: \"Mangrove Harmony\"" + ", audio: " + R.raw.greenalert_016 + "}]";
	
	public static String	gTones = "[{display: " + R.drawable.display15 + ", thumb: " +  + R.drawable.thumb15 + ", title: \"Fresh Green Tunes\"" + ", audio: " + R.raw.greentone_001 + "}," +
			"{display: " + R.drawable.display16 + ", thumb: " +  + R.drawable.thumb16 + ", title: \"Jungle Choir\"" + ", audio: " + R.raw.greentone_002 + "}," +
			"{display: " + R.drawable.display17 + ", thumb: " +  + R.drawable.thumb17 + ", title: \"Natural Fills\"" + ", audio: " + R.raw.greentone_003 + "}," +
			"{display: " + R.drawable.display18 + ", thumb: " +  + R.drawable.thumb18 + ", title: \"Symphony of Birds\"" + ", audio: " + R.raw.greentone_004 + "}," +
			"{display: " + R.drawable.display25 + ", thumb: " +  + R.drawable.thumb25 + ", title: \"Noon in the Rainforest\"" + ", audio: " + R.raw.greentone_005 + "}," +
			"{display: " + R.drawable.display26 + ", thumb: " +  + R.drawable.thumb26 + ", title: \"Darkening Dusk\"" + ", audio: " + R.raw.greentone_006 + "}," +
			"{display: " + R.drawable.display27 + ", thumb: " +  + R.drawable.thumb27 + ", title: \"The Awakening of the Jungle\"" + ", audio: " + R.raw.greentone_007 + "}," +
			"{display: " + R.drawable.display28 + ", thumb: " +  + R.drawable.thumb28 + ", title: \"Howler's Dusk\"" + ", audio: " + R.raw.greentone_008 + "}," +
			"{display: " + R.drawable.display35 + ", thumb: " +  + R.drawable.thumb35 + ", title: \"Voices of Carara\"" + ", audio: " + R.raw.greentone_009 + "}," +
			"{display: " + R.drawable.display36 + ", thumb: " +  + R.drawable.thumb36 + ", title: \"Mountain Melodies\"" + ", audio: " + R.raw.greentone_010 + "}," +
			"{display: " + R.drawable.display37 + ", thumb: " +  + R.drawable.thumb37 + ", title: \"Puma Curiosity\"" + ", audio: " + R.raw.greentone_011 + "}," +
			"{display: " + R.drawable.display38 + ", thumb: " +  + R.drawable.thumb38 + ", title: \"Flyers of the Forest\"" + ", audio: " + R.raw.greentone_012 + "}," +
			"{display: " + R.drawable.display45 + ", thumb: " +  + R.drawable.thumb45 + ", title: \"Voices of the Night\"" + ", audio: " + R.raw.greentone_013 + "}," +
			"{display: " + R.drawable.display46 + ", thumb: " +  + R.drawable.thumb46 + ", title: \"Wetland Lucid Dreams\"" + ", audio: " + R.raw.greentone_014 + "}," +
			"{display: " + R.drawable.display47 + ", thumb: " +  + R.drawable.thumb47 + ", title: \"Feast of Birds\"" + ", audio: " + R.raw.greentone_015 + "}," +
			"{display: " + R.drawable.display48 + ", thumb: " +  + R.drawable.thumb48 + ", title: \"Through the Mangrove\"" + ", audio: " + R.raw.greentone_016 + "}]";

	public static ImageView	gCurSelView = null;
}
