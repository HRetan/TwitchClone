package com.example.twitchclone.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.twitchclone.R
import com.example.twitchclone.main.adapter.MainAdapter
import com.example.twitchclone.model.source.ChannelRepository

class MainActivity : AppCompatActivity(), MainContract.View {

    private val liveView by lazy {
        findViewById(R.id.recyclerView_Live) as RecyclerView
    }
    private val offlineView by lazy {
        findViewById(R.id.recyclerView_Offline) as RecyclerView
    }

    private val featureView by lazy {
        findViewById(R.id.recyclerView_Recommend) as RecyclerView
    }

    private lateinit var mainPresenter : MainPresenter
    private lateinit var liveAdapter : MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        liveAdapter = MainAdapter(this, MainAdapter.ChannelStyle.LIVE_CHANNEL)

        liveView.layoutManager = LinearLayoutManager(this)

        mainPresenter = MainPresenter(this).apply {
            view = this@MainActivity
            channelData = ChannelRepository
        }

        mainPresenter.dataLoad()
        liveView.adapter = liveAdapter
    }
}
