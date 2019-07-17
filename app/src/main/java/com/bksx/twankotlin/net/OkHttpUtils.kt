package com.bksx.twankotlin.net

import android.util.TimeUtils
import com.bksx.twankotlin.App
import com.bksx.twankotlin.base.Constant
import com.bksx.twankotlin.utils.getSP
import com.blankj.utilcode.utils.LogUtils
import com.blankj.utilcode.utils.SPUtils
import com.parkingwang.okhttp3.LogInterceptor.LogInterceptor
import okhttp3.*
import java.io.File
import java.util.concurrent.TimeUnit

class OkHttpUtils private constructor(){

    private var okHttpClient : OkHttpClient? = null
    // 自定义TAG的Log
    private val okHttpLog: OkHttpLog by lazy { OkHttpLog() }
    // 连接超时时间
    private val connectionTime: Long = 10L
    // 写入超时时间
    private val writeTime: Long = 10L
    // 读取超时时间
    private val readTime: Long = 30L
    // 缓存文件
    private val cacheFile: File = File(App.mInstance.cacheDir.absolutePath)
    // 缓存文件大小
    private val maxSize: Long = 8 * 1024 * 1024
    // OkHttpCache
    private var cache: Cache? = null

    companion object {
        val mInstance: OkHttpUtils by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            OkHttpUtils()
        }
    }

    init {
        //初始化缓存文件
        if (!cacheFile.exists())cacheFile.mkdir()
        cache = Cache(cacheFile,maxSize)
    }

    //配置OkHttp
    fun getHttpClient():OkHttpClient{
        val hashmap:HashMap<String,MutableList<Cookie>> = HashMap()
        if (null == okHttpClient){
            okHttpClient = OkHttpClient.Builder()
                .cookieJar(object : CookieJar{
                    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
                        hashmap[url.host()] = cookies
                }

                    override fun loadForRequest(url: HttpUrl?): MutableList<Cookie> {
                        return hashmap[url?.host()]?: mutableListOf()
                    }
                })
                .connectTimeout(connectionTime,TimeUnit.SECONDS)
                .readTimeout(readTime,TimeUnit.SECONDS)
                .writeTimeout(writeTime,TimeUnit.SECONDS)
                .addInterceptor(LogInterceptor(okHttpLog))
                .addInterceptor(HeadIntercept())
                .addInterceptor(CookieIntercept())
                .cache(cache)
                .build()
        }
        return okHttpClient!!
    }

    //自定义OkHttpLog
    class OkHttpLog : LogInterceptor.Logger{
        override fun log(message: String?) {
            LogUtils.i("okhttp","$message")
        }
    }

    //add cookie
    class HeadIntercept : Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            val request:Request = chain.request()
            val builder:Request.Builder = request.newBuilder()
            builder.addHeader("Content-type","application/json;charset=utf-8")
            val domain:String = request.url().host()
            if (domain.isNotEmpty()){
                val cookies:String = App.mInstance.getSP(Constant.SP_COOKIE,"").toString()
                builder.addHeader(Constant.COOKIE,cookies)
            }
            return chain.proceed(builder.build())
        }
    }

    //sava cookie
    class CookieIntercept:Interceptor{
        override fun intercept(chain: Interceptor.Chain?): Response {
            val request:Request = chain!!.request()
            val response: Response = chain.proceed(request)
            val requesetUrl:String = request.url().toString()
            if ((requesetUrl.contains(Constant.wan_login)
                        || requesetUrl.contains(Constant.wan_register))
                && response!!.header(Constant.SET_COOKIE).isNotEmpty()){
                val cookies : MutableList<String> = response.headers(Constant.SET_COOKIE)
                val cookie : String = CookieUtils.encodeCookie(cookies)
                CookieUtils.savaCookie(cookie)
            }
            return response
        }

    }
}