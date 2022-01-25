package com.ccarrera.petshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import com.ccarrera.petshop.interfaces.SupportFragmentManager
import com.ccarrera.petshop.models.EnumPetType
import com.google.android.material.bottomnavigation.BottomNavigationView

class ActivityMain : AppCompatActivity(), SupportFragmentManager {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragmentList(EnumPetType.Dog)
        setBottomNavigationView()
    }

    private fun setBottomNavigationView(){
        findViewById<BottomNavigationView>(R.id.main_bottomNavigationView).
            setOnItemSelectedListener { itemSelected ->
                when(itemSelected.itemId){
                    R.id.nav_item_dogs ->{
                        setFragmentList(EnumPetType.Dog)
                    }
                    R.id.nav_item_cats ->{
                        setFragmentList(EnumPetType.Cat)
                    }
                }
            return@setOnItemSelectedListener true
        }
    }

    override fun setFragmentList(enumPetType: EnumPetType) {

        val fragment = FragmentProductList()
        val bundle = bundleOf()
        bundle.putString(Constants.PRODUCT_PET_PARAM, enumPetType.toString())
        fragment.arguments = bundle
        supportFragmentManager.commit {
            replace(R.id.main_fragmentContainer, fragment, fragment.tag)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun setFragmentDetails(idProduct: Int) {

        val fragment = FragmentProductDetails()
        val bundle = bundleOf()
        bundle.putInt(Constants.PRODUCT_ID_PARAM, idProduct)
        fragment.arguments = bundle
        supportFragmentManager.commit {
            replace(R.id.main_fragmentContainer, fragment, fragment.tag)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}