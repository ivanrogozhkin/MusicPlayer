package com.xsavzh.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xsavzh.musicplayer.databinding.ActivityMainBinding
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var index: Int = 0

    private var mediaPlayer = MediaPlayer()

    private val trackList = listOf(
        R.raw.feeblescreamsfromforestsunknown,
        R.raw.ealordofthedeeps,
        R.raw.blackspellofdestruction,
        R.raw.channellingthepowerofsoulsintoanewgod,
        R.raw.war,
        R.raw.thecryingorc,
        R.raw.myjourneytothestars,
        R.raw.dungeonsofdarkness,
        R.raw.stemmenfrataarnet,
        R.raw.dominussathanas,
        R.raw.alostforgottensadspirit
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(applicationContext, trackList[index])

        seekbar()
        buttonActions()
    }


    private fun seekbar()  = with(binding) {
        seekbar.max = mediaPlayer.duration

        Timer().scheduleAtFixedRate(timerTask {
            seekbar.progress = mediaPlayer.currentPosition
        }, 0, 1)
    }

     private fun buttonActions() = with(binding) {
         skipPreviousImageButton.setOnClickListener {
            mediaPlayer.pause()
            index--
            if (index < 0) index = trackList.size - 1

            mediaPlayer = MediaPlayer.create(applicationContext, trackList[index])
            mediaPlayer.start()
         }

         skipNextImageButton.setOnClickListener {
             mediaPlayer.pause()
             index++
             if (index > trackList.size - 1) index = 0

             mediaPlayer = MediaPlayer.create(applicationContext, trackList[index])
             mediaPlayer.start()
         }

         playImageButton.setOnClickListener {
             if (mediaPlayer.isPlaying) {
                 mediaPlayer.pause()
                 playImageButton.setImageResource(R.drawable.ic_baseline_play_arrow_75)
             } else {
                 mediaPlayer.start()
                 playImageButton.setImageResource(R.drawable.ic_baseline_pause_75)
             }
         }
     }
}