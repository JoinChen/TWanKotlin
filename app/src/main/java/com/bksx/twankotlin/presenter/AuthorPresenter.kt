package com.bksx.twankotlin.presenter

import com.bksx.twankotlin.base.BasePresenter
import com.bksx.twankotlin.bean.AuthorBean
import com.bksx.twankotlin.contract.IAuthorModel
import com.bksx.twankotlin.contract.IAuthorView
import com.bksx.twankotlin.model.AuthorModel

/**
 * @Author JoneChen
 * @Date 2019\7\26 0026-16:47
 */
class AuthorPresenter(private val mView: IAuthorView):BasePresenter<IAuthorView>(),
IAuthorModel.OnGetAuthorListener{
    private val model = AuthorModel()

    fun getAuthor() = kotlin.run{
        mView.showDialog()
        model.getAuthor(this)
    }

    override fun onGetAuthor(authorBean: AuthorBean?) {
        mView.hideDialog()
        authorBean?.data?.let {
            mView.showAuthor(it)
        }
    }
}