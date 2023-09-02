package com.example.applemarket.adapter

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.DetailHomeActivity
import com.example.applemarket.Main.HomeFragment
import com.example.applemarket.R
import com.example.applemarket.data.Goods
import com.example.applemarket.databinding.ItemHomeSaleListBinding

class HomeAdapter(private val list: MutableList<Goods>, private val context:Context) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

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


            itemView.setOnClickListener {

                val myIntent = Intent(itemView.context, DetailHomeActivity::class.java)
                myIntent.putExtra("Data",goods)
                itemView.context.startActivity(myIntent)
            }

            itemView.setOnLongClickListener {
                var builder = AlertDialog.Builder(context)
                builder.setTitle("삭제")
                builder.setMessage("정말 삭제하실껀가여>!?!?!?!?!")
                builder.setIcon(R.drawable.ic_chat)

                val listener = DialogInterface.OnClickListener { dialog, which ->
                    when (which) {

                        DialogInterface.BUTTON_POSITIVE -> removeItem(position)
                    }
                }
                builder.setPositiveButton("삭제", listener)
                builder.setNegativeButton("취소", listener)

                builder.show()
                true
            }

        }
    }
    fun removeItem(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }
}