package com.bksx.twankotlin.ui.dialog

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import android.widget.TextView
import com.bksx.twankotlin.R
import org.w3c.dom.Text

/**
 * @Author JoneChen
 * @Date 2019\7\18 0018-15:19
 */
class X5PopWindow(context: Context, private val isSelected: Boolean) : PopupWindow(context), View.OnClickListener {

    private lateinit var cancelTv: TextView
    private lateinit var collectOffLine: TextView
    private lateinit var collectUser: TextView
    private lateinit var cancelCollect: TextView
    private lateinit var divideView: View

    init {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val mRootView: View = inflater.inflate(R.layout.item_x5_pop, null)
        this.contentView = mRootView
        this.width = WindowManager.LayoutParams.MATCH_PARENT
        this.height = WindowManager.LayoutParams.WRAP_CONTENT
        this.isOutsideTouchable = true
        this.isFocusable = true
        this.animationStyle = R.style.PopAnimator
        this.setBackgroundDrawable(BitmapDrawable(context.resources))
        this.showAtLocation(mRootView, Gravity.BOTTOM, 0, 0)

        initView(mRootView)
        onViewClick()
    }

    private fun onViewClick() {
        cancelTv.setOnClickListener(this)
        collectOffLine.setOnClickListener(this)
        collectUser.setOnClickListener(this)
        cancelCollect.setOnClickListener(this)
    }

    private var onCollectOffListener: (() -> Unit)? = null
    private var onCollectUserListener: (() -> Unit)? = null
    private var onCancelCollectListener: (() -> Unit)? = null
    private var onCancelListener: (() -> Unit)? = null

    private fun initView(view: View) {
        view.run {
            cancelTv = findViewById(R.id.tv_item_pop_cancel)
            collectOffLine = findViewById(R.id.tv_item_pop_collect_offline)
            collectUser = findViewById(R.id.tv_item_pop_collect)
            cancelCollect = findViewById(R.id.tv_item_pop_uncollect)
            divideView = findViewById(R.id.view_item_pop)

            setVisibilityOrGone()
        }
    }

    private fun setVisibilityOrGone() {
        if (isSelected) {
            collectOffLine.visibility = View.GONE
            collectUser.visibility = View.GONE
            divideView.visibility = View.GONE
            cancelCollect.visibility = View.VISIBLE
        } else {
            collectOffLine.visibility = View.VISIBLE
            collectUser.visibility = View.VISIBLE
            divideView.visibility = View.VISIBLE
            cancelCollect.visibility = View.GONE
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_item_pop_cancel -> {
                dismiss()
                onCancelListener?.invoke()
            }

            R.id.tv_item_pop_collect_offline ->{
                dismiss()
                onCollectOffListener?.invoke()
            }

            R.id.tv_item_pop_collect ->{
                dismiss()
                onCollectUserListener?.invoke()
            }

            R.id.tv_item_pop_uncollect ->{
                dismiss()
                onCancelCollectListener?.invoke()
            }
        }
    }

    fun setOnColletOffListener(Block: () -> Unit) {
        this.onCollectOffListener = Block
    }

    fun setOnCollectUserListenr(Block: () -> Unit){
        this.onCollectUserListener = Block
    }

    fun setonCancelColletListener(Block: () -> Unit){
        this.onCancelCollectListener = Block
    }
}