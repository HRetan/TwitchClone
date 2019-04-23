package com.example.twitchclone.ui.main

import com.example.twitchclone.model.source.ChannelRepository
import com.example.twitchclone.ui.main.adapter.contract.AdapterContract

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
        fun refreshData()
        fun searchData(strChannel : String)
    }
}