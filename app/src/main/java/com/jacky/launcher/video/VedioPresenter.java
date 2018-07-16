package com.jacky.launcher.video;

import android.graphics.Color;
import android.support.v17.leanback.widget.Presenter;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jacky.launcher.detail.MediaModel;

public class VedioPresenter extends Presenter{
    @Override
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        ImageButtonWithText view=new ImageButtonWithText(parent.getContext());
        return new Presenter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        ImageButtonWithText view = (ImageButtonWithText) viewHolder.view;
        if (item instanceof MediaModel) {
            MediaModel mediaModel = (MediaModel) item;
            view.textView.setText(mediaModel.getTitle());

            Glide.with(viewHolder.view.getContext())
                    .load(mediaModel.getCover())
                    .into(view.imageView);
        }
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
    }
}
