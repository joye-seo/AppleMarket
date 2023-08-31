package com.example.applemarket

import android.os.Bundle
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import com.example.applemarket.data.Goods
import com.example.applemarket.databinding.ActivityDetailHomeBinding

class DetailHomeActivity : AppCompatActivity() {
    private lateinit var goods: Goods

    private lateinit var binding: ActivityDetailHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        goods = intent?.getSerializableExtra("Data") as Goods

        clickData()
        actionBarClickListener()
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