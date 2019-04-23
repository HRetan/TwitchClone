package com.example.twitchclone.model.source.remote

import android.os.Handler
import com.example.twitchclone.model.*
import com.example.twitchclone.model.source.ChannelSourceData
import com.example.twitchclone.util.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ChannelRemoteSourceData : ChannelSourceData {
//모델의 분량이 너무 커지기 때문에 분할을 할지 생각해 볼 문제

    private var allData: ArrayList<Channels> = ArrayList<Channels>()
    private var liveData: ArrayList<Channels> = ArrayList<Channels>()
    private var offlineData: ArrayList<Channels> = ArrayList<Channels>()
    private var featureData: ArrayList<FeatureData> = ArrayList<FeatureData>()
    private var searchData: ArrayList<Channels> = ArrayList<Channels>()
    private val userID: String = "146902104"

    private fun addFollowData() {
        RetrofitClient.getService().style(userID).enqueue(object : Callback<Channelfollows> {
            override fun onFailure(call: Call<Channelfollows>, t: Throwable) {

            }

            override fun onResponse(call: Call<Channelfollows>, response: Response<Channelfollows>) {
                var channelFollows: Channelfollows = response.body()!!


                for (iIndex in 0..(channelFollows.follows.size - 1)) {
                    allData.add(channelFollows.follows[iIndex])
                }

                for (iIndex in 0..(allData.size - 1)) {
                    checkLive(allData[iIndex])
                }
            }
        })
    }

    private fun checkLive(channel: Channels) {
        RetrofitClient.getService().liveState(channel.channel._id).enqueue(object : Callback<LiveStream> {
            override fun onResponse(call: Call<LiveStream>, response: Response<LiveStream>) {
                var liveStream: LiveStream = response.body()!!
                if (liveStream.stream == null) {
                    offlineData.add(channel)
                }
                else{
                    liveData.add(channel)
                }
            }

            override fun onFailure(call: Call<LiveStream>, t: Throwable) {

            }
        })
    }

    private fun addFeatureData() {
        RetrofitClient.getService().recomendChannel(5).enqueue(object : Callback<Features> {
            override fun onFailure(call: Call<Features>, t: Throwable) {

            }

            override fun onResponse(call: Call<Features>, response: Response<Features>) {
                var feature: Features = response.body()!!

                for (iIndex in 0..(feature.featured.size - 1)) {
                    featureData.add(feature.featured[iIndex])
                }
            }
        })
    }

    private fun addSearchData(strChannel: String) {
        RetrofitClient.getService().searchChannel(strChannel).enqueue(object : Callback<ChannelSearch> {
            override fun onFailure(call: Call<ChannelSearch>, t: Throwable) {

            }

            override fun onResponse(call: Call<ChannelSearch>, response: Response<ChannelSearch>) {
                var channelFollows: ChannelSearch = response.body()!!

                println("Test2 : ${channelFollows}")
                if (channelFollows.streams == null)
                    return

                for (iIndex in 0..(channelFollows.streams.size - 1)) {
                    searchData.add(channelFollows.streams[iIndex])
                }
            }
        })
    }

    override fun loadData(callback: ChannelSourceData.loadCallBack) {
        addFollowData()

        addFeatureData()

        Handler().postDelayed({ callback.dataLoad(offlineData, liveData, featureData) }, 5000)
    }

    override fun SearchData(strChannel: String, callback: ChannelSourceData.searchCallBack) {
        searchData.clear()

        addSearchData(strChannel)

        Handler().postDelayed({ callback.dataLoad(searchData) }, 3000)
    }

    override fun refreshData() {

    }

    fun clearData() {
        allData.clear()
        offlineData.clear()
        liveData.clear()
        featureData.clear()
    }
}