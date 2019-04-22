package com.example.twitchclone.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.twitchclone.R
import com.example.twitchclone.main.adapter.contract.AdapterContract
import com.example.twitchclone.model.Channels

class MainAdapter(var context : Context, var sytle : ChannelStyle) : RecyclerView.Adapter<MainViewHolder>(), AdapterContract.Model, AdapterContract.View {

    enum class ChannelStyle{
    LIVE_CHANNEL, OFFLINE_CHANNEL, FEATURE_CHANNEL
}

    private var channelStyle : ChannelStyle = sytle
    private var channelData : ArrayList<Channels> = ArrayList<Channels>()

    override fun onBindViewHolder(p0: MainViewHolder, p1: Int) {
        p0.onBind(channelData[p1])
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MainViewHolder {
        lateinit var view : View
        when(channelStyle)
        {
            ChannelStyle.LIVE_CHANNEL->{
                view = LayoutInflater.from(context).inflate(R.layout.item_channel, p0, false)
            }
            ChannelStyle.OFFLINE_CHANNEL->{
                view = LayoutInflater.from(context).inflate(R.layout.item_offlinechannel, p0, false)
            }
            ChannelStyle.FEATURE_CHANNEL->{
                view = LayoutInflater.from(context).inflate(R.layout.item_channel, p0, false)
            }
        }

        return MainViewHolder(view)
    }

    override fun getItemCount() = channelData.size

    override fun notifydata() {
        notifyDataSetChanged()
    }

    override fun addModel(dataList : ArrayList<Channels>) {
        channelData = dataList
    }

    override fun getData(position: Int) = channelData[position]
}