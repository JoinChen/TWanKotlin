package com.bksx.twankotlin.ui.adapter

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.recycler.BaseAdapter
import com.bksx.twankotlin.base.recycler.BaseViewHolder
import com.bksx.twankotlin.bean.QueryBean
import com.bksx.twankotlin.utils.formatDate2Day
import com.blankj.utilcode.utils.TimeUtils

/**
 * @Author JoneChen
 * @Date 2019\7\16 0016-16:34
 */
class SearchResultAdapter (
    val ctx: Context,
    val layoutRes: Int,
    private val mData: MutableList<QueryBean.Data.DataX>

): BaseAdapter<QueryBean.Data.DataX>(ctx,layoutRes,mData) {
    override fun convert(holder: BaseViewHolder, position: Int) {
        val itemDataX: QueryBean.Data.DataX = mData[position]
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N){
            holder.getView<TextView>(R.id.tv_item_home_title).apply {
                text = Html.fromHtml(itemDataX.title,Html.FROM_HTML_MODE_LEGACY)
            }
        }else{
            holder.getView<TextView>(R.id.tv_item_home_title).apply {
                text = Html.fromHtml(itemDataX.title)
            }
        }

        holder.getView<TextView>(R.id.tv_item_home_time).apply {
            text = itemDataX.niceDate
        }

        holder.getView<TextView>(R.id.tv_item_home_tag).apply {
            val tag: String = itemDataX.superChapterName + " / " + itemDataX.chapterName
            text = tag
        }

        holder.getView<ImageView>(R.id.iv_item_home_new).apply {
            visibility = if (System.currentTimeMillis().formatDate2Day() == itemDataX.publishTime.formatDate2Day()){
                View.VISIBLE
            }else{
                View.INVISIBLE
            }
        }
    }
}