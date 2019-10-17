package com.taonce.wankotlin.ui.fragment

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.BaseFragment
import com.bksx.twankotlin.bean.AuthorBean
import com.bksx.twankotlin.contract.IAuthorView
import com.bksx.twankotlin.presenter.AuthorPresenter
import com.bksx.twankotlin.ui.WxChapterActivity
import com.bksx.twankotlin.ui.adapter.AuthorAdapter
import kotlinx.android.synthetic.main.fragment_author.*
import java.nio.channels.MulticastChannel

/**
 * Author: taoyongxiang
 * Date: 2019/4/9
 * Project: WanKotlin
 * Desc:
 */

class WxChapterFragment : BaseFragment(),IAuthorView{
    private val mPresenter: AuthorPresenter by lazy { AuthorPresenter(this) }
    private var mAdapter: AuthorAdapter? = null
    private var mAuthorList: MutableList<AuthorBean.Data> = mutableListOf()

    override fun getLayouId(): Int {
        return R.layout.fragment_author
    }

    override fun initData() {
        mPresenter.getAuthor()
    }

    override fun initView() {
        mAdapter = AuthorAdapter(this.context!!,R.layout.item_fauthor,mAuthorList)
        rl_author.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this.context!!)
        }
        mAdapter?.notifyDataSetChanged()
        mAdapter?.setOnItemClickListener { position ->
            val data = mAuthorList[position]
            val id: Int = data.id
            val intent = Intent(this.context!!, WxChapterActivity::class.java)
            intent.putExtra("id",id)
            intent.putExtra("title",data.name)
            startActivity(intent)
        }
    }

    override fun initEvent() {

    }

    override fun showAuthor(authorList: MutableList<AuthorBean.Data>) {
        mAdapter?.addListData(authorList)
    }
}
