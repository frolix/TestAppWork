package com.example.testappfinal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.testappfinal.database.ProductsDB
import com.example.testappfinal.entity.ProductEntity
import com.example.testappfinal.repository.ProductRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProductRepository

    val allProducts: LiveData<List<ProductEntity>>

//    val getAll: List<ProductEntity>

    init {
        val productDao = ProductsDB.getDatabase(application,viewModelScope).productDao()
        repository = ProductRepository(productDao)
        allProducts = repository.allProducts
//        getAll = repository.getAll
    }

//    fun getAll():List<ProductEntity> = viewModelScope.launch{
//        return repository.getAll()
//    }



    fun insert(productEntity: ProductEntity) = viewModelScope.launch {
        repository.insert(productEntity)
    }

    fun getAll()=runBlocking {
        return@runBlocking repository.getAll()
    }

}

