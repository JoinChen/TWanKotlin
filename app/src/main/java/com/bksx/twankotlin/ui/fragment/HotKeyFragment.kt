package com.taonce.wankotlin.ui.fragment

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.bksx.twankotlin.App
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.BaseFragment
import com.bksx.twankotlin.base.Constant
import com.bksx.twankotlin.bean.HotKeyBean
import com.bksx.twankotlin.bean.db.HotKeyDB
import com.bksx.twankotlin.contract.IHotKeyView
import com.bksx.twankotlin.presenter.HotKeyPresenter
import com.bksx.twankotlin.ui.SearchResultActivity
import com.bksx.twankotlin.ui.adapter.FHotKeyAdapter
import kotlinx.android.synthetic.main.fragment_hotkey.*
import org.litepal.util.Const

/**
 * Author: taoyongxiang
 * Date: 2019/4/9
 * Project: WanKotlin
 * Desc:
 */

class HotKeyFragment : BaseFragment(), IHotKeyView {
    private var mPresenter: HotKeyPresenter? = null
    private var hotKeyList: MutableList<String> = mutableListOf()
    private var mAdapter: FHotKeyAdapter? = null

    override fun getLayouId(): Int = R.layout.fragment_hotkey


    override fun initData() {

    }

    override fun initView() {
        mPresenter = HotKeyPresenter(this)
        mAdapter = FHotKeyAdapter(this.context!!,R.layout.item_fhotkey,hotKeyList)
        rl_fhotkey.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this.context!!)
        }
        mAdapter?.notifyDataSetChanged()
        mAdapter?.setOnItemClickListener { position ->
            val value = hotKeyList[position]
            val intent = Intent(this.context!!,SearchResultActivity::class.java)
            intent.putExtra(Constant.SEARCH_KEY,value)
            this.context!!.startActivity(intent)
        }
    }

    override fun initEvent() {
        mPresenter?.getHotKey()
    }

    override fun showHotKey(hotKeyBean: HotKeyBean) {
       hotKeyBean.data?.forEach {
           hotKeyList.add(it.name)
       }
    }

    override fun showHotKeyDB(hotKeyMultiList: MutableList<HotKeyDB>) {
    }
}
