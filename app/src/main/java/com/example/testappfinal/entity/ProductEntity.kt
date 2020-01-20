package com.example.testappfinal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
class ProductEntity(@PrimaryKey
        @ColumnInfo(name = "product_name_ent")
        var productName: String = "",
        @ColumnInfo(name = "product_price_ent")
        var productPrice: Double = 0.0,
        @ColumnInfo(name = "product_weight_ent")
        var productWeight: Double = 0.0
)
