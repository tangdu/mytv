
package com.jacky.launcher.detail;

import android.os.Parcel;
import android.os.Parcelable;

import com.jacky.launcher.history.HistoryModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MediaModel implements Parcelable {

    private int id;
    //电影标题
    private String title;
    //电影简介
    private String content;
    //封图
    private String cover;
    //电影图片
    private List<String> imageUrl;
    //电影路径
    private String videoUrl;
    //电影类型(1:电影,2:美剧,3:连续剧)
    private int type;
    //电影年份
    private int year;
    //电影产地
    private String belong;
    //电影导演
    private String direction;
    //电影主演
    private String lead;
    //电影时长(秒)
    private int totalTime;
    //当前观看时间(秒)
    private int currentViewTime;
    //标签(动作、战争、科幻、冒险)
    private List<String> tag;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.cover);
        dest.writeStringList(this.imageUrl);
        dest.writeString(this.videoUrl);
        dest.writeInt(this.type);
        dest.writeInt(this.year);
        dest.writeString(this.belong);
        dest.writeString(this.direction);
        dest.writeString(this.lead);
        dest.writeInt(this.totalTime);
        dest.writeInt(this.currentViewTime);
        dest.writeStringList(this.tag);
    }

    protected MediaModel(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.content = in.readString();
        this.cover = in.readString();
        this.imageUrl = in.createStringArrayList();
        this.videoUrl = in.readString();
        this.type = in.readInt();
        this.year = in.readInt();
        this.belong = in.readString();
        this.direction = in.readString();
        this.lead = in.readString();
        this.totalTime = in.readInt();
        this.currentViewTime = in.readInt();
        this.tag = in.createStringArrayList();
    }

    public static final Creator<MediaModel> CREATOR = new Creator<MediaModel>() {
        @Override
        public MediaModel createFromParcel(Parcel source) {
            return new MediaModel(source);
        }

        @Override
        public MediaModel[] newArray(int size) {
            return new MediaModel[size];
        }
    };

}
