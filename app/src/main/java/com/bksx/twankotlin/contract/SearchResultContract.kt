package com.bksx.twankotlin.contract

import com.bksx.twankotlin.base.BaseBean
import com.bksx.twankotlin.base.IBaseView
import com.bksx.twankotlin.bean.QueryBean

/**
 * @Author JoneChen
 * @Date 2019\7\16 0016-14:23
 */
interface ISearchResultView : IBaseView {
    fun showSearchData(queryBean: MutableList<QueryBean.Data.DataX>)

    fun finishedRefresh()
}

interface ISearchRltModel {

    fun getSearch(index: Int = 0, key: String, listener: OnGetSearchListener)

    interface OnGetSearchListener {
        fun onSearchListener(queryBean: QueryBean?)
    }

}
