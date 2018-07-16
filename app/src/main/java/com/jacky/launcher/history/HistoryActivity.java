package com.jacky.launcher.history;

import android.app.Activity;
import android.os.Bundle;

import com.jacky.launcher.R;

/**
 * @author jacky
 * @version v1.0
 * @since 16/8/28
 */
public class HistoryActivity extends Activity {

    public static final String His_Media = "His_Media";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
    }
}