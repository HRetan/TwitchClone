package com.example.twitchclone.ui.searchchannel

import com.example.twitchclone.model.source.ChannelRepository
import com.example.twitchclone.ui.searchchannel.adapter.contract.SearchAdapterContract

interface SearchContract {
    interface View{
        fun showLoading()
        fun hideLoading()
        fun showList()
    }

    interface Presenter{
        var view : View
        var channelData : ChannelRepository

        var adapterModel : SearchAdapterContract.Model
        var adapterView : SearchAdapterContract.View?

        fun searchData(strChannel : String)
        fun connectData()
    }
}