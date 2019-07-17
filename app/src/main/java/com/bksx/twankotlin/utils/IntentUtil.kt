package com.bksx.twankotlin.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import java.io.Serializable

/**
 * @Author JoneChen
 * @Date 2019\7\10 0010-10:12
 */
fun <T : Activity> Activity.start(
    clazz: Class<T>,
    map: Map<String, Any>? = null,
    requestCode: Int? = 0,
    isFinished: Boolean = false
){
    val intent = Intent(this,clazz)
    if (map != null){
        if (map.isNotEmpty()){
            map.forEach {
                val key : String = it.key
                when(val value : Any = it.value){
                    is String -> intent.putExtra(key, value)
                    is Int -> intent.putExtra(key, value)
                    is Float -> intent.putExtra(key, value)
                    is Double -> intent.putExtra(key, value)
                    is Boolean -> intent.putExtra(key, value)
                    is Short -> intent.putExtra(key, value)
                    is ShortArray -> intent.putExtra(key, value)
                    is Long -> intent.putExtra(key, value)
                    is LongArray -> intent.putExtra(key, value)
                    is IntArray -> intent.putExtra(key, value)
                    is FloatArray -> intent.putExtra(key, value)
                    is DoubleArray -> intent.putExtra(key, value)
                    is Char -> intent.putExtra(key, value)
                    is CharArray -> intent.putExtra(key, value)
                    is Byte -> intent.putExtra(key, value)
                    is ByteArray -> intent.putExtra(key, value)
                    is BooleanArray -> intent.putExtra(key, value)
                    is Bundle -> intent.putExtra(key, value)
                    is Parcelable -> intent.putExtra(key, value)
                    is Serializable -> intent.putExtra(key, value)
                }
            }
        }
    }
    requestCode?.let { this.startActivityForResult(intent,it) }
    /*如果 ?: 左侧表达式非空，elvis操作符就返回其左侧表达式，
    否则返回右侧表达式。请注意，当且仅当左侧为空时，才会对右侧表达式求值。*/
    requestCode ?: this.startActivity(intent)
    if (isFinished) this.finish()
}