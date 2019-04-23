package com.example.twitchclone.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.twitchclone.R
import com.example.twitchclone.main.adapter.contract.AdapterContract
import com.example.twitchclone.model.Channels
import com.example.twitchclone.model.FeatureData

class MainAdapter(var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    AdapterContract.Model, AdapterContract.View {

    private var liveItem: ArrayList<Channels> = ArrayList<Channels>()
    private var offlineItem: ArrayList<Channels> = ArrayList<Channels>()
    private var featureItem: ArrayList<FeatureData> = ArrayList<FeatureData>()

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        when (p1)
        {
            0, liveItem.size + 1, liveItem.size + featureItem.size + 2 -> {
                var holder = p0 as MainViewHolder
                if(p1 == 0)
                    holder.onBind("생방송 채널")
                else if(p1 == liveItem.size + 1 && featureItem.size != 0)
                    holder.onBind("추천 채널")
                else if(offlineItem.size != 0)
                    holder.onBind("오프라인 채널")

            }
            in 1..liveItem.size -> {
                var holder = p0 as LiveViewHolder
                holder.onBind(liveItem[p1 - 1])
            }
            in liveItem.size + 2..liveItem.size + featureItem.size + 1 -> {
                var holder = p0 as FeatureViewHolder
                holder.onBind(featureItem[p1 - (liveItem.size + 2)])
            }

            in liveItem.size + featureItem.size + 3..liveItem.size + featureItem.size + offlineItem.size + 4 -> {
                var holder = p0 as OffLineViewHolder
                holder.onBind(offlineItem[p1 - (liveItem.size + featureItem.size + 3)])
            }
        }
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        lateinit var view: View
        when (p1) {
            0 -> {
                view = LayoutInflater.from(context).inflate(R.layout.item_textline, p0, false)
                return MainViewHolder(view)
            }
            1 -> {
                view = LayoutInflater.from(context).inflate(R.layout.item_channel, p0, false)
                return LiveViewHolder(view)
            }
            2 -> {
                view = LayoutInflater.from(context).inflate(R.layout.item_channel, p0, false)
                return FeatureViewHolder(view)
            }
            else -> {
                view = LayoutInflater.from(context).inflate(R.layout.item_offlinechannel, p0, false)
                return OffLineViewHolder(view)
            }
        }
    }

    override fun getItemCount() = liveItem.size + featureItem.size + offlineItem.size + 3

    override fun notifydata() {
        notifyDataSetChanged()
    }

    override fun addModel(
        liveData: ArrayList<Channels>,
        offLineData: ArrayList<Channels>,
        featureData: ArrayList<FeatureData>
    ) {
        liveItem = liveData
        offlineItem = offLineData
        featureItem = featureData
    }

    override fun getFeatureData(position: Int): FeatureData {
        return featureItem[position - (liveItem.size + 2)]
    }

    override fun getLiveData(position: Int): Channels {
        return liveItem[position - 1]
    }

    override fun getOfflineData(position: Int): Channels {
        return offlineItem[position - (liveItem.size + featureItem.size + 3)]
    }

    override fun getItemViewType(position: Int): Int {
        when (position) {
            0, liveItem.size + 1, liveItem.size + featureItem.size + 2 -> return 0
            in 1..liveItem.size -> return 1
            in liveItem.size + 2..liveItem.size + featureItem.size + 1 -> return 2
        }

        return 3
    }
}