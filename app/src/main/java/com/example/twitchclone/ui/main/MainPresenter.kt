package com.example.twitchclone.ui.main

import android.content.Context
import android.content.Intent
import com.example.twitchclone.model.Channels
import com.example.twitchclone.model.FeatureData
import com.example.twitchclone.model.source.ChannelRepository
import com.example.twitchclone.model.source.ChannelSourceData
import com.example.twitchclone.ui.main.adapter.contract.AdapterContract
import com.example.twitchclone.ui.searchchannel.SearchChannel

class MainPresenter(var context: Context) : MainContract.Presenter {
    override lateinit var channelData: ChannelRepository
    override lateinit var view: MainContract.View

    override lateinit var adapterModel: AdapterContract.Model

    override var adapterView: AdapterContract.View? = null

    override fun dataLoad() {
        view.showLoading()
        channelData.loadData(object : ChannelSourceData.loadCallBack {
            override fun dataLoad(
                offlineData: ArrayList<Channels>,
                liveData: ArrayList<Channels>,
                featureData: ArrayList<FeatureData>
            ) {
                adapterModel.addModel(liveData, offlineData, featureData)
                view.hideLoading()
                adapterView?.notifydata()
            }
        })

    }

    override fun refreshData() {
        channelData.refreshData()

        adapterModel.addModel(ArrayList<Channels>(), ArrayList<Channels>(), ArrayList<FeatureData>())
        adapterView?.notifydata()

        view.showLoading()
        channelData.loadData(object : ChannelSourceData.loadCallBack {
            override fun dataLoad(
                offlineData: ArrayList<Channels>,
                liveData: ArrayList<Channels>,
                featureData: ArrayList<FeatureData>
            ) {
                adapterModel.addModel(liveData, offlineData, featureData)
                view.hideLoading()
                adapterView?.notifydata()
            }
        })
    }

    override fun searchData(strChannel: String) {
        var intent = Intent(context, SearchChannel::class.java)

        intent.putExtra("channelName", strChannel)

        context.startActivity(intent)
    }

    fun clearAdapter(){
        adapterModel.addModel(
            ArrayList<Channels>(),
            ArrayList<Channels>(),
            ArrayList<FeatureData>()
        )
        adapterView?.notifydata()
    }
}