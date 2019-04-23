package com.example.twitchclone.ui.main

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.twitchclone.R
import com.example.twitchclone.model.source.ChannelRepository
import com.example.twitchclone.ui.main.adapter.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private val liveView by lazy {
        findViewById(R.id.recyclerView_Live) as RecyclerView
    }
    private val refreshSwipe by lazy{
        findViewById(R.id.swipe_refresh) as SwipeRefreshLayout
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

        refreshSwipe.setOnRefreshListener {
            mainPresenter.refreshData()

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
        refreshSwipe.isRefreshing = false
    }
}
