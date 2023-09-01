package com.example.applemarket

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.applemarket.Main.AroundFragment
import com.example.applemarket.Main.ChatFragment
import com.example.applemarket.Main.HomeFragment
import com.example.applemarket.Main.SettingFragment
import com.example.applemarket.Main.TownFragment
import com.example.applemarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var homeFragment: HomeFragment
    private lateinit var townFragment: TownFragment
    private lateinit var aroundFragment: AroundFragment
    private lateinit var chatFragment: ChatFragment
    private lateinit var settingFragment: SettingFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bottomNav()

        //기존 backPressed가 description되어 콜백인스턴스 붙여주기
        this.onBackPressedDispatcher.addCallback(this, callback)


        //아이템 클릭? 시 선택된 컬러
        binding.navMain.itemActiveIndicatorColor = null

        binding.fabWrite.setOnClickListener {
//            val intent = Intent(this, DetailHomeActivity::class.java)
//            startActivity(intent)
        }


    }

    private fun bottomNav() {

        homeFragment = HomeFragment()
        townFragment = TownFragment()
        aroundFragment = AroundFragment()
        chatFragment = ChatFragment()
        settingFragment = SettingFragment()

        binding.navMain.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.home -> changeFragment(homeFragment)
                    R.id.town -> changeFragment(townFragment)
                    R.id.around -> changeFragment(aroundFragment)
                    R.id.chat -> changeFragment(chatFragment)
                    R.id.setting -> changeFragment(settingFragment)
                }
                true
            }
            selectedItemId = R.id.home
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.frameLayout.id, fragment)
            .commit()
    }

    //콜백 인스턴스 생성
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // 뒤로 버튼 이벤트 처리
            var builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("종료")
            builder.setMessage("정말 종료하실꺼예요!??!?!?!?")
            builder.setIcon(R.drawable.ic_chat)

            val listener = DialogInterface.OnClickListener { dialog, which ->
                when (which) {

                    DialogInterface.BUTTON_POSITIVE ->
                        finish()
                }
            }
            builder.setPositiveButton("종료", listener)
            builder.setNegativeButton("취소", listener)

            builder.show()

        }
    }
}