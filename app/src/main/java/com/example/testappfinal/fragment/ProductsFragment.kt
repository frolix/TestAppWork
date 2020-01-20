package com.example.testappfinal.fragment


import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.AutoCompleteTextView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testappfinal.NewProductActivity

import com.example.testappfinal.R
import com.example.testappfinal.adapter.ProductListAdapter
import com.example.testappfinal.entity.ProductEntity
import com.example.testappfinal.menuactivity.ContactsFormActivity
import com.example.testappfinal.viewmodel.ProductViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_splash_screen.view.*
import kotlinx.android.synthetic.main.fragment_products.*

/**
 * A simple [Fragment] subclass.
 */
class ProductsFragment : Fragment(), SearchView.OnQueryTextListener {
    private val newWordActivityRequestCode = 1
    private lateinit var productViewModel: ProductViewModel
    private val productList: MutableList<ProductEntity> = ArrayList()
    private lateinit var adapter: ProductListAdapter
    var searchView: SearchView? = null


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)

        useProductList()

        view?.findViewById<FloatingActionButton>(R.id.fab)?.setOnClickListener {
            val intent = Intent(activity, NewProductActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }

    }


    private fun useProductList() {
        adapter = ProductListAdapter(productList)
        recyclerview?.adapter = adapter
        recyclerview?.layoutManager = LinearLayoutManager(context?.applicationContext)
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        productViewModel.allProducts.observe(viewLifecycleOwner, Observer { products ->
            products?.let {
                if (productList.isNotEmpty())
                    productList.clear()
                productList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_products, container, false)
        return rootView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra("productName")?.let {
                val name = ProductEntity(
                    it,
                    intentData.getDoubleExtra("productPrice", 1.1),
                    intentData.getDoubleExtra("productWeight", 1.1)
                )
                productViewModel.insert(name)
                Unit
            }

        } else {
            Toast.makeText(
                context?.applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView!!.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onPause() {
        super.onPause()
        searchView?.setQuery("",true)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        adapter.filter.filter(newText)
        return true
    }
}
