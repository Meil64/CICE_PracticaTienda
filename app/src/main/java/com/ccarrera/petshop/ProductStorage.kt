package com.ccarrera.petshop

import com.ccarrera.petshop.models.EnumPetType
import com.ccarrera.petshop.models.EnumProductType
import com.ccarrera.petshop.models.Product
import com.ccarrera.petshop.models.SimpleProduct
import com.google.firebase.database.DataSnapshot

object ProductStorage {

    lateinit var productCollection: List<Product>
    lateinit var simpleProductCollection: List<SimpleProduct>

    fun saveProducts(data : DataSnapshot){

        val products = mutableListOf<Product>()
        val simpleProducts = mutableListOf<SimpleProduct>()

        data.children.forEach {
            val product = Product(
                id = it.child(Constants.DB_CHILDNODE_ID).value.toString().toInt(),
                name = it.child(Constants.DB_CHILDNODE_NAME).value.toString(),
                animal = it.child(Constants.DB_CHILDNODE_ANIMAL).value.toString(),
                productType = it.child(Constants.DB_CHILDNODE_TYPE).value.toString(),
                image = it.child(Constants.DB_CHILDNODE_IMAGE).value.toString(),
                details = it.child(Constants.DB_CHILDNODE_DETAILS).value.toString(),
                price = it.child(Constants.DB_CHILDNODE_PRICE).value.toString().toFloat()
            )

            products.add(product)
            simpleProducts.add(SimpleProduct.createFromProduct(product))
        }

        productCollection = products
        simpleProductCollection = simpleProducts
    }

    fun getProductById(id: Int): Product {
        return productCollection.first { product -> product.id == id }
    }

    fun getProductsByPet(enumPetType: EnumPetType) : List<SimpleProduct>{
        return simpleProductCollection.filter {
                simpleProduct -> simpleProduct.animal == enumPetType }
    }
}