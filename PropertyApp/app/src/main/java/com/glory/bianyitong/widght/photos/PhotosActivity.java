package com.glory.bianyitong.widght.photos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.glory.bianyitong.R;
import com.litao.android.lib.BaseGalleryActivity;
import com.litao.android.lib.Configuration;
import com.litao.android.lib.entity.PhotoEntry;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;



/**
 * Created by 16/4/29.
 */
public class PhotosActivity extends BaseGalleryActivity implements View.OnClickListener {

    RelativeLayout left_return_btn;
    TextView tv_title_text;
    TextView tv_title_text2;
    private TextView mTextViewOpenAlbum;
    private TextView mTextViewSelectedCount;
    private TextView mTextViewSend;
    private List<PhotoEntry> mSelectedPhotos;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        EventBus.getDefault().register(this);
        left_return_btn = (RelativeLayout) findViewById(R.id.left_return_btn);
        tv_title_text = (TextView) findViewById(R.id.tv_title_text);
        tv_title_text2 = (TextView) findViewById(R.id.tv_title_text2);
        left_return_btn.setOnClickListener(this);
        tv_title_text.setText(getString(R.string.choose_a_photo));
        position = getIntent().getIntExtra("position", -1);
        initView();
        attachFragment(R.id.gallery_root);
        //        setTitle("选择照片");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private void initView() {

        mTextViewOpenAlbum = (TextView) findViewById(R.id.album);
        mTextViewSelectedCount = (TextView) findViewById(R.id.selected_count);
        mTextViewSend = (TextView) findViewById(R.id.send_photos);

        mTextViewOpenAlbum.setOnClickListener(this);
        mTextViewSelectedCount.setOnClickListener(this);
        mTextViewSend.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.album:
                openAlbum();
                break;
            case R.id.send_photos:
                sendPhotos();
                break;
            case R.id.left_return_btn:
                PhotosActivity.this.finish();
                break;

        }
    }

    /**
     * @return
     */
    @Override
    public Configuration getConfiguration() {
        //default configuration
        Configuration cfg = new Configuration.Builder()
                .hasCamera(true)
                .hasShade(true)
                .hasPreview(true)
                .setSpaceSize(4)
                .setPhotoMaxWidth(120)
                .setCheckBoxColor(0xFFFFC526)
                .setDialogHeight(Configuration.DIALOG_HALF)
                .setDialogMode(Configuration.DIALOG_GRID)
                .setMaximum(6)
                .setTip(null)
                .setAblumsTitle(null)
                .build();
        return cfg;
    }

    @Override
    public List<PhotoEntry> getSelectPhotos() {
        return mSelectedPhotos;
    }

    @Override
    public void onSelectedCountChanged(int count) {
        mTextViewSelectedCount.setVisibility(count > 0 ? View.VISIBLE : View.INVISIBLE);
        mTextViewSelectedCount.setText(String.valueOf(count));
    }

    @Override
    public void onAlbumChanged(String name) {
//        getSupportActionBar().setSubtitle(name);
        tv_title_text2.setText(name);
    }

    @Override
    public void onTakePhoto(PhotoEntry entry) {
        EventBus.getDefault().post(entry);
        finish();
    }

    @Override
    public void onChoosePhotos(List<PhotoEntry> entries) {
        EventBus.getDefault().post(new EventEntry(entries, EventEntry.RECEIVED_PHOTOS_ID,position));
        finish();
    }

    /**
     * @param entry
     */
    @Override
    public void onPhotoClick(PhotoEntry entry) {

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void photosMessageEvent(EventEntry entry) {
        if (entry.id == EventEntry.SELECTED_PHOTOS_ID) {
            mSelectedPhotos = entry.photos;
        }
    }


}
