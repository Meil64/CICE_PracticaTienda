package com.ccarrera.petshop

object Constants {

    //Nodo principal de la db de Firebase
    val DB_PRODUCTS_ROOTNAME = "products"

    //Nodos de la db de Firebase
    val DB_CHILDNODE_ID ="id"
    val DB_CHILDNODE_NAME ="name"
    val DB_CHILDNODE_ANIMAL ="animal"
    val DB_CHILDNODE_TYPE ="productType"
    val DB_CHILDNODE_IMAGE ="image"
    val DB_CHILDNODE_DETAILS ="details"
    val DB_CHILDNODE_PRICE ="price"

    //Conversión a Enum
    val DB_ANIMAL_CAT = "gato"
    val DB_ANIMAL_DOG = "perro"
    val DB_PRODUCT_FOOD = "comida"
    val DB_PRODUCT_ACC = "accesorio"

    //Fragment param
    val PRODUCT_ID_PARAM = "PRODUCT_ID_PARAM"
    val PRODUCT_PET_PARAM = "PRODUCT_PET_PARAM"

}