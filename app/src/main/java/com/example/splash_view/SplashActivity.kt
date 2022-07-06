package com.example.splash_view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableBoolean
import com.bumptech.glide.Glide
import com.example.splash_view.databinding.ActivityMainBinding
import com.example.splash_view.databinding.ActivitySplashBinding
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    //데이터바이딩 사용
    private lateinit var binding :ActivitySplashBinding

    //Bundle -> 여러가지타입을 저장하는 Map 클래스
    //액티비티간 데이터를 주고받을때 번들을 사용해서 다양한 데이터 전송
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //화면에 출력하는 xml을 명시하기
        //setContentView(R.layout.activity_splash)

        //데이터바이딩으로 사용 위에 코드랑 같은 의미
        binding = DataBindingUtil.setContentView(this,R.layout.activity_splash)
        binding.splashActivity = this
        binding.textView.text=("Splash 나온다 ")

        //Gilde 활용해서 gif 이미지 로드
        Glide.with(this)  //현재 파라미터를 받고
            .load(R.drawable.dokdo) //이미지 로드위치
            .error(R.drawable.splashlogo) //에러날때 표시할 이미지
            .into(binding.splashTitle) // 이미지를 넣을 imageView 파라미터로


        //애니메이션 인스턴스 만들기
        var fadeOutAnimation = AnimationUtils.loadAnimation(this,R.anim.fade_out)
        splash_wrap.startAnimation(fadeOutAnimation)


        //handler -> 다른객체들이 보낸 데이터를 받고 데이터를 처리하는 객체
        //Looper.getMainLooper()) ->handler 생성할때 생서자의 인자로 전달하고싶을때 사용
        //postDelayed -> 일정시간후 일어난 일
        Handler(Looper.getMainLooper()).postDelayed({

            val intent = Intent(this,MainActivity::class.java)

            startActivity(intent)

            finish()

        },3000)
        Toast.makeText(this@SplashActivity,
            "Splash실행",Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("view ","스플래시 종료")
        Toast.makeText(this@SplashActivity,
            "Splash종료", Toast.LENGTH_SHORT).show()
    }


}


