package com.aibfive.sample.ui.socket

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import com.aibfive.basetools.ui.BaseActivity
import com.aibfive.basetools.util.LogUtil
import com.aibfive.sample.databinding.ActivitySocketBinding
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket

class SocketActivity : BaseActivity<ActivitySocketBinding>() {

    var socket : Socket? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, SocketActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun initView() {
        super.initView()
        binding.btnOpen.setOnClickListener {
            val intent = Intent(this, SocketService::class.java)
            startService(intent)
        }
        binding.btnConnect.setOnClickListener {
            connect()
        }
        binding.btnSend.setOnClickListener {
            send()
        }

    }

    fun connect(){
        //while (socket == null){

        //}
        Thread(object : Runnable{
            override fun run() {
                try {
                    //while (socket == null) {
                        socket = Socket("localhost", 8668)
                        //socket = Socket("172.16.114.1", 8668)
                        LogUtil.v("Socket", "客户端创建了：$socket")
                    //}
                    while (!socket!!.isClosed){
                        val inputStream = socket!!.getInputStream()
                        val inputStreamReader = InputStreamReader(inputStream)
                        val bufferedReader = BufferedReader(inputStreamReader)
                        val lineStr = bufferedReader.readLine()
                        if(!TextUtils.isEmpty(lineStr)) {
                            LogUtil.v("Socket", "接收：$lineStr")
                            //inputStream.close()
                           // inputStreamReader.close()
                            //bufferedReader.close()
                            break
                        }
                    }
                } catch (e: Exception) {
                    LogUtil.v("Socket", "connect客户端报错了：${e.toString()}")
                }
            }
        }).start()
    }

    fun send(){
        /*if(socket==null){
            LogUtil.v("Socket", "Socket is Null")
            return
        }*/
        Thread(object : Runnable{
            override fun run() {
                try {
                    val outputStream = socket!!.getOutputStream()
                    val outputStreamWriter = OutputStreamWriter(outputStream)
                    val bufferedWriter = BufferedWriter(outputStreamWriter)
                    bufferedWriter.write("发送：服务端，你好啊！")
                    bufferedWriter.flush()
                    outputStream.close()
                    outputStreamWriter.close()
                    bufferedWriter.close()
                } catch (e: Exception) {
                    LogUtil.v("Socket", "send客户端报错了：${e.toString()}")
                }
            }
        }).start()

    }
}