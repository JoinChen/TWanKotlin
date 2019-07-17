package com.bksx.twankotlin.model

import com.bksx.twankotlin.bean.HotKeyBean
import com.bksx.twankotlin.bean.db.HotKeyDB
import com.bksx.twankotlin.contract.IHotKeyModel
import com.bksx.twankotlin.net.BaseObserver
import com.bksx.twankotlin.net.RetrofitUtil
import com.bksx.twankotlin.net.RxSchedulers
import org.litepal.LitePal
import org.litepal.extension.find

/**
 * @Author JoneChen
 * @Date 2019\7\11 0011-14:04
 */
class HotKeyModel : IHotKeyModel {

    override fun findAllKey(listener: IHotKeyModel.OnGetHotKeyListener) {
        LitePal.order("times desc").findAsync(HotKeyDB::class.java).listen {
            listener.onAllKeyListener(it)
        }
    }

    override fun getHotKey(listener: IHotKeyModel.OnGetHotKeyListener) {
        RetrofitUtil.mInstance
            .getService()
            .getHotKey()
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<HotKeyBean> {
                override fun onSuccess(value: HotKeyBean) {
                    listener.onListener(value)
                }

                override fun onFailed() {
                    listener.onListener(null)
                }
            })
    }

    override fun saveKey2DB(key: String) {
        val items: List<HotKeyDB> = LitePal.where("key = ?", key).find()
        if (items.isEmpty()) {
            val hotKeyDB = HotKeyDB()
            hotKeyDB.key = key
            hotKeyDB.times = 1
            hotKeyDB.save()
        } else {
            val hotKeyDB = HotKeyDB()
            hotKeyDB.times = items[0].times + 1
            hotKeyDB.update(items[0].id)
        }
    }
}