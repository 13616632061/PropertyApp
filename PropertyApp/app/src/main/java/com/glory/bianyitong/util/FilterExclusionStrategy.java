package com.glory.bianyitong.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Created by billlamian on 17-8-10.
 */

public class FilterExclusionStrategy implements ExclusionStrategy {
    private String filterField;
    public FilterExclusionStrategy(String filterField) {
        this.filterField = filterField;
    }

    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        if(filterField.equals(f.getName())){
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
