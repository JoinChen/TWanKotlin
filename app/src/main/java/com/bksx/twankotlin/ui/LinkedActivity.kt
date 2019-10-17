package com.bksx.twankotlin.ui

import android.content.Intent
import android.graphics.Bitmap
import android.view.View
import com.bksx.twankotlin.App
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.BaseMVPActivity
import com.bksx.twankotlin.base.Constant
import com.bksx.twankotlin.bean.CollectBean
import com.bksx.twankotlin.contract.ICollectView
import com.bksx.twankotlin.presenter.CollectionPresenter
import com.bksx.twankotlin.ui.dialog.X5PopWindow
import com.bksx.twankotlin.utils.formatDate2Day
import com.bksx.twankotlin.utils.getSP
import com.bksx.twankotlin.utils.toast
import com.blankj.utilcode.utils.SPUtils
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.activity_x5_webview.*
import kotlinx.android.synthetic.main.item_common_head.*

class LinkedActivity : BaseMVPActivity<ICollectView, CollectionPresenter>(), ICollectView {
    private val mPresenter by lazy { getPresenter() }
    private var url: String = ""
    private var isCollected: Boolean = false
    private var articleId: Int = -1
    private var isShow: Boolean = true
    private var title: String = ""
    private var collectTime: String = ""
    private var publishTime: String = ""
    private var author = ""

    override fun getLayoutId(): Int {
        return R.layout.activity_x5_webview
    }

    override fun initData() {
    }

    override fun initView() {
        val intent: Intent = intent
        url = intent.getStringExtra(Constant.SEARCH_LINK_KEY)
        toast(url)
        isCollected = intent.getBooleanExtra(Constant.X5_IS_COLLECTED, false)
        articleId = intent.getIntExtra(Constant.X5_ARTICLE_ID, -1)
        isShow = intent.getBooleanExtra(Constant.X5_IS_SHOW, true)
        collectTime = intent.getStringExtra(Constant.X5_COLLECTED_TIME)
        publishTime = intent.getStringExtra(Constant.X5_PUBLISH_TIME)
        author = intent.getStringExtra(Constant.X5_AUTHOR)
        title = intent.getStringExtra(Constant.X5_ARTICLE_TITLE)
        iv_head_more.visibility = if (isShow) View.VISIBLE else View.INVISIBLE
        wv_link.loadUrl(url)

        wv_link.webViewClient = object : WebViewClient() {
            override fun onPageStarted(p0: WebView?, p1: String?, p2: Bitmap?) {
                pb_x5.visibility = View.VISIBLE
                super.onPageStarted(p0, p1, p2)
            }

            override fun onPageFinished(p0: WebView?, p1: String?) {
                pb_x5.visibility = View.GONE
                super.onPageFinished(p0, p1)
            }
        }

        wv_link.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(p0: WebView?, p1: String?) {
                tv_head_title.text = p1
                super.onReceivedTitle(p0, p1)
            }

            override fun onProgressChanged(p0: WebView?, p1: Int) {
                pb_x5.progress = p1
                super.onProgressChanged(p0, p1)
            }
        }
    }

    override fun initEvent() {
        iv_head_back.setOnClickListener {
            finish()
        }
        tv_head_title.text = title
        iv_head_more.setOnClickListener {
            val pop = X5PopWindow(this@LinkedActivity, isCollected)
            pop.run {
                //具体的点击操作
                val username: String = getSP(Constant.SP_USERNAME, "") as String
                //用户登录收藏

                this.setOnCollectUserListenr {
                    if (username.isNotEmpty()) {
                        App.mInstance.toast("用户收藏成功")
                        mPresenter.getCollectUser(articleId)
                    } else {
                        App.mInstance.toast("请登录！")
                    }
                }

                //取消收藏
                this.setonCancelColletListener { mPresenter.getUnCollect(articleId) }


                //离线收藏DB

                this.setOnColletOffListener {
                    collectTime = System.currentTimeMillis().formatDate2Day()
                    mPresenter.getCollectOffLine(articleId, author, publishTime, collectTime, url, title)
                }
            }
        }
    }

    override fun getPresenter(): CollectionPresenter = CollectionPresenter(this)

    override fun showCollect() {
        isCollected = !isCollected
    }

    override fun showUnColelct() {
        isCollected = !isCollected
    }

    override fun showCollectOffLine() {

    }

    override fun showCollectAll() {

    }

    override fun finishRefresh() {

    }

    override fun showCollectUserAll(collectList: MutableList<CollectBean.Data.DataX>) {

    }

    override fun onBackPressed() {
        if (wv_link.canGoBack()) {
            wv_link.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        wv_link.destroy()
    }
}
