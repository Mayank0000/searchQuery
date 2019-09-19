package com.example.search.model;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;

public class UserModelClass {

    private String name;
    private String year;
    private String imageUrl;
    private String imDbId;


    public UserModelClass(String name,String year,String imageUrl,String imDbId) {
        this.name = name;
        this.imDbId = imDbId;
        this.imageUrl = imageUrl;
        this.year = year;
    }

    public static DiffUtil.ItemCallback<UserModelClass> DIFF_CALLBACK = new DiffUtil.ItemCallback<UserModelClass>() {
        @Override
        public boolean areItemsTheSame(@NonNull UserModelClass oldItem, @NonNull UserModelClass newItem) {
            return oldItem.name.equals(newItem.name);
        }

        @Override
        public boolean areContentsTheSame(@NonNull UserModelClass oldItem, @NonNull UserModelClass newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        UserModelClass article = (UserModelClass) obj;
        return article.name.equals(this.name);
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public String getName() {
        return name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @BindingAdapter("profileImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    public String getImDbId() {
        return imDbId;
    }

    public void setImDbId(String imDbId) {
        this.imDbId = imDbId;
    }
}
