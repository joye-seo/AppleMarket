package com.example.applemarket

import android.os.Bundle
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

        //아이템 클릭? 시 선택된 컬러
        binding.navMain.itemActiveIndicatorColor = null


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
}