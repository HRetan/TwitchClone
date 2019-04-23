package com.example.twitchclone.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.twitchclone.R
import com.example.twitchclone.main.adapter.MainAdapter
import com.example.twitchclone.model.source.ChannelRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private val liveView by lazy {
        findViewById(R.id.recyclerView_Live) as RecyclerView
    }

    private lateinit var mainPresenter : MainPresenter
    private lateinit var mainAdapter : MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        mainAdapter = MainAdapter(this)

        liveView.layoutManager = LinearLayoutManager(this)

        mainPresenter = MainPresenter(this).apply {
            view = this@MainActivity
            channelData = ChannelRepository
            adapterModel = mainAdapter
            adapterView = mainAdapter
        }

        imageButton.setOnClickListener {
            mainPresenter.searchData(search_Channel.text.toString())
        }

        mainPresenter.dataLoad()

    }

    override fun onResume() {
        super.onResume()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        liveView.adapter = mainAdapter
    }
}
