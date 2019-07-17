package com.bksx.twankotlin.net

import com.bksx.twankotlin.base.Constant
import com.bksx.twankotlin.bean.HotKeyBean
import com.bksx.twankotlin.bean.QueryBean
import io.reactivex.Observable
import retrofit2.http.*

interface BaseService {

    /**
     * 搜索热词
     * */
    @GET(value = Constant.wan_hot_key)
    fun getHotKey():Observable<HotKeyBean>

    /**
     * 搜索指定内容
     */
    @POST(value = Constant.wan_query + "{index}/json")
    @FormUrlEncoded
    fun query(
        @Path (value = "index") index: Int = 0,
        @Field (value = "k") key: String
    ):Observable<QueryBean>
}