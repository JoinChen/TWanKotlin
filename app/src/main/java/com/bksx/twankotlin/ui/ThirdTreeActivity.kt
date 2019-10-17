package com.bksx.twankotlin.ui

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.BaseMVPActivity
import com.bksx.twankotlin.base.Constant
import com.bksx.twankotlin.bean.ThirdBean
import com.bksx.twankotlin.contract.ISecondTreeView
import com.bksx.twankotlin.contract.IThirdTreeView
import com.bksx.twankotlin.presenter.SearchResultPresenter
import com.bksx.twankotlin.presenter.ThirdTreePresenter
import com.bksx.twankotlin.ui.adapter.SecondTreeAdapter
import com.bksx.twankotlin.ui.adapter.ThirdTreeAdapter
import com.bksx.twankotlin.utils.formatDate2Day
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import kotlinx.android.synthetic.main.activity_search_result.*
import kotlinx.android.synthetic.main.item_common_head.*

class ThirdTreeActivity : BaseMVPActivity<ISecondTreeView, ThirdTreePresenter>(), IThirdTreeView {
    private val mPresenter: ThirdTreePresenter by lazy { getPresenter() }
    private var index: Int = 0
    private var cid: Int = 0
    private var title: String = ""
    private var mThirdList: MutableList<ThirdBean.Data.DataX> = mutableListOf()
    private var mAdapter: ThirdTreeAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_search_result
    }

    override fun initData() {
        title = intent.getStringExtra(Constant.THIRD_TITLE)
        cid = intent.getIntExtra(Constant.Third_KEY, 0)
        mPresenter.getSecondTree(index, cid)
    }

    override fun initView() {

        tv_head_title.text = title
        mAdapter = ThirdTreeAdapter(this,R.layout.item_third_tree,mThirdList)
        rv_search_result.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@ThirdTreeActivity)
        }
        mAdapter?.notifyDataSetChanged()
        mAdapter?.setOnItemClickListener { position ->
           val dataX = mThirdList[position]
            toCommonX5Activity(
                context = this,
                url = dataX.link,
                isCollected = dataX.collect,
                articleId = dataX.id,
                isShow = true,
                collectTime = "",
                publishTime = dataX.publishTime.formatDate2Day()
            )
        }

        srl_search_result.setRefreshHeader(ClassicsHeader(this@ThirdTreeActivity))
        srl_search_result.setRefreshFooter(ClassicsFooter(this@ThirdTreeActivity))
//        srl_search_result.autoRefresh()
    }

    override fun initEvent() {
        iv_head_back.setOnClickListener { finish() }

        srl_search_result.setOnRefreshListener { mPresenter.getSecondTree(index, cid) }
        srl_search_result.setOnLoadMoreListener { mPresenter.getSecondTree(index, cid) }
    }

    override fun getPresenter(): ThirdTreePresenter = ThirdTreePresenter(this@ThirdTreeActivity)

    override fun showThirdTree(thirdList: MutableList<ThirdBean.Data.DataX>) {
        index++
//        thirdList.let {
//            mThirdList.addAll(thirdList)
//        }
        mAdapter?.addListData(thirdList)
    }

    override fun finishedRefresh() {
        srl_search_result.finishRefresh()
        srl_search_result.finishLoadMore()
    }

    private fun toCommonX5Activity(
        context: Context,
        url: String,
        isCollected: Boolean = false,
        articleId: Int = -1,
        isShow: Boolean = true,
        collectTime: String = "",
        publishTime: String = "",
        author: String = "",
        title: String = ""
    ) {
        val intent = Intent(context, LinkedActivity::class.java)
        intent.putExtra(Constant.SEARCH_LINK_KEY, url)
        if (isShow) {
            intent.putExtra(Constant.X5_IS_COLLECTED, isCollected)
            intent.putExtra(Constant.X5_ARTICLE_ID, articleId)
            intent.putExtra(Constant.X5_COLLECTED_TIME, collectTime)
            intent.putExtra(Constant.X5_PUBLISH_TIME, publishTime)
            intent.putExtra(Constant.X5_AUTHOR, author)
            intent.putExtra(Constant.X5_ARTICLE_TITLE, title)
        }
        intent.putExtra(Constant.X5_IS_SHOW, isShow)
        context.startActivity(intent)
    }
}
