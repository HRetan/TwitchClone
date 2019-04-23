package com.example.twitchclone.searchchannel

import com.example.twitchclone.model.Channels
import com.example.twitchclone.model.source.ChannelRepository
import com.example.twitchclone.model.source.ChannelSourceData
import com.example.twitchclone.searchchannel.adapter.contract.SearchAdapterContract

class SearchPresenter : SearchContract.Presenter{
    override lateinit var view: SearchContract.View
    override lateinit var channelData: ChannelRepository

    override lateinit var adapterModel: SearchAdapterContract.Model
    override var adapterView: SearchAdapterContract.View? = null

    override fun searchData(strChannel: String) {
        adapterModel.addModel(ArrayList<Channels>())
        adapterView?.notifydata()

        view.showLoading()
        channelData.SearchData(strChannel, object : ChannelSourceData.searchCallBack {
            override fun dataLoad(
                searchData: ArrayList<Channels>
            ) {
                adapterModel.addModel(searchData)
                view.hideLoading()
                adapterView?.notifydata()
            }
        })
    }

    override fun connectData(){
        view.showList()
    }
}