package com.example.twitchclone.ui.twitchweb

class TwitchPresenter : TwitchContract.Presenter {
    override lateinit var view: TwitchContract.View

    override fun contectWebView() {
        view.showWebView()
    }
}