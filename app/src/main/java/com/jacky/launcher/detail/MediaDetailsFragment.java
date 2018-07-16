package com.jacky.launcher.detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.app.DetailsFragment;
import android.support.v17.leanback.widget.Action;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ClassPresenterSelector;
import android.support.v17.leanback.widget.DetailsOverviewLogoPresenter;
import android.support.v17.leanback.widget.DetailsOverviewRow;
import android.support.v17.leanback.widget.FullWidthDetailsOverviewRowPresenter;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnActionClickedListener;
import android.support.v17.leanback.widget.SparseArrayObjectAdapter;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.jacky.launcher.video.VideoActivity;

/**
 * @author jacky
 * @version v1.0
 * @since 16/8/28
 */
public class MediaDetailsFragment extends DetailsFragment {

    private ArrayObjectAdapter mRowsAdapter;
    private MediaModel mMediaModel;
    private Context mContext;
    private static final int ACTION_WATCH_TRAILER = 1;

    private BackgroundManager mBackgroundManager;
    private DisplayMetrics mMetrics;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity();
        mMediaModel = getActivity().getIntent().getParcelableExtra(MediaDetailsActivity.MEDIA);

        prepareBackgroundManager();
        buildDetails();
    }

    private void prepareBackgroundManager() {
        mBackgroundManager = BackgroundManager.getInstance(getActivity());
        mBackgroundManager.attach(getActivity().getWindow());
        mMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
    }

    private void buildDetails() {
        ClassPresenterSelector selector = new ClassPresenterSelector();
        FullWidthDetailsOverviewRowPresenter rowPresenter = new FullWidthDetailsOverviewRowPresenter(
                new MediaDetailsDescriptionPresenter(),
                new DetailsOverviewLogoPresenter());

        rowPresenter.setOnActionClickedListener(new OnActionClickedListener() {
            @Override
            public void onActionClicked(Action action) {
                if (action.getId() == ACTION_WATCH_TRAILER) {
                    Intent intent = new Intent(getActivity(), VideoActivity.class);
                    intent.putExtra(VideoActivity.VIDEO, mMediaModel);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), action.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        selector.addClassPresenter(DetailsOverviewRow.class, rowPresenter);
        selector.addClassPresenter(ListRow.class, new ListRowPresenter());
        mRowsAdapter = new ArrayObjectAdapter(selector);

        final DetailsOverviewRow detailsOverview = new DetailsOverviewRow(mMediaModel);
        //设置头图
        Glide.with(mContext)
                .load(mMediaModel.getCover())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        detailsOverview.setImageDrawable(resource);
                        return true;
                    }
                });
        //设置背景图
        updateBackground(mMediaModel.getImageUrl().get(0));

        SparseArrayObjectAdapter adapter = new SparseArrayObjectAdapter();
        if (!mMediaModel.getVideoUrl().isEmpty()) {
            adapter.set(ACTION_WATCH_TRAILER, new Action(ACTION_WATCH_TRAILER, "播放"));
        }
        detailsOverview.setActionsAdapter(adapter);
        mRowsAdapter.add(detailsOverview);

        setAdapter(mRowsAdapter);
    }

    private void updateBackground(String uri) {
        Glide.with(this)
                .load(uri)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        mBackgroundManager.setDrawable(resource);
                    }
                });
    }

    @Override
    public void onStop() {
        mBackgroundManager.release();
        super.onStop();
    }
}