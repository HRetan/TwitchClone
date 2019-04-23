package com.example.twitchclone.searchchannel.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.twitchclone.R
import com.example.twitchclone.model.Channels
import com.example.twitchclone.searchchannel.adapter.contract.SearchAdapterContract

class SearchAdapter(var context: Context) : RecyclerView.Adapter<SearchViewHolder>(), SearchAdapterContract.Model,
    SearchAdapterContract.View {

    private var searchItem : ArrayList<Channels> = ArrayList<Channels>()

    override fun onBindViewHolder(p0: SearchViewHolder, p1: Int) {
        p0.onBind(searchItem[p1])
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SearchViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_channel, p0, false)
        return SearchViewHolder(view)
    }

    override fun getSearchData(position: Int) = searchItem[position]

    override fun getItemCount() = searchItem.size

    override fun addModel(searchData: ArrayList<Channels>) {
        searchItem = searchData
    }

    override fun notifydata() {
        notifyDataSetChanged()
    }
}