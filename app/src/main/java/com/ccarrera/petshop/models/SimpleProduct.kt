package com.ccarrera.petshop.models

import com.ccarrera.petshop.Constants

data class SimpleProduct(
    var id: Int,
    var name: String,
    var animal: EnumPetType,
    var productType: EnumProductType,
    var image: String,
    var price: Float
){
    companion object{
        fun createFromProduct(product: Product): SimpleProduct {

            return SimpleProduct(
                id = product.id,
                name = product.name,
                animal = animalStringToEnum(product.animal),
                productType = typeStringToEnum(product.productType),
                image = product.image,
                price = product.price
            )
        }

        private fun animalStringToEnum(animalString : String) : EnumPetType{
            if(animalString == Constants.DB_ANIMAL_DOG)
                return EnumPetType.Dog
            else if(animalString == Constants.DB_ANIMAL_CAT)
                return EnumPetType.Cat
            return EnumPetType.All
        }

        private fun typeStringToEnum(typeString : String) : EnumProductType{
            if(typeString == Constants.DB_PRODUCT_FOOD)
                return EnumProductType.Food
            else if(typeString == Constants.DB_PRODUCT_ACC)
                return EnumProductType.Accessories
            return EnumProductType.All
        }
    }
}
