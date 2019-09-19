package com.example.search.ui.movielist;

import com.example.search.model.UserModelClass;

public interface OnClickHorizontalRow {
    void onClick(String imDbID);
    void onBookMarkClick(UserModelClass userModelClass);
}
