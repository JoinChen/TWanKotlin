package com.bksx.twankotlin.ui.adapter

import android.content.Context
import com.bksx.twankotlin.base.recycler.BaseAdapter
import com.bksx.twankotlin.base.recycler.BaseViewHolder
import com.bksx.twankotlin.bean.db.CollectOffDB

/**
 * @Author JoneChen
 * @Date 2019\7\31 0031-10:04
 */
class CollectAllAdapter(
    context: Context,
    layoutRes: Int,
    mData: MutableList<CollectOffDB> = mutableListOf()
): BaseAdapter<CollectOffDB>(context,layoutRes,mData) {
    override fun convert(holder: BaseViewHolder, position: Int) {

    }
}