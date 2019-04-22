package com.example.twitchclone.util

import com.example.twitchclone.model.Channelfollows
import com.example.twitchclone.model.Features
import com.example.twitchclone.model.LiveStream
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @Headers(
        "Accept: application/vnd.twitchtv.v5+json",
        "Client-ID: clmkfe63efnp9ssj1eogpg57ak3m79"
    )
    @GET("kraken/users/{userID}/follows/channels")
    fun style(@Path("userID") userID : String)
    : Call<Channelfollows>
    // 사용자 채널 정보 가져옴

    @Headers(
        "Accept: application/vnd.twitchtv.v5+json",
        "Client-ID: clmkfe63efnp9ssj1eogpg57ak3m79"
    )
    @GET("kraken/streams/{channelID}")
    fun liveState(@Path("channelID") channelID : String)
    : Call<LiveStream>
    // 채널 ID를 통해 라이브 여부 확인

    @Headers(
        "Accept: application/vnd.twitchtv.v5+json",
        "Client-ID: clmkfe63efnp9ssj1eogpg57ak3m79"
    )
    @GET("kraken/streams/featured?")
    fun recomendChannel(@Query("limit") limit : Int)
    : Call<Features>
}