package com.bksx.twankotlin.contract

import com.bksx.twankotlin.base.IBaseView
import com.bksx.twankotlin.bean.AuthorBean

/**
 * @Author JoneChen
 * @Date 2019\7\26 0026-16:33
 */
interface IAuthorView: IBaseView{
    fun showAuthor(authorList:MutableList<AuthorBean.Data>)

}

interface IAuthorModel{
    fun getAuthor(listener: OnGetAuthorListener)

    interface OnGetAuthorListener{
        fun onGetAuthor(authorBean: AuthorBean?)
    }
}

