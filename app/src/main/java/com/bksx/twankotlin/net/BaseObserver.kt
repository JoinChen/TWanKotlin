package com.bksx.twankotlin.net

import android.content.Context
import com.bksx.twankotlin.App
import com.bksx.twankotlin.base.BaseBean
import com.bksx.twankotlin.utils.toast
import com.blankj.utilcode.utils.ToastUtils
import com.google.gson.JsonParseException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONException
import retrofit2.HttpException
import java.lang.NullPointerException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

interface BaseObserver <T : BaseBean> : Observer<T>{


    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: T) {
        t.let {
            if (0 == it.errorCode){
                onSuccess(t)
            }else{
                App.mInstance.toast(it.errorMsg)
                onFailed()
            }
        }
    }

    override fun onError(e: Throwable) {
    }

    //成功的处理就交给model层了
    fun onSuccess(value:T){

    }

    // 失败的回调之所以交给model层，是因为需要通过model层的listener回调给presenter层，hideProgress
    fun onFailed()

    fun onErrorAble(e:Throwable?){
        val context : Context = App.mInstance
        when(e){
            is NullPointerException -> ToastUtils.showShortToast(context,"接口挂了")
            is HttpException -> ToastUtils.showShortToast(context,"Http错误")
            is ConnectException -> ToastUtils.showShortToast(context,"链接错误")
            is UnknownHostException -> ToastUtils.showShortToast(context,"找不到主机")
            is InterruptedException -> ToastUtils.showShortToast(context,"链接超时")
            is SocketTimeoutException -> ToastUtils.showShortToast(context,"请求超时")
            is JsonParseException,is JSONException,is ParseException -> ToastUtils.showShortToast(context,"解析错误")
            else ->ToastUtils.showShortToast(context,"接口挂了")
        }
    }
}