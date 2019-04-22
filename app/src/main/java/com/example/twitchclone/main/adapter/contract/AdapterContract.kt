package com.example.twitchclone.main.adapter.contract

import com.example.twitchclone.model.Channels

interface AdapterContract {
    interface View{
        fun notifydata()
    }

    interface Model{
        fun addModel(dataList : ArrayList<Channels>)
        fun getData(position : Int) : Channels
    }
}