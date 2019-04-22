package com.example.twitchclone.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.twitchclone.main.adapter.contract.AdapterContract

class MainAdapter : RecyclerView.Adapter<MainViewHolder>(), AdapterContract.Model, AdapterContract.View {
    override fun onBindViewHolder(p0: MainViewHolder, p1: Int) {

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MainViewHolder {
        return MainViewHolder()
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun notifydata() {

    }

    override fun addModel() {

    }
}