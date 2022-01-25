package com.ccarrera.petshop

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ccarrera.petshop.adapter.ProductAdapter
import com.ccarrera.petshop.interfaces.SupportFragmentManager
import com.ccarrera.petshop.models.EnumPetType
import java.io.IOException

class FragmentProductList : Fragment() {

    private val TAG = "FragmentProducts"
    private lateinit var supportFragmentManager: SupportFragmentManager
    private lateinit var rootView: View
    private lateinit var productPetType: EnumPetType

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            supportFragmentManager = context as ActivityMain
        }catch(e: IOException){
            Log.d(TAG, "HomeFragment Error: RootActivity is on null state")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        rootView = inflater.inflate(R.layout.fragment_product_list, container, false)

        productPetType = EnumPetType.All
        val enumPetTypeString = arguments?.getString(Constants.PRODUCT_PET_PARAM)
        if(enumPetTypeString != null)
            productPetType = EnumPetType.valueOf(enumPetTypeString)

        setRecycler(productPetType)

        return rootView
    }

    private fun setRecycler(enumPetType: EnumPetType) {

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.fragmentList_RecyclerView)

        val adapter = ProductAdapter(ProductStorage.getProductsByPet(enumPetType), this.requireContext())

        adapter.setOnItemClickListener(object : ProductAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val id = ProductStorage.getProductsByPet(productPetType)[position].id
                supportFragmentManager.setFragmentDetails(id)
            }
        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this.context,2)
    }
}