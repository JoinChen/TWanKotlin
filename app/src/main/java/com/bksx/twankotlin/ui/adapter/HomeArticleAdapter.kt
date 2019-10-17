package com.bksx.twankotlin.ui.adapter

import android.content.Context
import android.widget.TextView
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.ArticleBean
import com.bksx.twankotlin.base.recycler.BaseAdapter
import com.bksx.twankotlin.base.recycler.BaseViewHolder
import com.bksx.twankotlin.bean.QueryBean
import com.bksx.twankotlin.utils.formatDate2Day

/**
 * @Author JoneChen
 * @Date 2019\7\24 0024-10:39
 */
class HomeArticleAdapter (
    val ctx: Context,
    val layoutRes: Int,
    private val mData: MutableList<ArticleBean.Data.DataX>
):BaseAdapter<ArticleBean.Data.DataX>(ctx,layoutRes,mData){
    override fun convert(holder: BaseViewHolder, position: Int) {
        holder.getView<TextView>(R.id.tv_item_home_title).apply {
                text = mData[position].title
            }

        holder.getView<TextView>(R.id.tv_item_home_time).apply {
            text = mData[position].publishTime.formatDate2Day()
        }

        holder.getView<TextView>(R.id.tv_item_home_tag).apply {
            text = mData[position].author
        }
    }
}