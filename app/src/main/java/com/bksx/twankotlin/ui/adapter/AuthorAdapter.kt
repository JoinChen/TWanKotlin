package com.bksx.twankotlin.ui.adapter

import android.content.Context
import android.widget.TextView
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.recycler.BaseAdapter
import com.bksx.twankotlin.base.recycler.BaseViewHolder
import com.bksx.twankotlin.bean.AuthorBean
import com.bksx.twankotlin.contract.IAuthorView
import com.bksx.twankotlin.presenter.AuthorPresenter

/**
 * @Author JoneChen
 * @Date 2019\7\26 0026-17:02
 */
class AuthorAdapter(
    ctx:Context,
    layouRes:Int,
    private val mData:MutableList<AuthorBean.Data> = mutableListOf()
):BaseAdapter<AuthorBean.Data>(ctx,layouRes,mData){

    override fun convert(holder: BaseViewHolder, position: Int) {
        val data = mData.get(position)
        holder.getView<TextView>(R.id.tv_author_name).apply {
            text = data.name
        }
    }
}