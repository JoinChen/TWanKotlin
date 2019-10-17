package com.bksx.twankotlin.ui.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.recycler.BaseAdapter
import com.bksx.twankotlin.base.recycler.BaseViewHolder
import com.bksx.twankotlin.bean.CollectBean
import com.bksx.twankotlin.bean.db.CollectOffDB
import com.bksx.twankotlin.presenter.CollectionPresenter
import com.bksx.twankotlin.utils.formatDate2Day

/**
 * @Author JoneChen
 * @Date 2019\7\31 0031-15:56
 */
class CollectUserAllAdapter(
    context: Context,
    layoutRes: Int,
    private val mData: MutableList<CollectBean.Data.DataX> = mutableListOf()
) : BaseAdapter<CollectBean.Data.DataX>(context, layoutRes, mData) {
    override fun convert(holder: BaseViewHolder, position: Int) {
        holder.getView<TextView>(R.id.tv_item_collect_title).apply {
            text = mData[position].title
        }

        holder.getView<TextView>(R.id.tv_item_collect_time).text = mData[position].publishTime.formatDate2Day()

        holder.getView<TextView>(R.id.tv_item_collect_author).text = mData[position].author

        holder.getView<ImageView>(R.id.iv_item_collect).setOnClickListener {
            listener?.invoke(position)
        }

    }

    private var listener: ((position: Int) -> Unit)? = null
    fun setCancleCollect(listener: ((position: Int) -> Unit)?) {
        this.listener = listener
    }
}