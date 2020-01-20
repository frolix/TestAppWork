package com.example.testappfinal.adapter

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.testappfinal.R
import com.example.testappfinal.dao.ProductDao
import com.example.testappfinal.entity.ProductEntity
import com.example.testappfinal.repository.ProductRepository
import com.example.testappfinal.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.recyclerview_item.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.collections.ArrayList

class ProductListAdapter internal constructor(
    private val productsList: List<ProductEntity>
) : RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>(), Filterable {

    private var filterListResult: List<ProductEntity>


    init {
        this.filterListResult = productsList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return filterListResult.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.onBind(filterListResult[position])
    }

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val productName = view.product_name
        private val productPrice = view.product_price
        private val productWeight = view.product_weight


        fun onBind(productEntity: ProductEntity) {
            productName.text = productEntity.productName
            productPrice.text = productEntity.productPrice.toString()
            productWeight.text = productEntity.productWeight.toString()
        }

    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val charSearch = constraint.toString()
                if (charSearch.isEmpty())
                    filterListResult = productsList
                else {
                    val resultList = ArrayList<ProductEntity>()
                    for (row in productsList) {
                        if (row.productName.toLowerCase().contains(charSearch.toLowerCase()))
                            resultList.add(row)
                    }

                    filterListResult = resultList
                }

                val filterResults = FilterResults()
                filterResults.values = filterListResult
                return filterResults

            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterListResult = results!!.values as List<ProductEntity>
                notifyDataSetChanged()
            }

        }
    }


}

