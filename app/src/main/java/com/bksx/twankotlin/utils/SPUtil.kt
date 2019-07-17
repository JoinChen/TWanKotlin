package com.bksx.twankotlin.utils

import android.content.Context
import android.content.SharedPreferences
import com.blankj.utilcode.utils.ToastUtils

const val fileName = "share_preference"

/**
 * 向SP中写入数据
 * 键值对
 */
fun Context.putSP(key:String,value:Any){
    val sp:SharedPreferences by lazy { this.getSharedPreferences(
        fileName,Context.MODE_PRIVATE
    ) }
    when(value){
        is String ->sp.edit().putString(key,value).apply()
        is Int ->sp.edit().putInt(key, value).apply()
        is Long ->sp.edit().putLong(key, value) .apply()
        is Boolean ->sp.edit().putBoolean(key, value).apply()
        is Float ->sp.edit().putFloat(key, value).apply()
        else ->ToastUtils.showShortToast(this,"类型错误")
    }
}

/**
 *向SP中取出数据
 *
 */
fun Context.getSP(key:String,value: Any):Any?{
    val sp:SharedPreferences by lazy { this.getSharedPreferences(fileName,Context.MODE_PRIVATE) }
    when(value){
        is String -> return sp.getString(key,value)
        is Int -> return sp.getInt(key,value)
        is Long -> return sp.getLong(key,value)
        is Boolean -> return sp.getBoolean(key,value)
        is Float ->return sp.getFloat(key,value)
        else ->return ToastUtils.showShortToast(this,"类型错误")
    }
    return null
}

/**
 * 清除[SharedPreferences]的所有数据
 */
fun Context.clear() {
    val sp: SharedPreferences = this.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    sp.edit().clear().apply()
}

/**
 * 检测[SharedPreferences]是否包含某个键值对
 */
fun Context.contains(key: String): Boolean {
    val sp: SharedPreferences = this.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    return sp.contains(key)
}