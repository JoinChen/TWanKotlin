package com.bksx.twankotlin.net

import com.bksx.twankotlin.App
import com.bksx.twankotlin.base.Constant
import com.bksx.twankotlin.utils.putSP
import com.blankj.utilcode.utils.SPUtils
import java.lang.StringBuilder

object CookieUtils {
    fun encodeCookie(cookies: MutableList<String>): String {
        val sb = StringBuilder()
        val set = HashSet<String>()
        cookies.map { cookie -> cookie.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray() }
            .forEach {
                it.filterNot { item ->
                    set.contains(item)
                }.forEach { item ->
                    set.add(item)
                }
            }

        val iterator: MutableIterator<String> = set.iterator()
        while (iterator.hasNext()) {
            val cookie: String = iterator.next()
            sb.append(cookie).append(";")
        }
        val last: Int = sb.lastIndexOf(";")
        if (sb.length - 1 == last) {
            sb.deleteCharAt(last)
        }
        return sb.toString()
    }

    //add cookie
    fun savaCookie(cookies:String){
        App.mInstance.run {
            putSP(Constant.SP_COOKIE,cookies)
        }
    }
}