package com.bksx.twankotlin.ui

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.bksx.twankotlin.App
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.BaseMVPActivity
import com.bksx.twankotlin.base.Constant
import com.bksx.twankotlin.bean.TreeBean
import com.bksx.twankotlin.contract.ISecondTreeView
import com.bksx.twankotlin.presenter.SecondTreePresenter
import com.bksx.twankotlin.ui.adapter.SecondTreeAdapter
import com.bksx.twankotlin.utils.start
import com.bksx.twankotlin.utils.toast
import kotlinx.android.synthetic.main.activity_search_result.*
import kotlinx.android.synthetic.main.activity_second_tree.*
import kotlinx.android.synthetic.main.item_common_head.*

/**
 * @Author JoneChen
 * @Date 2019\7\26 0026-10:52
 */
class SecondTreeActivity : BaseMVPActivity<ISecondTreeView, SecondTreePresenter>(), ISecondTreeView {
    private val mPresenter = getPresenter()
    private var mSecondList: MutableList<TreeBean.Data>? = null
    private var mSecondListChildren: MutableList<TreeBean.Data.Children> = mutableListOf()
    private var mAdapter: SecondTreeAdapter? = null
    private var position: Int? = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_second_tree
    }

    override fun initData() {
        position = intent.getIntExtra(Constant.SECOND_CLICK_POSITION,0)
    }

    override fun initView() {
        mPresenter.getSecondTree()
        mAdapter = SecondTreeAdapter(this@SecondTreeActivity,R.layout.item_second_tree,mSecondListChildren)
        rl_second_tree.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@SecondTreeActivity)
        }

        mAdapter?.notifyDataSetChanged()
        mAdapter?.setOnItemClickListener {position ->
            val id = mSecondListChildren[position].id
            val titile = mSecondListChildren[position].name
            val intent = Intent(this@SecondTreeActivity,ThirdTreeActivity::class.java)
            intent.putExtra(Constant.Third_KEY,id)
            intent.putExtra(Constant.THIRD_TITLE,titile)
            startActivity(intent)
        }
    }

    override fun initEvent() {
        iv_head_back.setOnClickListener { finish() }
    }

    override fun getPresenter(): SecondTreePresenter {
        return SecondTreePresenter(this@SecondTreeActivity)
    }

    override fun showSecondTree(mList: MutableList<TreeBean.Data>) {
        if (mList.isNotEmpty()){
            val children = mList[position!!].children
            children.let {
                mSecondListChildren.addAll(children)
            }
            mAdapter?.notifyDataSetChanged()

            val title: String? = mList[position!!].name
            tv_head_title.text = title

        }else{
            App.mInstance.toast("no more!")
        }
    }


}