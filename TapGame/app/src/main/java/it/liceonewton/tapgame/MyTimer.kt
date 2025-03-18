package it.liceonewton.tapgame

import android.os.CountDownTimer

class MyTimer (seconds:Long,
               val onTickCallBack : (x:Long)->Any,
                val onFinishCallBack: ()->Unit): CountDownTimer (seconds,1000)
{


    override fun onTick(millisUntilFinished: Long) {
        onTickCallBack(millisUntilFinished)
    }

    override fun onFinish() {
        onFinishCallBack()
    }
}