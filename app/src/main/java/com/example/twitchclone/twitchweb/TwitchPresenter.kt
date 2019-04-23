package com.example.twitchclone.twitchweb

class TwitchPresenter : TwitchContract.Presenter {
    override lateinit var view: TwitchContract.View

    override fun contectWebView() {
        view.showWebView()
    }
}