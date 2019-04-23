package com.example.twitchclone.ui.searchchannel.adapter.contract

import com.example.twitchclone.model.Channels

interface SearchAdapterContract {
    interface View{
        fun notifydata()
    }

    interface Model{
        fun addModel(searchData : ArrayList<Channels>)
        fun getSearchData(position : Int) : Channels
    }
}