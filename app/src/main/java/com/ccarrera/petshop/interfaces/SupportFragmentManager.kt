package com.ccarrera.petshop.interfaces

import com.ccarrera.petshop.models.EnumPetType

interface SupportFragmentManager {
    fun setFragmentList(enumPetType: EnumPetType)
    fun setFragmentDetails(idProduct: Int = 0)
}