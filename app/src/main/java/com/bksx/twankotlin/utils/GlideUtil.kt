package com.bksx.twankotlin.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * @Author JoneChen
 * @Date 2019\7\19 0019-11:07
 */
fun Context.loadImage(
    resId: Int,target: ImageView
){
    Glide.with(this)
        .load(resId)
        .into(target)
}

fun Context.loadImageWithUrl(
    url:String,target: ImageView
){
    Glide.with(this)
        .load(url)
        .into(target)
}