package com.example.twitchclone.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.example.twitchclone.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainPresenter = MainPresenter(this).apply {
            view = this@MainActivity
            channelData = ChannelRepository
        }

        mainPresenter.dataLoad()
    }
}
