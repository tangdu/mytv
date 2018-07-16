package com.jacky.launcher.function;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.Presenter;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jacky.launcher.R;
import com.jacky.launcher.detail.MediaModel;

/**
 * ImageCard Presenter
 *
 * @author jacky
 * @version v1.0
 * @since 16/7/16
 */
public class FunctionCardPresenter extends Presenter {
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent) {
//        ImageView view = new ImageView(parent.getContext());
//        view.setLayoutParams(new ViewGroup.LayoutParams(CARD_WIDTH, CARD_HEIGHT));
//        view.setFocusable(true);
//        view.setFocusableInTouchMode(true);
//        view.setVisibility(View.VISIBLE);
//        view.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
//        ImageView cardView = (ImageView) viewHolder.view;
//        FunctionModel functionModel = (FunctionModel) item;
////        cardView.setImageResource(functionModel.getIcon());
//        Drawable drawable = cardView.getContext().getResources().getDrawable(functionModel.getIcon());
//        cardView.setImageDrawable(drawable);
////        Glide.with(cardView.getContext()).load(functionModel.getIcon()).placeholder(functionModel.getIcon()).diskCacheStrategy(DiskCacheStrategy.RESULT).into(cardView);
//    }
//
//    @Override
//    public void onUnbindViewHolder(ViewHolder viewHolder) {
//    }

    private Context mContext;
    private int CARD_WIDTH = 313;
    private int CARD_HEIGHT = 176;
    private Drawable mDefaultCardImage;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        mContext = parent.getContext();
        mDefaultCardImage = mContext.getResources().getDrawable(R.drawable.bk_default);
        ImageCardView cardView = new ImageCardView(mContext);
        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);
        cardView.setBackgroundColor(Color.GRAY);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        ImageCardView cardView = (ImageCardView) viewHolder.view;
        cardView.setMainImageDimensions(CARD_WIDTH,CARD_HEIGHT);
        FunctionModel functionModel = (FunctionModel) item;
        cardView.setMainImageScaleType(ImageView.ScaleType.FIT_XY);
        cardView.setPadding(0,0,0,0);
        cardView.getMainImageView().setImageResource(functionModel.getIcon());
        cardView.setTitleText(functionModel.getName());
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        ImageCardView cardView = (ImageCardView) viewHolder.view;
        cardView.setBadgeImage(null);
        cardView.setMainImage(null);
    }
}
