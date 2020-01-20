package com.example.testappfinal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testappfinal.dao.ProductDao
import com.example.testappfinal.entity.ProductEntity
import kotlinx.coroutines.CoroutineScope


@Database(entities = [(ProductEntity::class)], version = 1, exportSchema = false)
abstract class ProductsDB : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: ProductsDB? = null

        fun getDatabase(
                context: Context,
                scope: CoroutineScope
        ): ProductsDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE
                    ?: synchronized(this) {
                        val instance = Room.databaseBuilder(
                                context.applicationContext,
                                ProductsDB::class.java,
                                "products_database"
                        )
                                .fallbackToDestructiveMigration()
                                .build()
                        INSTANCE = instance
                        // return instance
                        instance
                    }
        }
    }
}