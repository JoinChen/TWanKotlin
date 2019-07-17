package com.bksx.twankotlin.base.recycler

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.utils.ToastUtils

/**
 * @Author JoneChen
 * @Date 2019\7\15 0015-10:14
 */
abstract class BaseAdapter<T>(
    private val ctx: Context,
    private val layoutRes: Int,
    private val mData: MutableList<T>?,
    private val mHeadView: View? = null,
    private val mFootView: View? = null

) : RecyclerView.Adapter<BaseViewHolder>() {

    //闭包实现事件的lambda的语法
    private var mItemClick: ((position: Int) -> Unit)? = null

    private var mItemLongClick: ((position: Int) -> Boolean)? = null

    companion object {
        const val type_header: Int = 1
        const val type_normal: Int = 0
        const val type_footer: Int = 2
    }

    fun setOnItemClickListener(itemClick: (position: Int) -> Unit) {
        this.mItemClick = itemClick
    }

    fun setOnItemLongClickListener(itemLongClick: (position: Int) -> Boolean) {
        this.mItemLongClick = itemLongClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when {
            mHeadView != null && viewType == 1 -> BaseViewHolder(mHeadView)
            mFootView != null && viewType == 2 -> BaseViewHolder(mFootView)
            else -> BaseViewHolder(LayoutInflater.from(ctx).inflate(layoutRes, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return when {
            (mHeadView != null && mFootView == null) ||
                    (mFootView != null && mHeadView == null) -> (mData?.size ?: 0) + 1
            mHeadView != null && mFootView != null -> (mData?.size ?: 0) + 2
            mHeadView == null && mFootView == null -> mData?.size ?: 0
            else -> mData?.size ?: 0
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            if (mHeadView == null) {
                mItemClick?.invoke(position)
            } else {
                mItemClick?.invoke(position - 1)
            }
        }

        holder.itemView.setOnLongClickListener {
            if (mItemLongClick != null) {
                if (mHeadView == null) mItemLongClick!!.invoke(position)
                else mItemLongClick!!.invoke(position - 1)
            } else return@setOnLongClickListener false
        }

        if (getItemViewType(position) == type_header || getItemViewType(position) == type_footer) return

        convert(holder, position)
    }

    override fun getItemViewType(position: Int): Int {
        mHeadView ?: mFootView ?: return type_normal
        mHeadView?.run { if (position == 0) return type_header }
        mFootView?.run { if (position == itemCount - 1) return type_footer }
        return type_normal
    }

    /**
     * 给具体adapter实现逻辑的抽象方法
     */
    abstract fun convert(holder: BaseViewHolder, position: Int)

    fun addData(item: T){
        mData?.add(item)
        notifyDataSetChanged()
    }

    /**
     * 添加数据,并根据条件是否删除原来数据
     */
    fun addListData(listData: MutableList<T>,isDelete: Boolean = false){
        if (isDelete){
            mData?.clear()
        }

        mData?.addAll(listData)
        notifyDataSetChanged()
    }

    /**
     *删除指定项数据
     */
    fun deletePositionData(position: Int){
        if (position in 0 until itemCount){
            mData?.removeAt(position)
            notifyDataSetChanged()
            if (position != itemCount){
                notifyItemRangeChanged(position,itemCount - position)
            }else{
                ToastUtils.showShortToast(ctx,"delete item failed,position error!")
            }
        }
    }

}