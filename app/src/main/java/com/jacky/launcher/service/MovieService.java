package com.jacky.launcher.service;

import android.util.JsonReader;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jacky.launcher.detail.MediaModel;
import com.jacky.launcher.video.DebugLog;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieService {

    //查看观看历史
    public List<MediaModel> getViewHistory(FileInputStream inStream) {
        long start = System.currentTimeMillis();
        final Gson gson = new Gson();
        List<MediaModel> result = new ArrayList<>();
        try {
            ByteArrayOutputStream stream=new ByteArrayOutputStream();
            byte[] buffer=new byte[256];
            int length=-1;
            while((length=inStream.read(buffer))!=-1)   {
                stream.write(buffer,0,length);
            }
            stream.close();
            inStream.close();
            if("".equals(stream.toString())){
                return result;
            }
            result = gson.fromJson(stream.toString(), new TypeToken<List<MediaModel>>() {
            }.getType());
            return result;
        } catch (Exception e) {
            DebugLog.e("getViewHistory_error" + e.getMessage());
        } finally {
            DebugLog.e("getViewHistory_info result=" + result + ",cost=" + (System.currentTimeMillis() - start));
        }
        return result;
    }

    //查看电影资源
    public List<MovieInfoModel> getMainMovieList() {

        long start = System.currentTimeMillis();
        final OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url("http://47.106.106.7/api/movie/list").build();
        final Gson gson = new Gson();
        List<MovieInfoModel> result = new ArrayList<>();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String body = response.body().string();
                String headers = response.headers().toString();
                DebugLog.i("getMainMovieList" + "run: " + body);
                result = gson.fromJson(body, new TypeToken<List<MovieInfoModel>>() {
                }.getType());
                return result;
            } else {
                DebugLog.e("getMainMovieList" + "run: " + response.code() + response.message());
            }
            DebugLog.i("getMainMovieList" + "run: ");
        } catch (Exception e) {
            DebugLog.e("getMainMovieList_error " + e.getMessage());
        } finally {
            DebugLog.e("getMainMovieList_info result=" + result + ",cost=" + (System.currentTimeMillis() - start));
        }
        return result;
    }

    //查看所有分类
    public List<CategoryModel> getCategory() {
        long start = System.currentTimeMillis();
        final OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url("http://47.106.106.7/api/movie/category")
                .cacheControl(new CacheControl.Builder()
                        .maxStale(1, TimeUnit.HOURS).build()).build();

        final Gson gson = new Gson();
        List<CategoryModel> result = new ArrayList<>();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String body = response.body().string();
                String headers = response.headers().toString();
                DebugLog.i("getCategory" + "run: " + body);
                result = gson.fromJson(body, new TypeToken<List<CategoryModel>>() {
                }.getType());
                return result;
            } else {
                DebugLog.e("getCategory" + "run: " + response.code() + response.message());
            }
            DebugLog.i("getCategory" + "run: ");
        } catch (Exception e) {
            DebugLog.e("getCategory_error" + e.getMessage());
        } finally {
            DebugLog.e("getCategory_info result=" + result + ",cost=" + (System.currentTimeMillis() - start));
        }
        return result;
    }
}
