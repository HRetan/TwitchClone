package com.example.twitchclone.main

import android.content.Context
import com.example.twitchclone.model.Channels
import com.example.twitchclone.model.FeatureData
import com.example.twitchclone.model.source.ChannelRepository
import com.example.twitchclone.model.source.ChannelSourceData

class MainPresenter(var context : Context) : MainContract.Presenter {
    override lateinit var channelData: ChannelRepository
    override lateinit var view: MainContract.View

    override fun dataLoad() {
        channelData.loadData(object : ChannelSourceData.loadCallBack{
            override fun dataLoad(
                offlineData: ArrayList<Channels>,
                liveData: ArrayList<Channels>,
                featureData: ArrayList<FeatureData>
            ) {

            }
        })
    }
}