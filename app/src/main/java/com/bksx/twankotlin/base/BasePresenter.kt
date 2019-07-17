package com.bksx.twankotlin.base

/**
 * @Author JoneChen
 * @Date 2019\7\10 0010-16:19
 */
open class BasePresenter <V>{
    private var view:V? = null

    fun attachView(view:V){
        this.view = view
    }

    fun detachView(){
        this.view = null
    }

    fun isAttach() : Boolean{
        return view != null
    }
}