package com.example.twitchclone.model

//추천 채널
data class Features(val featured : ArrayList<FeatureData>)
data class FeatureData(val stream : Channels)

// 현재 follow한 채널
data class Channelfollows(val follows: ArrayList<Channels>)

// 현재 스트림 중인 채널
data class ChannelSearch(val streams : ArrayList<Channels>)

data class Channels(val channel: ChannelDetail)

data class ChannelDetail(
    val _id: String,
    val game: String,
    val display_name: String,
    val name: String,
    val status: String,
    val logo: String,
    val video_banner: String,
    val url : String
)

// Live 여부 확인
data class LiveStream(
    val stream: String
)