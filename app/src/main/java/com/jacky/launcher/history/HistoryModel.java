
package com.jacky.launcher.history;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import com.jacky.launcher.R;
import com.jacky.launcher.app.AppUninstall;
import com.jacky.launcher.detail.MediaModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryModel implements Parcelable {

    private int id;
    private String title;
    private String content;
    private String imageUrl;
    private String videoUrl;
    private int   currentViewTime;
    private int   videoTotalTime;

    public HistoryModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getCurrentViewTime() {
        return currentViewTime;
    }

    public void setCurrentViewTime(int currentViewTime) {
        this.currentViewTime = currentViewTime;
    }

    public int getVideoTotalTime() {
        return videoTotalTime;
    }

    public void setVideoTotalTime(int videoTotalTime) {
        this.videoTotalTime = videoTotalTime;
    }

    public static List<HistoryModel> getHistoryList(Context context) {
        List<HistoryModel> historyModels = new ArrayList<>();
        HistoryModel appUninstall = new HistoryModel();
        appUninstall.setTitle("权利的游戏");
        appUninstall.setImageUrl("http://e.hiphotos.baidu.com/zhidao/pic/item/5ab5c9ea15ce36d3418e754838f33a87e850b1c4.jpg");
//        historyModels.setIntent(new Intent(context, AppUninstall.class));
        historyModels.add(appUninstall);

        return historyModels;
    }

    public static final Creator<HistoryModel> CREATOR = new Creator<HistoryModel>() {
        @Override
        public HistoryModel createFromParcel(Parcel in) {
            return new HistoryModel(in);
        }

        @Override
        public HistoryModel[] newArray(int size) {
            return new HistoryModel[size];
        }
    };

    protected HistoryModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        content = in.readString();
        imageUrl = in.readString();
        videoUrl = in.readString();
        currentViewTime = in.readInt();
        videoTotalTime = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(imageUrl);
        dest.writeString(videoUrl);
        dest.writeInt(currentViewTime);
        dest.writeInt(videoTotalTime);
    }
}
