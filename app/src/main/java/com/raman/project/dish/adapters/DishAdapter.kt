package com.raman.project.dish.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.raman.project.dish.R
import com.raman.project.dish.databinding.DishItemsBinding
import com.raman.project.dish.model.DishData

class DishAdapter(private val context:Context, private val dialog: Dialog):ListAdapter<DishData, DishAdapter.DishViewHolder>(DiffUtilCallBack()) {

    inner class DishViewHolder(itemView:View):ViewHolder(itemView){
        val binding:DishItemsBinding = DishItemsBinding.bind(itemView)
    }

    class DiffUtilCallBack():DiffUtil.ItemCallback<DishData>(){
        override fun areItemsTheSame(oldItem: DishData, newItem: DishData): Boolean {
            return oldItem.dishId == newItem.dishId
        }

        override fun areContentsTheSame(oldItem: DishData, newItem: DishData): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.dish_items, parent, false)
        return DishViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.apply {
            dishName.text = data.dishName
            Glide.with(context).load(data.imageUrl).placeholder(R.drawable.paneer_tikka).into(dishImg)

            item.setOnClickListener {
                dialog.createDialog()

            }
        }
    }
    interface Dialog {
        fun createDialog()
    }
}
