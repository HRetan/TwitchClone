package com.example.twitchclone.twitchweb

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.twitchclone.R

class TwitchWeb : AppCompatActivity(), TwitchContract.View{

    private val twitch_web by lazy{
        findViewById(R.id.twitch_Web) as WebView
    }
    lateinit var twitchPresenter: TwitchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitch_web)

        supportActionBar?.hide()
        twitch_web.settings.javaScriptEnabled = true

        twitchPresenter = TwitchPresenter().apply {
            view = this@TwitchWeb
        }

        twitchPresenter.contectWebView()
    }

    override fun showWebView() {
        twitch_web.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return false
            }
        }
        twitch_web.loadUrl(intent.getStringExtra("url"))
    }
}
