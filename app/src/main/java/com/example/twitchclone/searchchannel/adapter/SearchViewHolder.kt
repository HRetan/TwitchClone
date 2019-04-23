package com.example.twitchclone.searchchannel.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.twitchclone.R
import com.example.twitchclone.model.Channels
import com.example.twitchclone.twitchweb.TwitchWeb

class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val text_name by lazy {
        itemView.findViewById(R.id.text_name) as TextView
    }

    private val text_game by lazy {
        itemView.findViewById(R.id.text_game) as TextView
    }

    private val text_status by lazy {
        itemView.findViewById(R.id.text_status) as TextView
    }

    fun onBind(data: Channels) {

        Glide.with(itemView).load(data.channel.video_banner).into(itemView.findViewById(R.id.imageView))
        Glide.with(itemView).load(data.channel.logo).into(itemView.findViewById(R.id.image_logo))

        text_game.text = data.channel.game
        text_name.text = "${data.channel.display_name} (${data.channel.name})"
        text_status.text = data.channel.status

        itemView.setOnClickListener {
            onClick(data)
        }
    }

    private fun onClick(data: Channels)
    {
        var intent = Intent(itemView.context, TwitchWeb::class.java)

        intent.putExtra("url", data.channel.url)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        itemView.context.startActivity(intent)
    }
}