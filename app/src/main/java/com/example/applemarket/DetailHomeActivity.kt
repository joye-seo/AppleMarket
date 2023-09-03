package com.example.applemarket

import android.os.Bundle
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.example.applemarket.data.Goods
import com.example.applemarket.databinding.ActivityDetailHomeBinding
import com.google.android.material.snackbar.Snackbar

class DetailHomeActivity : AppCompatActivity() {
    private var isClicked = false
    private lateinit var goods: Goods

    private lateinit var binding: ActivityDetailHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goods = intent?.getSerializableExtra("Data") as Goods

        clickData()
        actionBarClickListener()
        binding.actionBar.bringToFront()
//        getData()
    }

    private fun actionBarClickListener() = with(binding) {

        btnBack.setOnClickListener {
            onBackPressed()
        }
        btnMore.setOnClickListener {
            val popupMenu = PopupMenu(applicationContext, btnMore)
            menuInflater.inflate(R.menu.detail_menu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { MenuItem ->

                when (MenuItem.itemId) {
                    R.id.btn_modify -> {
//                        intent = Intent(this@DetailHomeActivity, ModifyActivity::class.java)
//                        intent.putExtra("Data", sale)
//                        startActivity(intent)
                    }

                    R.id.btn_delete -> {

                    }

                }
                false

            }
            popupMenu.show()
        }

        btnHeart.setOnClickListener {

            if (isClicked) {
                btnHeart.setImageResource(R.drawable.ic_home_heart)
                Snackbar.make(
                    it, // 현재 뷰 (하트 버튼이 클릭된 뷰)
                    "관심 목록에 취소되었습니다.", // 스낵바에 표시할 메시지
                    Snackbar.LENGTH_SHORT // 스낵바를 표시할 시간
                ).show()
                isClicked = false
            } else {
                btnHeart.setImageResource(R.drawable.ic_home_heart_click)
                // 스낵바를 생성하고 표시
                Snackbar.make(
                    it, // 현재 뷰 (하트 버튼이 클릭된 뷰)
                    "관심 목록에 추가되었습니다.", // 스낵바에 표시할 메시지
                    Snackbar.LENGTH_SHORT // 스낵바를 표시할 시간
                ).show()
                isClicked = true
            }
        }


    }


    private fun clickData() = with(binding) {
        tvAddress.text = goods.address
        tvSaleName.text = goods.title
        tvContent.text = goods.Content
        tvNickname.text = goods.seller
        tvMoney.text = goods.getFormattedMoney()
        tvTime.text = goods.getFormattedPullUp()
        ivSale.setImageResource(goods.picture)

    }

    private fun getData() {
        binding.tvSaleName.text = intent.getStringExtra("ModifyTitle")
        binding.tvMoney.text = intent.getStringExtra("ModifyMoney")
    }
}