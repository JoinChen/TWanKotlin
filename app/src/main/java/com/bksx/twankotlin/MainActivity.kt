package com.bksx.twankotlin


import android.content.Intent
import android.os.Looper
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import com.bksx.twankotlin.base.BaseActivity
import com.bksx.twankotlin.ui.SearchActivity
import com.bksx.twankotlin.ui.fragment.HomePageFragment
import com.bksx.twankotlin.utils.start
import com.taonce.wankotlin.ui.fragment.HotKeyFragment
import com.taonce.wankotlin.ui.fragment.TreeFragment
import com.taonce.wankotlin.ui.fragment.WxChapterFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.tv_search

class MainActivity : BaseActivity() {
    private lateinit var fragments: List<Fragment>

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        fragments = listOf(HomePageFragment(), HotKeyFragment(), TreeFragment(), WxChapterFragment())
    }

    override fun initView() {
        vp_content.adapter = MyAdapter(supportFragmentManager)
        vp_content.pageMargin = 16
        vp_content.currentItem = 0
        vp_content.offscreenPageLimit = 3//预加载3个页面
    }

    override fun initEvent() {
        tv_search.setOnClickListener { start(SearchActivity::class.java) }
    }

    inner class MyAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int = fragments.size

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            super.destroyItem(container, position, `object`)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            return super.instantiateItem(container, position)
        }
    }
}
