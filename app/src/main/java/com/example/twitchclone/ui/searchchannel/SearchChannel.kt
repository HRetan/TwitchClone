package com.example.twitchclone.ui.searchchannel

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.twitchclone.R
import com.example.twitchclone.model.source.ChannelRepository
import com.example.twitchclone.ui.searchchannel.adapter.SearchAdapter
import kotlinx.android.synthetic.main.activity_search_channel.*

class SearchChannel : AppCompatActivity(), SearchContract.View {

    private val recyclerView by lazy{
        findViewById(R.id.recyclerView_Search) as RecyclerView
    }

    private lateinit var searchPresenter: SearchPresenter
    private lateinit var searchAdapter : SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_channel)

        supportActionBar?.hide()

        searchAdapter = SearchAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)

        searchPresenter = SearchPresenter().apply{
            view = this@SearchChannel
            channelData = ChannelRepository
            adapterModel = searchAdapter
            adapterView = searchAdapter
        }

        imageButton3.setOnClickListener {
            searchPresenter.searchData(text_Channel.text.toString())
        }

        imageButton2.setOnClickListener {
            finish()
        }

        searchPresenter.connectData()
    }


    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }


    override fun showLoading() {
        progressBar2.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar2.visibility = View.GONE
    }

    override fun showList() {
        searchPresenter.searchData(intent.getStringExtra("channelName"))
        recyclerView.adapter = searchAdapter
    }
}
