package com.raman.project.dish.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.raman.project.dish.R
import com.raman.project.dish.databinding.CategoryItemsBinding
import com.raman.project.dish.model.CategoryData

class CategoryAdapter(private val context: Context): ListAdapter<CategoryData,CategoryAdapter.CategoryViewHolder>(DiffUtilCallBack()) {

    inner class CategoryViewHolder(itemView: View):ViewHolder(itemView){
        val binding = CategoryItemsBinding.bind(itemView)
    }

    class DiffUtilCallBack(): DiffUtil.ItemCallback<CategoryData>() {
        override fun areItemsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
            return oldItem.id == newItem.id
        }


        override fun areContentsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = CategoryItemsBinding.inflate(LayoutInflater.from(context), parent, false)
        return CategoryViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.apply {
            categoryName.text = data.name
            Glide.with(context).load(data.img).placeholder(R.drawable.paneer_tikka).into(categoryImg)
        }
    }
}