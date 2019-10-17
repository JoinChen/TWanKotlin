package com.bksx.twankotlin.ui

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.BaseActivity
import com.bksx.twankotlin.base.BaseMVPActivity
import com.bksx.twankotlin.base.Constant
import com.bksx.twankotlin.bean.WxHistoryBean
import com.bksx.twankotlin.contract.IWxHistoryView
import com.bksx.twankotlin.presenter.WxHistoryPresenter
import com.bksx.twankotlin.ui.adapter.WxChapterAdapter
import com.bksx.twankotlin.utils.formatDate2Day
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import kotlinx.android.synthetic.main.activity_wx_chapter.*
import kotlinx.android.synthetic.main.fragment_author.*
import kotlinx.android.synthetic.main.item_common_head.*

class WxChapterActivity : BaseMVPActivity<IWxHistoryView, WxHistoryPresenter>(), IWxHistoryView {
    private val mPresenter by lazy { getPresenter() }
    private var cid: Int = 0
    private var index: Int = 0
    private lateinit var title: String
    private var mAdapter: WxChapterAdapter? = null
    private var mData: MutableList<WxHistoryBean.Data.DataX> = mutableListOf()

    override fun getLayoutId(): Int {
        return R.layout.activity_wx_chapter
    }

    override fun initData() {
        cid = intent.getIntExtra("id",0)
        title = intent.getStringExtra("title")
        mPresenter.getWxHistory(cid,index)
    }

    override fun initView() {
        mAdapter = WxChapterAdapter(this@WxChapterActivity,R.layout.item_wx_chapter,mData)
        rl_wx_chapter.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@WxChapterActivity)
        }
        mAdapter?.notifyDataSetChanged()
        mAdapter?.setOnItemClickListener {position ->
            val dataX = mData[position]
            toCommonX5Activity(
                context = this@WxChapterActivity,
                url = dataX.link,
                isCollected = dataX.collect,
                articleId = dataX.chapterId,
                isShow = true,
                collectTime = "",
                publishTime = dataX.publishTime.formatDate2Day(),
                author = dataX.author,
                title = dataX.title
            )
        }

        srl_wx_chapter.setRefreshHeader(ClassicsHeader(this@WxChapterActivity))
        srl_wx_chapter.setRefreshFooter(ClassicsFooter(this@WxChapterActivity))
    }

    override fun initEvent() {
        iv_head_back.setOnClickListener { finish() }
        tv_head_title.text = title

        srl_wx_chapter.setOnRefreshListener { mPresenter.getWxHistory(cid,index) }
        srl_wx_chapter.setOnLoadMoreListener { mPresenter.getWxHistory(cid,index) }
    }

    override fun getPresenter(): WxHistoryPresenter {
        return WxHistoryPresenter(this)
    }

    override fun showWxHistory(wxHistoryList: MutableList<WxHistoryBean.Data.DataX>) {
        index++
        mAdapter?.addListData(wxHistoryList)
    }

    override fun finishedRefresh() {
        srl_wx_chapter.finishRefresh()
        srl_wx_chapter.finishLoadMore()
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
