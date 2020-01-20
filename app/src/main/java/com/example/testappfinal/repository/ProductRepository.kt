package com.example.testappfinal.repository

import androidx.lifecycle.LiveData
import com.example.testappfinal.dao.ProductDao
import com.example.testappfinal.entity.ProductEntity

class ProductRepository (private val productDao: ProductDao) {

//    val allProduct: List<ProductEntity> = productDao.getAllProducts()

//
//    suspend fun getAllProducts(): ArrayList<ProductEntity> {
//        val allProduct: ArrayList<ProductEntity> = productDao.getAllProducts()
//        return allProduct
//    }

    suspend fun getAll():List<ProductEntity>{
        return productDao.getAllProducts()
    }
//    val getAll: List<ProductEntity> = productDao.getAllProducts()

    val allProducts: LiveData<List<ProductEntity>> = productDao.getListAllProducts()

    suspend fun insert(productEntity: ProductEntity){
        productDao.insertProduct(productEntity)
    }

}