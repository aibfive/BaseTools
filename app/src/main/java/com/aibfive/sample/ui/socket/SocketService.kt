package com.aibfive.sample.ui.socket

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.text.TextUtils
import com.aibfive.basetools.util.LogUtil
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.ServerSocket
import java.net.Socket

class SocketService : Service() {

    lateinit var serverSocket: ServerSocket
    lateinit var socket : Socket

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        LogUtil.v("Socket", "onCreate")
        Thread(object : Runnable {
            override fun run() {
                try {
                    LogUtil.v("Socket", "服务端准备创建")
                    serverSocket = ServerSocket(8668)
                    socket = serverSocket.accept()
                    LogUtil.v("Socket", "服务端创建了：$socket")
                    while (!socket.isClosed){
                        val inputStream = socket.getInputStream()
                        val inputStreamReader = InputStreamReader(inputStream)
                        val bufferedReader = BufferedReader(inputStreamReader)
                        val lineStr = bufferedReader.readLine()
                        if(!TextUtils.isEmpty(lineStr)){
                            LogUtil.v("Socket", "接收：$lineStr")
                            val outputStream = socket.getOutputStream()
                            val outputStreamWriter = OutputStreamWriter(outputStream)
                            val bufferedWriter = BufferedWriter(outputStreamWriter)
                            bufferedWriter.write("发送：客户端，你好啊！")
                            //LogUtil.v("Socket", "发送：客户端，你好啊！")
                            bufferedWriter.flush()
                           // outputStream.close()
                            //outputStreamWriter.close()
                            //bufferedWriter.close()
                        }
                        //inputStream.close()
                        //inputStreamReader.close()
                        //bufferedReader.close()
                    }
                } catch (e: Exception) {
                    LogUtil.v("Socket", "服务端报错了：${e.toString()}")
                }
            }
        }).start()
        super.onCreate()
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        LogUtil.v("Socket", "onStart")
    }

    override fun onDestroy() {
        super.onDestroy()
        socket.close()
        serverSocket.close()
    }



}