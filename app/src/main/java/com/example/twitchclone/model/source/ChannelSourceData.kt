package com.example.twitchclone.model.source

import com.example.twitchclone.model.Channels
import com.example.twitchclone.model.FeatureData

interface ChannelSourceData {
    interface loadCallBack{
        fun dataLoad(offlineData : ArrayList<Channels>, liveData : ArrayList<Channels>, featureData : ArrayList<FeatureData>)
    }

    fun loadData(callback : loadCallBack)
    fun refreshData()
}