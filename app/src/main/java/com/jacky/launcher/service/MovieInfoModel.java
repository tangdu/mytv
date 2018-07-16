package com.jacky.launcher.service;

import com.jacky.launcher.detail.MediaModel;

import java.util.List;

import lombok.Data;

@Data
public class MovieInfoModel {

    private String categoryCode;
    private String categoryTitle;
    private List<MediaModel> mediaModelList;
}
