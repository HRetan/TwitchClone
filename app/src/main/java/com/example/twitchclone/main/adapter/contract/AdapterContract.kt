package com.example.twitchclone.main.adapter.contract

import com.example.twitchclone.model.Channels
import com.example.twitchclone.model.FeatureData

interface AdapterContract {
    interface View{
        fun notifydata()
    }

    interface Model{
        fun addModel(liveData : ArrayList<Channels>, offLineData : ArrayList<Channels>, featureData : ArrayList<FeatureData>)
        fun getLiveData(position : Int) : Channels
        fun getOfflineData(position : Int) : Channels
        fun getFeatureData(position : Int) : FeatureData
    }
}