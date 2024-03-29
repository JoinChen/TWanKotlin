package com.bksx.twankotlin.net

import com.bksx.twankotlin.base.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUtil private constructor(){
    private val okHttpClient:OkHttpClient = OkHttpUtils.mInstance.getHttpClient()
    private var retrofit:Retrofit? = null
    companion object{
        val mInstance : RetrofitUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            RetrofitUtil()
        }
    }

    //配置Retrofit
    private fun getRetrofit() : Retrofit?  {
        if (null == retrofit){
            retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return retrofit
    }

    // 返回BaseService对象，调用其具体接口方法，获取Observable<T>对象
    fun getService() : BaseService = RetrofitUtil.mInstance.getRetrofit()!!.create(BaseService::class.java)
}