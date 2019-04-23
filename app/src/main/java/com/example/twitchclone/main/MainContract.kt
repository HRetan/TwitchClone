package com.example.twitchclone.main

import com.example.twitchclone.main.adapter.contract.AdapterContract
import com.example.twitchclone.model.source.ChannelRepository

interface MainContract {
    interface View{
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter{
        var view : View
        var channelData : ChannelRepository

        var adapterModel : AdapterContract.Model
        var adapterView : AdapterContract.View?

        fun dataLoad()
        fun searchData(strChannel : String)
    }
}