package com.example.applemarket

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.applemarket.data.Goods
import com.example.applemarket.databinding.ItemHomeSaleListBinding

class HomeAdapter(private val list: MutableList<Goods>) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemHomeSaleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(list[position])

    }

    inner class HomeViewHolder(private val binding: ItemHomeSaleListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(goods: Goods) = with(binding) {

            tvAddress.text = goods.address
            tvHomeChat.text = goods.chat.toString()
            tvHomeHeart.text = goods.like.toString()
            tvItemName.text = goods.title
            tvMoney.text = goods.getFormattedMoney()
            tvPullUp.text = goods.getFormattedPullUp()
            ivSale.setImageResource(goods.picture)


//            itemView.setOnClickListener {
//
//                val myIntent = Intent(itemView.context, DetailHomeActivity::class.java)
//                itemView.context.startActivity(myIntent)
//            }


        }
    }

}