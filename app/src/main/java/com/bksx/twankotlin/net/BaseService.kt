package com.bksx.twankotlin.net

import com.bksx.twankotlin.base.ArticleBean
import com.bksx.twankotlin.base.BaseBean
import com.bksx.twankotlin.base.Constant
import com.bksx.twankotlin.bean.*
import io.reactivex.Observable
import retrofit2.http.*

interface BaseService {

    /**
     * 搜索热词
     * */
    @GET(value = Constant.wan_hot_key)
    fun getHotKey(): Observable<HotKeyBean>

    /**
     * 搜索指定内容
     */
    @POST(value = Constant.wan_query + "{index}/json")
    @FormUrlEncoded
    fun query(
        @Path(value = "index") index: Int = 0,
        @Field(value = "k") key: String
    ): Observable<QueryBean>

    /**
     * 登录
     */
    @POST(value = Constant.wan_login)
    @FormUrlEncoded
    fun getLogin(
        @Field(value = "username") username: String,
        @Field(value = "password") password: String
    ): Observable<LoginBean>

    /**
     * 退出登录
     */
    @GET(value = Constant.wan_logout)
    fun getLogOut(): Observable<LoginBean>


    /**
     * 注册
     */
    @POST(value = Constant.wan_register)
    @FormUrlEncoded
    fun getRegister(
        @Field(value = "username") username: String,
        @Field(value = "password") password: String,
        @Field(value = "repassword") repassword: String
    ): Observable<LoginBean>

    /**
     * banner
     */
    @GET(value = Constant.wan_banner)
    fun getBanner(): Observable<BannerBean>

    /**
     * article文章列表
     */
    @GET(value = Constant.wan_artical + "{index}/json")
    fun getArticle(
        @Path(value = "index") index: Int
    ): Observable<ArticleBean>

    /*
    * 知识体系tree
    * */
    @GET(value = Constant.wan_tree)
    fun getTree(): Observable<TreeBean>

    /*
    * 知识体系系列文章
    * */
    @GET(value = Constant.wan_artical + "{index}/json")
    fun getTreeArticle(
        @Path(value = "index") index: Int,
        @Query(value = "cid") cid: Int
    ): Observable<ThirdBean>

    /**
     * 微信公众号
     */
    @GET(value = Constant.wan_wx)
    fun getAuthor(): Observable<AuthorBean>

    /**
     * 微信公众号历史数据
     */
    @GET(value = Constant.wan_wx_history + "{cid}/{index}/json")
    fun getWxHistory(
        @Path(value = "cid") cid: Int,
        @Path(value = "index") index: Int
    ): Observable<WxHistoryBean>

    /**
     * 收藏文章(登录用户)
     */
    @POST(value = Constant.wan_collect_user + "{id}/json")
    fun getCollectUser(
        @Path(value = "id") id: Int
    ): Observable<BaseBean>

    /**
     * 取消收藏文章
     */
    @POST(value = Constant.wan_collect_cancle + "{id}/json")
    fun cancleCollect(
        @Path(value = "id") id: Int
    ): Observable<BaseBean>

    /**
     * 收藏文章列表
     */
    @GET(value = Constant.wan_collect_all + "{index}/json")
    fun getCollectAll(
        @Path(value = "index") index: Int
    ):Observable<CollectBean>
}