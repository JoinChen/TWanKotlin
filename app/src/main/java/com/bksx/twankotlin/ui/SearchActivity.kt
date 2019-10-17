package com.bksx.twankotlin.ui

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.BaseMVPActivity
import com.bksx.twankotlin.base.Constant
import com.bksx.twankotlin.bean.HotKeyBean
import com.bksx.twankotlin.bean.db.HotKeyDB
import com.bksx.twankotlin.contract.IHotKeyView
import com.bksx.twankotlin.presenter.HotKeyPresenter
import com.bksx.twankotlin.ui.adapter.HotKeyDBAdapter
import com.bksx.twankotlin.utils.start
import com.blankj.utilcode.utils.ToastUtils
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.activity_go_search.*

class SearchActivity : BaseMVPActivity<IHotKeyView, HotKeyPresenter>(), IHotKeyView {

    private val mKeyPresenter: HotKeyPresenter by lazy { getPresenter() }
    private val mTabList: MutableList<String> = mutableListOf()
    private var mDBAdapter: HotKeyDBAdapter?= null
    //实现来自BaseMVPActivity
    override fun getPresenter(): HotKeyPresenter = HotKeyPresenter(this)

    override fun getLayoutId(): Int {
        return R.layout.activity_go_search
    }

    override fun initData() {
        mKeyPresenter.getHotKey()
        mKeyPresenter.findAllKey()
    }

    override fun initView() {
    }

    override fun initEvent() {
        iv_go_search_back.setOnClickListener { this.finish() }
        et_go_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == 0) {
                val content: String = et_go_search.text.toString().trim()
                mKeyPresenter.saveKey2DB(content)
                start(SearchResultActivity::class.java, mapOf(Pair(Constant.SEARCH_KEY,content)))
                val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(et_go_search.windowToken, 0)
            }
            return@setOnEditorActionListener false
        }
    }

    //实现来自IHotKeyView
    override fun showHotKey(hotKeyBean: HotKeyBean) {
        hotKeyBean.data?.forEach {
            mTabList.add(it.name)
        }

        settingTagFlowLayout()
    }

    //设置流式布局标签数据
    private fun settingTagFlowLayout() {
        tab_go_search.adapter = object : TagAdapter<String>(mTabList) {
            override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
                val tv = TextView(this@SearchActivity)
                tv.apply {
                    text = t
                    textSize = 14f
                    setBackgroundResource(R.drawable.tab_bg)
                }
                return tv
            }
        }

        tab_go_search.setOnTagClickListener { _, position, _ ->
            val key: String = mTabList[position]
            mKeyPresenter.saveKey2DB(key)
            start(SearchResultActivity::class.java, mapOf(Pair(Constant.SEARCH_KEY,key)))
            return@setOnTagClickListener true
        }
    }

    //使用litepal保存查询的数据到名称为"Twanandroid"的数据库中
    override fun showHotKeyDB(hotKeyMultiList: MutableList<HotKeyDB>) {
        mDBAdapter = HotKeyDBAdapter(this@SearchActivity,
            R.layout.item_hot_key_db,
            hotKeyMultiList)
        rv_go_search.layoutManager = LinearLayoutManager(this@SearchActivity)
        rv_go_search.adapter = mDBAdapter
        mDBAdapter?.notifyDataSetChanged()
        mDBAdapter?.setOnItemClickListener { position ->
            val key: String = hotKeyMultiList[position].key
            mKeyPresenter.saveKey2DB(key)
            start(SearchResultActivity::class.java, mapOf(Pair(Constant.SEARCH_KEY,key)))
        }
    }
}
