package com.bksx.twankotlin.ui.adapter

import android.content.Context
import android.widget.TextView
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.recycler.BaseAdapter
import com.bksx.twankotlin.base.recycler.BaseViewHolder
import com.bksx.twankotlin.bean.WxHistoryBean
import com.bksx.twankotlin.utils.formatDate2Day

/**
 * @Author JoneChen
 * @Date 2019\7\30 0030-9:46
 */
class WxChapterAdapter(
    context: Context,
    layoutRes: Int,
    private val mData: MutableList<WxHistoryBean.Data.DataX> = mutableListOf()
): BaseAdapter<WxHistoryBean.Data.DataX>(context,layoutRes,mData) {

    override fun convert(holder: BaseViewHolder, position: Int) {
        holder.getView<TextView>(R.id.tv_wx_chapter_title).apply {
            text = mData[position].title
        }

        holder.getView<TextView>(R.id.tv_wx_chapter_time).apply {
            text = mData[position].publishTime.formatDate2Day()
        }

        holder.getView<TextView>(R.id.tv_wx_chapter_name).apply {
            text = mData[position].author
        }
    }
}