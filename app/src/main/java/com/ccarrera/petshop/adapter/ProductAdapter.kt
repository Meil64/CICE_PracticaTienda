package com.ccarrera.petshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ccarrera.petshop.R
import com.ccarrera.petshop.models.SimpleProduct

class ProductAdapter(dataSet: List<SimpleProduct>, private val context: Context):
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private lateinit var mListener : onItemClickListener
    private var data = dataSet

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    class ViewHolder(view: View, listener: onItemClickListener) : RecyclerView.ViewHolder(view){

        var productImage: ImageView
        var productName: TextView
        var productPrice: TextView

        init{
            productImage = view.findViewById(R.id.itemProduct_ImageView)
            productName = view.findViewById(R.id.itemProduct_NameTextView)
            productPrice = view.findViewById(R.id.itemProduct_priceTextView)

            view.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)

        return ViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context)
            .load(data[position].image)
            .into(holder.productImage)

        holder.productName.text = data[position].name
        holder.productPrice.text = data[position].price.toString() + "€"
    }

    override fun getItemCount() = data.size
}