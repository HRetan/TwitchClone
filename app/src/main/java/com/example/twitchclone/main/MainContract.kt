package com.example.twitchclone.main

import com.example.twitchclone.model.source.ChannelRepository

interface MainContract {
    interface View{

    }

    interface Presenter{
        var view : View
        var channelData : ChannelRepository

        fun dataLoad()
    }
}