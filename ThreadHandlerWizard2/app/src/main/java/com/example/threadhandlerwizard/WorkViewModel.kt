package com.example.threadhandlerwizard

import android.app.Application
import android.media.MediaPlayer
import android.os.Handler
import android.os.HandlerThread
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class WorkViewModel(private val app: Application) : AndroidViewModel(app) {
    private val handlerThread = HandlerThread("VM-Work").apply { start() }
    private val worker = Handler(handlerThread.looper)

    private val _progress = MutableLiveData(0)
    val progress: LiveData<Int> = _progress

    private val _status = MutableLiveData("Idle")
    val status: LiveData<String> = _status

    private var mediaPlayer: MediaPlayer? = null

    @Volatile private var running = false

    fun start() {
        if (running) return
        running = true
        _status.postValue("Preparing…")
        _progress.postValue(0)

        // 背景工作開始時啟動音樂
        startMusic()

        worker.post {
            try {
                Thread.sleep(3000)
                _status.postValue("Working…")
                for (i in 1..100) {
                    if (!running) break
                    Thread.sleep(100)
                    _progress.postValue(i)
                }
                _status.postValue(if (running) "背景工作結束！" else "Canceled")
                running = false
                // 工作完成時停止音樂
                stopMusic()
            } catch (_: InterruptedException) {
                _status.postValue("Canceled")
                running = false
                stopMusic()
            }
        }
    }

    fun cancel() {
        running = false
        // 按下 Cancel 時立即停止音樂
        stopMusic()
    }

    override fun onCleared() {
        running = false
        stopMusic()
        handlerThread.quitSafely()
        super.onCleared()
    }

    private fun startMusic() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(app, R.raw.background_music)
            mediaPlayer?.isLooping = true
        }
        mediaPlayer?.start()
    }

    private fun stopMusic() {
        worker.post {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }
}