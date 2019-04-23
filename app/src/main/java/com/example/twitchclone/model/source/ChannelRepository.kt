package com.example.twitchclone.model.source

import com.example.twitchclone.model.Channels
import com.example.twitchclone.model.FeatureData
import com.example.twitchclone.model.source.remote.ChannelRemoteSourceData

object ChannelRepository : ChannelSourceData {

    private val channelRemoteSourceData = ChannelRemoteSourceData

    override fun loadData(callback: ChannelSourceData.loadCallBack) {

        channelRemoteSourceData.loadData(object : ChannelSourceData.loadCallBack{
            override fun dataLoad(
                offlineData: ArrayList<Channels>,
                liveData: ArrayList<Channels>,
                featureData: ArrayList<FeatureData>
            ) {
                    callback.dataLoad(offlineData, liveData, featureData)
            }
        })

    }

    override fun SearchData(strChannel: String, callback: ChannelSourceData.searchCallBack) {
        channelRemoteSourceData.SearchData(strChannel, object : ChannelSourceData.searchCallBack{
            override fun dataLoad(searchData: ArrayList<Channels>) {
                callback.dataLoad(searchData)
            }
        })
    }

    override fun refreshData() {

    }
}