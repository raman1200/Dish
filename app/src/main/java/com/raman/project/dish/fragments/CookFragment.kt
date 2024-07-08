package com.raman.project.dish.fragments

import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.raman.project.dish.R
import com.raman.project.dish.adapters.CategoryAdapter
import com.raman.project.dish.adapters.DishAdapter
import com.raman.project.dish.databinding.FragmentCookBinding
import com.raman.project.dish.model.CategoryData
import com.raman.project.dish.model.DishData
import com.raman.project.dish.viewModels.DishViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CookFragment : Fragment(), DishAdapter.Dialog {

    private lateinit var binding: FragmentCookBinding
    private lateinit var dishAdapter: DishAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var dialog:BottomSheetDialog
    private val viewModel: DishViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCookBinding.inflate(layoutInflater, container, false)

        initialize()
        clickListeners()
        observer()

        return binding.root
    }

    private fun clickListeners() {

    }

    private fun setAdapters(){
        categoryAdapter = CategoryAdapter(requireContext())
        dishAdapter = DishAdapter(requireContext(), this)
        binding.apply {
            categoryRecycler.adapter = categoryAdapter
            dishRecycler.adapter = dishAdapter
            val llm = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            categoryRecycler.layoutManager = llm
            val llm1 = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            dishRecycler.layoutManager = llm1
            dummyData()
        }
    }

    private fun initialize() {
        dialog = BottomSheetDialog(requireContext())
        viewModel.getDishes()
        setAdapters()
    }
    private fun observer(){
        viewModel.dishes.observe(requireActivity()) { dishes ->
            dishes?.let {
                dishAdapter.submitList(it)
            }
        }
    }

    private fun dummyData(){
        val dummyCategoryData = listOf(CategoryData(1, "Rice Items", "https://www.realsimple.com/thmb/NI2xqE0QLcUrI-jMb8y1858kPnc=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/long-grain-white-rice-gettyimages-806770568-f3c1d7887feb475783bb3e3b592066e3.jpg"),
        CategoryData(2, "Indian", "https://cdn.britannica.com/94/240094-050-D5CC461B/Indian-naan-flatbread.jpg"),
        CategoryData(3, "Soup", "https://downshiftology.com/wp-content/uploads/2023/09/Vegetable-Soup-main-500x375.jpg"),
        CategoryData(4, "Desserts", "https://www.tasteofhome.com/wp-content/uploads/2018/01/Cherry-Delight-Dessert_EXPS_TOHcom23_27515_P2_MD_03_22_4b.jpg"),
        CategoryData(5, "Curries", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlRfPiU4Yk-0OUSAqGMWPjlF_xG_9JIxdCgw&s")
        ,
        CategoryData(6, "Snacks", "https://www.tastingtable.com/img/gallery/25-most-popular-snacks-in-america-ranked-worst-to-best/intro-1645492743.jpg")
        )

        categoryAdapter.submitList(dummyCategoryData)

    }

    override fun createDialog() {
        val view = layoutInflater.inflate(R.layout.dish_item_click, null)
        dialog.setContentView(view)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.show()

        val close = view.findViewById<ImageView>(R.id.close)
        close.setOnClickListener {
            dialog.dismiss()
        }





    }


}