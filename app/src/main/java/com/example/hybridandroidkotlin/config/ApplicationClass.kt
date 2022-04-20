package com.example.hybridandroidkotlin.config

import android.app.Application
import android.content.SharedPreferences
import okhttp3.MediaType.Companion.toMediaTypeOrNull

class ApplicationClass : Application() {
    val MEDIA_TYPE_JSON = "application/json; charset=uft-8".toMediaTypeOrNull()
    val MEDIA_TYPE_JPEG = "image/jpeg".toMediaTypeOrNull()

    // 테스트 서버 주소
    // val API_URL = "http://dev-api.test.com/"

    // 실 서버 주소
    // val API_URL = "http://api.test.com/"

    // 코틀린의 전역변수 문법
    companion object {
        // 만들어져있는 SharedPreferences 를 사용해야합니다. 재생성하지 않도록 유념해주세요
        lateinit var sSharedPreferences: SharedPreferences
    }

    // 앱이 처음 생성되는 순간, SP를 새로 만들어줍니다.
    override fun onCreate() {
        super.onCreate()
        sSharedPreferences =
            applicationContext.getSharedPreferences("SOFTSQUARED_TEMPLATE_APP", MODE_PRIVATE)
    }
}