package com.bksx.twankotlin.ui

import android.support.v7.widget.LinearLayoutManager
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.BaseMVPActivity
import com.bksx.twankotlin.base.Constant
import com.bksx.twankotlin.bean.CollectBean
import com.bksx.twankotlin.bean.db.CollectOffDB
import com.bksx.twankotlin.contract.ICollectModel
import com.bksx.twankotlin.contract.ICollectView
import com.bksx.twankotlin.presenter.CollectionPresenter
import com.bksx.twankotlin.ui.adapter.CollectAllAdapter
import com.bksx.twankotlin.ui.adapter.CollectUserAllAdapter
import com.bksx.twankotlin.utils.getSP
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import kotlinx.android.synthetic.main.activity_collect.*
import kotlinx.android.synthetic.main.item_common_head.*

/**
 * @Author JoneChen
 * @Date 2019\7\31 0031-9:26
 */
class CollectActivity : BaseMVPActivity<ICollectView, CollectionPresenter>(), ICollectView,
    ICollectModel.OnGetAllCollectListener {
    private val mPresenter: CollectionPresenter by lazy { getPresenter() }
    //离线数据
    private var mMutableList: MutableList<CollectOffDB> = mutableListOf()
    //登录用户收藏数据
    private var mUserMutableList: MutableList<CollectBean.Data.DataX> = mutableListOf()
    private var userName: String = ""
    private var mAdapter: CollectAllAdapter? = null
    private var mUserAdapter: CollectUserAllAdapter? = null
    private var index: Int = 0
    private var deletePosition: Int = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_collect
    }

    override fun initData() {
        userName = getSP(Constant.SP_USERNAME, "") as String
        if (userName.isNotEmpty()) {
            tv_head_title.text = "在线收藏"
        } else {
            tv_head_title.text = "离线收藏"
        }

        srl_collect.setRefreshHeader(ClassicsHeader(this))
        srl_collect.setRefreshFooter(ClassicsFooter(this))
    }

    override fun initView() {
        mPresenter.getUserAllCollect(index)
        mUserAdapter = CollectUserAllAdapter(this, R.layout.item_collect, mUserMutableList)
        rl_collect.apply {
            adapter = mUserAdapter
            layoutManager = LinearLayoutManager(this@CollectActivity)
        }
        mUserAdapter?.notifyDataSetChanged()
        mUserAdapter?.setOnItemClickListener { position ->
            val collectOffBean = mUserMutableList[position]

        }

        mUserAdapter?.setCancleCollect { position ->
            deletePosition = position
            mPresenter.getUnCollect(mUserMutableList[position].originId)
        }
    }

    override fun initEvent() {
        iv_head_back.setOnClickListener { finish() }

        srl_collect.setOnRefreshListener { mPresenter.getUserAllCollect(index) }
        srl_collect.setOnLoadMoreListener { mPresenter.getUserAllCollect(index) }
    }

    override fun getPresenter(): CollectionPresenter {
        return CollectionPresenter(this)
    }

    override fun showCollectUserAll(collectList: MutableList<CollectBean.Data.DataX>) {
        index++
        mUserAdapter?.addListData(collectList)
    }

    override fun onGetAllCollec(mCollectList: MutableList<CollectOffDB>) {
    }

    override fun showCollectAll() {

    }

    override fun showCollect() {
    }

    override fun showUnColelct() {
        mUserMutableList.removeAt(deletePosition)
        mUserAdapter?.notifyDataSetChanged()
    }

    override fun showCollectOffLine() {
    }

    override fun finishRefresh() {
        srl_collect.finishRefresh()
        srl_collect.finishLoadMore()
    }

}