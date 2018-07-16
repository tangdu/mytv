
package com.jacky.launcher.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.DividerRow;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jacky.launcher.R;
import com.jacky.launcher.app.AppCardPresenter;
import com.jacky.launcher.app.AppDataManage;
import com.jacky.launcher.app.AppModel;
import com.jacky.launcher.detail.MediaDetailsActivity;
import com.jacky.launcher.detail.MediaModel;
import com.jacky.launcher.function.FunctionCardPresenter;
import com.jacky.launcher.function.FunctionModel;
import com.jacky.launcher.history.HistoryModel;
import com.jacky.launcher.service.MovieInfoModel;
import com.jacky.launcher.service.MovieService;
import com.jacky.launcher.video.DebugLog;
import com.jacky.launcher.video.VedioPresenter;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    protected BrowseFragment mBrowseFragment;
    private ArrayObjectAdapter rowsAdapter;
    private BackgroundManager mBackgroundManager;
    private DisplayMetrics mMetrics;
    private Context mContext;
    private MovieService movieService=new MovieService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String headerName = getResources().getString(R.string.app_header_app_name);
//        EventBus.getDefault().register(this);

        mContext = this;
        mBrowseFragment = (BrowseFragment) getFragmentManager().findFragmentById(R.id.browse_fragment);
        mBrowseFragment.setHeadersState(BrowseFragment.HEADERS_DISABLED);
        mBrowseFragment.setTitle(headerName);
        prepareBackgroundManager();
        buildRowsAdapter();
        new DownTask().execute();
    }

    public class DownTask extends AsyncTask<Void, Void, List<MovieInfoModel>> {
        @Override
        protected List<MovieInfoModel> doInBackground(Void... voids) {
            List<MovieInfoModel> mainMovieList = movieService.getMainMovieList();
            return mainMovieList;
        }

        @Override
        protected void onPostExecute(List<MovieInfoModel> movieInfoModels) {
            addVideoRow(movieInfoModels);
        }
    }


    private void prepareBackgroundManager() {
        mBackgroundManager = BackgroundManager.getInstance(this);
        mBackgroundManager.attach(this.getWindow());
        mMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
        Resources resources = mContext.getResources();
        BitmapDrawable drawable = (BitmapDrawable)resources.getDrawable(R.drawable.bk_default);
        mBackgroundManager.setDrawable(drawable);
//        mBackgroundManager.setBitmap(drawable.getBitmap());
        //加载桌面图片
//        Glide.with(mContext)
//                .load("http://img.hb.aicdn.com/cf0d0c1882bb826b7a094da493216b308b8034d7b962-yEDfJY_fw658")
//                .asBitmap()
//                .centerCrop()
//                .into(new SimpleTarget<Bitmap>(width, height) {
//                    @Override
//                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                        mBackgroundManager.setBitmap(resource);
//                    }
//                });
    }

    private void buildRowsAdapter() {
        rowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());

        addFunctionRow();
        rowsAdapter.add(new DividerRow());
        addHistoryRow();
//        addAppRow();

        mBrowseFragment.setAdapter(rowsAdapter);
        mBrowseFragment.setOnItemViewClickedListener(new OnItemViewClickedListener() {
            @Override
            public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
                if (item instanceof MediaModel) {
                    MediaModel mediaModel = (MediaModel) item;
                    Intent intent = new Intent(mContext, MediaDetailsActivity.class);
                    intent.putExtra(MediaDetailsActivity.MEDIA, mediaModel);

//                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                            (Activity) mContext,
//                            itemViewHolder.view.getRootView(),
//                            MediaDetailsActivity.SHARED_ELEMENT_NAME).toBundle();
                    startActivity(intent);
                } else if (item instanceof AppModel) {
                    AppModel appBean = (AppModel) item;
                    Intent launchIntent = mContext.getPackageManager().getLaunchIntentForPackage(
                            appBean.getPackageName());
                    if (launchIntent != null) {
                        mContext.startActivity(launchIntent);
                    }
                } else if (item instanceof FunctionModel) {
                    FunctionModel functionModel = (FunctionModel) item;
                    Intent intent = functionModel.getIntent();
                    if (intent != null) {
                        startActivity(intent);
                    }
                }else if (item instanceof HistoryModel) {
                    HistoryModel historyModel = (HistoryModel) item;
                    Intent intent = new Intent(mContext, MediaDetailsActivity.class);
                    intent.putExtra(MediaDetailsActivity.His_Media, historyModel);
                    if (intent != null) {
                        startActivity(intent);
                    }
                }
            }
        });
        /*mBrowseFragment.setOnItemViewSelectedListener(new OnItemViewSelectedListener() {
            @Override
            public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
                if (item instanceof MediaModel) {
                    MediaModel mediaModel = (MediaModel) item;
                    int width = mMetrics.widthPixels;
                    int height = mMetrics.heightPixels;
                    Glide.with(mContext)
                            .load(mediaModel.getImageUrl().get(0))
                            .asBitmap()
                            .centerCrop()
                            .into(new SimpleTarget<Bitmap>(width, height) {
                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                    mBackgroundManager.setBitmap(resource);
                                }
                            });
                } else {
                    mBackgroundManager.setBitmap(null);
                }
            }
        });*/
    }

    private void addHistoryRow() {
        String headerName = getResources().getString(R.string.app_header_photo_name);
        ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(new VedioPresenter());
        FileInputStream fileInputStream=null;
        try {
            fileInputStream = this.openFileInput("history.json");
        } catch (FileNotFoundException e) {
            DebugLog.e("getViewHistory_error_open " + e.getMessage());
        }
        if(fileInputStream==null){
            return;
        }

        for (MediaModel mediaModel : movieService.getViewHistory(fileInputStream)) {
            listRowAdapter.add(mediaModel);
        }
        HeaderItem header = new HeaderItem(0, headerName);
        rowsAdapter.add(new ListRow(header, listRowAdapter));
    }

    private void addVideoRow(List<MovieInfoModel> mainMovieList ) {
        for (MovieInfoModel movieInfoModel : mainMovieList) {
            ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(new VedioPresenter());
            for (MediaModel mediaModel : movieInfoModel.getMediaModelList()) {
                listRowAdapter.add(mediaModel);
            }
            HeaderItem header = new HeaderItem(0, movieInfoModel.getCategoryTitle());
            rowsAdapter.add(new ListRow(header, listRowAdapter));
        }
    }


    private void addAppRow() {
        String headerName = getResources().getString(R.string.app_header_app_name);
        ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(new AppCardPresenter());
        ArrayList<AppModel> appDataList = new AppDataManage(mContext).getLaunchAppList();
        int cardCount = appDataList.size();

        for (int i = 0; i < cardCount; i++) {
            listRowAdapter.add(appDataList.get(i));
        }
        HeaderItem header = new HeaderItem(0, headerName);
        rowsAdapter.add(new ListRow(header, listRowAdapter));
    }

    private void addFunctionRow() {
        String headerName = getResources().getString(R.string.app_header_function_name);
        ArrayObjectAdapter listRowAdapter = new ArrayObjectAdapter(new FunctionCardPresenter());
        List<FunctionModel> functionModels = FunctionModel.getFunctionList(mContext);
        int cardCount = functionModels.size();
        for (int i = 0; i < cardCount; i++) {
            listRowAdapter.add(functionModels.get(i));
        }
        HeaderItem header = new HeaderItem(0, headerName);
        rowsAdapter.add(new ListRow(listRowAdapter));
    }
}
