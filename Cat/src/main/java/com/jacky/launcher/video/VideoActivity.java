package com.jacky.launcher.video;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;

import com.jacky.launcher.R;
import com.jacky.launcher.detail.MediaModel;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;


public class VideoActivity extends AppCompatActivity implements PlayerManager.PlayerStateListener {

    private String url1 = "https://fzjy-oss-bucket.oss-cn-shenzhen.aliyuncs.com/upload/resData/20170516/5hSFSwTGKa.mp4";
    private String url2 = "http://zv.3gv.ifeng.com/live/zhongwen800k.m3u8";
    private String url3 = "rtsp://184.72.239.149/vod/mp4:BigBuckBunny_115k.mov";
    private String url4 = "http://cn2.okokzyyun.com/20171211/5ynbwi6C/800kb/hls/index.m3u8";
    private PlayerManager player;
    public static final String VIDEO = "Video";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initPlayer();
    }

    private void initPlayer() {
        player = new PlayerManager(this);
        player.setFullScreenOnly(true);
        player.setScaleType(PlayerManager.SCALETYPE_FILLPARENT);
        player.playInFullScreen(true);
        player.setPlayerStateListener(this);
        player.play(url4);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (player.gestureDetector.onTouchEvent(event))
            return true;
        return super.onTouchEvent(event);
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onError() {
    }

    @Override
    public void onLoading() {
    }

    @Override
    public void onPlay() {
    }

}
