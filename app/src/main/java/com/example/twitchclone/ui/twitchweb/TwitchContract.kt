package com.example.twitchclone.ui.twitchweb

interface TwitchContract {
    interface View{
        fun showWebView()
    }

    interface Presenter{
        var view : View

        fun contectWebView()
    }
}