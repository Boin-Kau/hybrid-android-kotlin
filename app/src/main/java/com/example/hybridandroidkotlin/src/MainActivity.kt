package com.example.hybridandroidkotlin.src

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.webkit.WebSettings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.hybridandroidkotlin.R
import com.example.hybridandroidkotlin.config.BaseActivity
import com.example.hybridandroidkotlin.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    @SuppressLint("ObsoleteSdkInt", "SetJavaScriptEnabled")
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

        val mWebView = binding.webView

        //하드웨어 가속 활성화 / 비활성화 :
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // chromium, enable hardware acceleration
            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        } else {
            // older android version, disable hardware acceleration
            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        }

        val mWebSettings = mWebView.settings

        //시스템 폰트크기 무시
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            mWebSettings.textZoom = 100
        }

        mWebSettings.javaScriptEnabled = true
        mWebSettings.setSupportMultipleWindows(true) // 새창 띄우기 허용 여부
        mWebSettings.javaScriptCanOpenWindowsAutomatically = false // 자바스크립트 새창(멀티뷰) 띄우기 허용 여부
        mWebSettings.useWideViewPort = true // 화면 사이즈 맞추기 허용
        mWebSettings.setSupportZoom(false) // 화면 줌 허용 여부
        mWebSettings.builtInZoomControls = false // 화면 확대 축소 허용 여부
        mWebSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN // 컨텐츠 사이즈 맞추기
        mWebSettings.cacheMode = WebSettings.LOAD_NO_CACHE // 브라우저 캐시 허용 여부
        mWebSettings.domStorageEnabled = true // 로컬저장소 허용 여부
        mWebSettings.saveFormData = true // 입력된 데이터 저장 허용 여부

    }

}