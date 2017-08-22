package com.glory.bianyitong.widght.photos;

import com.litao.android.lib.entity.PhotoEntry;

import java.util.List;

/**
 * Created by 16/4/30.
 */
public class EventEntry {

    public static final int  RECEIVED_PHOTOS_ID = 0x00000010;

    public static final int  SELECTED_PHOTOS_ID = 0x00000020;


    public List<PhotoEntry> photos;
    public int id;
    public int ratingBar;
    public String comment;
    public int position;

    public EventEntry(List<PhotoEntry> photos, int id){
        this.photos = photos;
        this.id = id;
    }

    public EventEntry(List<PhotoEntry> photos, int id, int position) {
        this.photos = photos;
        this.id = id;
        this.position = position;
    }
}
