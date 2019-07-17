package com.bksx.twankotlin.presenter

import android.content.Context
import android.widget.Toast
import com.bksx.twankotlin.App
import com.bksx.twankotlin.base.BasePresenter
import com.bksx.twankotlin.bean.QueryBean
import com.bksx.twankotlin.contract.ISearchResultView
import com.bksx.twankotlin.contract.ISearchRltModel
import com.bksx.twankotlin.model.SearchResultModel

/**
 * @Author JoneChen
 * @Date 2019\7\16 0016-15:32
 */
class SearchResultPresenter(private val mView: ISearchResultView) : BasePresenter<ISearchResultView>(),
    ISearchRltModel.OnGetSearchListener {

    private val model: ISearchRltModel = SearchResultModel()

    fun getSearch(index: Int, key: String) {
        if (key.isNotEmpty()) {
            model.getSearch(index, key, this)
            mView.showDialog()
        }
    }

    override fun onSearchListener(queryBean: QueryBean?) {
        mView.hideDialog()
        mView.finishedRefresh()
        queryBean?.data?.datas?.let {
            if (it.isNotEmpty()){
                mView.showSearchData(it)
            }else{
                App.mInstance.toast("no more!")
            }
        }
    }

    private fun Context.toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}