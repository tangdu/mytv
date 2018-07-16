package com.jacky.launcher.video;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import com.jacky.launcher.R;
import com.jacky.launcher.detail.MediaModel;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.pili.pldroid.player.widget.PLVideoView;



public class VideoActivity extends Activity  {

    private PLVideoTextureView mVideoView;
    private MediaController mMediaController;
    public static final String VIDEO = "Video";
    MediaModel mediaModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Intent intent = getIntent();
        Bundle bundleExtra = intent.getExtras();
        mediaModel = bundleExtra.getParcelable(VideoActivity.VIDEO);
        initPlayer();
    }

    private void initPlayer() {
        AVOptions options = new AVOptions();
        mVideoView = (PLVideoTextureView) findViewById(R.id.video_player);
        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_16_9);
        mVideoView.setBackgroundColor(Color.BLACK);
        mMediaController = new MediaController(this);
        mVideoView.setMediaController(mMediaController);
        mVideoView.setAVOptions(options);
        mVideoView.setVideoPath(mediaModel.getVideoUrl());
        mVideoView.start();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mVideoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideoView.start();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.stopPlayback();
    }
}
