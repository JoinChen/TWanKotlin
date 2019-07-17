package com.bksx.twankotlin.ui

import android.support.v7.widget.LinearLayoutManager
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.BaseMVPActivity
import com.bksx.twankotlin.base.Constant
import com.bksx.twankotlin.bean.QueryBean
import com.bksx.twankotlin.contract.ISearchResultView
import com.bksx.twankotlin.presenter.SearchResultPresenter
import com.bksx.twankotlin.ui.adapter.SearchResultAdapter
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import kotlinx.android.synthetic.main.activity_search_result.*
import kotlinx.android.synthetic.main.item_common_head.*

/**
 * @Author JoneChen
 * @Date 2019\7\16 0016-14:12
 */
class SearchResultActivity : BaseMVPActivity<ISearchResultView, SearchResultPresenter>(), ISearchResultView {
    private val mResultPresenter: SearchResultPresenter by lazy { getPresenter() }
    private val mResultList: MutableList<QueryBean.Data.DataX> = mutableListOf()
    private var index: Int = 0
    private lateinit var mKey: String
    private var mSearchResultAdapter: SearchResultAdapter? = null

    override fun getLayoutId(): Int = R.layout.activity_search_result

    override fun initData() {
        mKey = intent.getStringExtra(Constant.SEARCH_KEY)
        mResultPresenter.getSearch(index, mKey)
    }

    override fun initView() {
        tv_head_title.text = mKey
        mSearchResultAdapter = SearchResultAdapter(
            this@SearchResultActivity,
            R.layout.item_search_result,
            mResultList
        )
        rv_search_result.adapter = mSearchResultAdapter
        rv_search_result.layoutManager = LinearLayoutManager(this@SearchResultActivity)
        mSearchResultAdapter?.notifyDataSetChanged()
        mSearchResultAdapter?.setOnItemClickListener { position ->

        }

        srl_search_result.setRefreshHeader(ClassicsHeader(this@SearchResultActivity))
        srl_search_result.setRefreshFooter(ClassicsFooter(this@SearchResultActivity))

    }

    override fun initEvent() {
        iv_head_back.setOnClickListener {
            this.finish()
        }
        srl_search_result.setOnRefreshListener { mResultPresenter.getSearch(index, mKey) }
        srl_search_result.setOnLoadMoreListener { mResultPresenter.getSearch(index, mKey) }
    }

    override fun getPresenter(): SearchResultPresenter = SearchResultPresenter(this)

    override fun showSearchData(queryBean: MutableList<QueryBean.Data.DataX>) {
        index++
        mSearchResultAdapter?.addListData(queryBean)
    }

    override fun finishedRefresh() {
        srl_search_result.finishRefresh()
        srl_search_result.finishLoadMore()
    }
}