package com.bksx.twankotlin.presenter

import com.bksx.twankotlin.base.BasePresenter
import com.bksx.twankotlin.bean.HotKeyBean
import com.bksx.twankotlin.bean.db.HotKeyDB
import com.bksx.twankotlin.contract.IHotKeyModel
import com.bksx.twankotlin.contract.IHotKeyView
import com.bksx.twankotlin.model.HotKeyModel

/**
 * @Author JoneChen
 * @Date 2019\7\11 0011-14:26
 */
class HotKeyPresenter(private val mView: IHotKeyView) :
    BasePresenter<IHotKeyView>(), IHotKeyModel.OnGetHotKeyListener{

    private val model: IHotKeyModel = HotKeyModel()

    fun getHotKey(): Unit = model.getHotKey(this)

    fun saveKey2DB(key: String): Unit = model.saveKey2DB(key)

    fun findAllKey(): Unit = model.findAllKey(this)

    /*设置model数据的显示 与view产生联系*/
    override fun onListener(hotKeyBean: HotKeyBean?) {
        hotKeyBean?.let {
            mView.showHotKey(hotKeyBean)
        }
    }

    override fun onAllKeyListener(hotKeyMultiList: MutableList<HotKeyDB>) {
        if (hotKeyMultiList.isNotEmpty()) mView.showHotKeyDB(hotKeyMultiList)
    }
}