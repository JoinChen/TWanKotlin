package com.bksx.twankotlin.base.recycler

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * @Author JoneChen
 * @Date 2019\7\15 0015-10:53
 */
class BaseViewHolder(private val item: View) : RecyclerView.ViewHolder(item) {

    private val mViews = SparseArrayCompat<View>()

    fun <V : View> getView(id: Int): V {
        var view = mViews[id]
        if (view == null){
            view = item.findViewById(id)
            mViews.put(id,view)
        }
        return view as V
    }
}