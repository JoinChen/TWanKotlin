package com.bksx.twankotlin.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bksx.twankotlin.App
import com.bksx.twankotlin.R
import com.bksx.twankotlin.base.ArticleBean
import com.bksx.twankotlin.base.BaseFragment
import com.bksx.twankotlin.base.Constant
import com.bksx.twankotlin.bean.BannerBean
import com.bksx.twankotlin.contract.IHomeView
import com.bksx.twankotlin.presenter.HomePagePresenter
import com.bksx.twankotlin.ui.LinkedActivity
import com.bksx.twankotlin.ui.SearchResultActivity
import com.bksx.twankotlin.ui.adapter.HomeArticleAdapter
import com.bksx.twankotlin.utils.*
import com.bumptech.glide.Glide
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.youth.banner.Banner
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.activity_search_result.*
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @Author JoneChen
 * @Date 2019\7\9 0009-17:48
 */
class HomePageFragment : BaseFragment(), IHomeView {
    private lateinit var mPresenter: HomePagePresenter
    private var banner: Banner? = null
    private val bannerImageUrl: MutableList<String> = mutableListOf()
    private val bannerUrl: MutableList<String> = mutableListOf()
    private var index: Int = 0
    private var articleList: MutableList<ArticleBean.Data.DataX> = mutableListOf()
    private var homeArticleAdapter : HomeArticleAdapter? = null

    override fun initView() {
        mPresenter = HomePagePresenter(this)
        mPresenter.getBanner()
        mPresenter.getArticle(index)
    }

    override fun initData() {
        homeArticleAdapter = HomeArticleAdapter(this.context!!,R.layout.item_search_result,articleList)
        rv_home_page.apply {
            adapter = homeArticleAdapter
            layoutManager = LinearLayoutManager(this.context!!)
        }
        homeArticleAdapter?.notifyDataSetChanged()
        homeArticleAdapter?.setOnItemClickListener {position ->
            val dataX = articleList[position]
            toCommonX5Activity(
                context = this.context!!,
                url = dataX.link,
                isCollected = dataX.collect,
                articleId = dataX.id,
                collectTime = "",
                publishTime = dataX.publishTime.formatDate2Day(),
                author = dataX.author,
                title = dataX.title
            )
        }

        srl_home_page.setRefreshHeader(ClassicsHeader(this.context!!))
        srl_home_page.setRefreshFooter(ClassicsFooter(this.context!!))
        srl_home_page.autoRefresh()
    }

    override fun initEvent() {
        banner = home_banner
        banner?.apply {
            setBannerAnimation(Transformer.DepthPage)
            isAutoPlay(true)
            setDelayTime(5000)
            setIndicatorGravity(Gravity.CENTER)
        }

        srl_home_page.setOnRefreshListener { mPresenter.getArticle(index) }
        srl_home_page.setOnLoadMoreListener { mPresenter.getArticle(index) }
    }

    override fun getLayouId(): Int {
        return R.layout.fragment_home
    }

    override fun showHomeBanner(bannerBean: BannerBean) {
        bannerBean.data.let {
            it.forEach { it ->
                bannerImageUrl.add(it.imagePath)
                bannerUrl.add(it.url)
            }
        }

        banner?.apply {
            setImages(bannerImageUrl)
            setImageLoader(GlideImageLoader())
            start()
        }

        banner?.setOnBannerListener { position ->
            val url: String = bannerUrl[position]
            toCommonX5Activity(
                context = this.context!!,
                url = url,
                isCollected = false,
                articleId = 0,
                collectTime = "",
                publishTime = "",
                author = "",
                title = ""
            )
        }
    }

    override fun showHomeArticle(articleBean: MutableList<ArticleBean.Data.DataX>) {
        index ++
        articleList.addAll(articleBean)
    }

    override fun finishedRefresh() {
        srl_home_page.finishRefresh()
        srl_home_page.finishLoadMore()
    }

    inner class GlideImageLoader : ImageLoader(){
        override fun displayImage(context: Context, path: Any, imageView: ImageView) {
            this@HomePageFragment.context?.loadImageWithUrl(path as String,imageView)
        }
    }

    public fun toCommonX5Activity(
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