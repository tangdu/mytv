package com.jacky.launcher.event;

import com.jacky.launcher.service.MovieInfoModel;

import java.util.List;

import lombok.Data;

@Data
public class MovieEvent {

    private List<MovieInfoModel> movieInfoModels;
}
