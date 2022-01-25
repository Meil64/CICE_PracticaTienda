package com.ccarrera.petshop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class FragmentProductDetails : Fragment() {

    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        rootView = inflater.inflate(R.layout.fragment_product_details, container, false)

        val productId = arguments?.getInt(Constants.PRODUCT_ID_PARAM)
        if(productId != null){

            val product = ProductStorage.getProductById(productId)

            //Cabecera nombre + precio
            rootView.findViewById<TextView>(R.id.details_titleTextView)
                .text =  "${product.name} - ${product.price}€"

            //Imagen
            Glide.with(this.requireContext())
                .load(product.image)
                .into(rootView.findViewById<ImageView>(R.id.details_imageView))

            //Detalles
            rootView.findViewById<TextView>(R.id.details_detailsTextView)
                .text = product.details.replace("  ", "\n\n")
        }

        return rootView
    }
}