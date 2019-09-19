package com.example.search.ui.movielist;

import com.example.search.model.UserModelClass;

public interface OnClickRowEvent {
    void onBookMarkClick(UserModelClass userModelClass,int type);
    void onItemClick(String id);
}
