package com.example.hybridandroidkotlin.src

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.hybridandroidkotlin.R
import com.example.hybridandroidkotlin.config.BaseActivity
import com.example.hybridandroidkotlin.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 인터넷 연결상태 확인 후, 연결이 안되어있다면 앱 종료
        val status = MyNetworkManager(this).checkNetworkState()
        if(!status) {
            val toast = Toast.makeText(this, "인터넷 연결을 확인해주세요.  \r\n어플리케이션을 종료합니다.", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER, Gravity.CENTER_HORIZONTAL, Gravity.CENTER_VERTICAL)
            toast.show()

            ActivityCompat.finishAffinity(this)
            exitProcess(0)
        } 
    }

}