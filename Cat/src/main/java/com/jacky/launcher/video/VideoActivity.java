package com.jacky.launcher.video;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import com.jacky.launcher.R;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.pili.pldroid.player.widget.PLVideoView;



public class VideoActivity extends AppCompatActivity  {

    private String url1 = "https://fzjy-oss-bucket.oss-cn-shenzhen.aliyuncs.com/upload/resData/20170516/5hSFSwTGKa.mp4";
    private String url2 = "http://zv.3gv.ifeng.com/live/zhongwen800k.m3u8";
    private String url3 = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_115k.mov";
    private String url4 = "http://yunxianchang.live.ujne7.com/vod-system-bj/44_176_20170224113626af3a75cd-3508-4bc3-b51f-366fca3c7e39.m3u8";
    private PLVideoTextureView mVideoView;
    private MediaController mMediaController;
    public static final String VIDEO = "Video";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initPlayer();
    }

    private void initPlayer() {
        AVOptions options = new AVOptions();
        mVideoView = (PLVideoTextureView) findViewById(R.id.video_player);
        mMediaController = new MediaController(this);
        mVideoView.setMediaController(mMediaController);
        mVideoView.setAVOptions(options);
        mVideoView.setVideoPath(url4);
        mVideoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
