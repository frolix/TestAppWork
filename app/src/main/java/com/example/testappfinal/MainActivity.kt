package com.example.testappfinal

//import ProductListAdapter
import com.example.testappfinal.adapter.ProductListAdapter
import android.app.Application
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import com.example.testaplication.adapter.ViewPagerAdapter
import com.example.testappfinal.fragment.ManufacturersFragment
import com.example.testappfinal.fragment.ProductsFamilyFragment
import com.example.testappfinal.fragment.ProductsFragment
import com.example.testappfinal.menuactivity.ContactsFormActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var searchView:SearchView?=null

//    lateinit var adapter: ProductListAdapter
    lateinit var adapter: ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_item_list,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_notification -> {
                return true
            }
            R.id.menu_contacts -> {
                val intent = Intent(this, ContactsFormActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_setting -> {
                return true
            }
            R.id.menu_share -> {
                return true
            }
            R.id.menu_terms -> {
                return true
            }
            R.id.menu_private_policy -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun init() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ProductsFamilyFragment(), "Product Family")
        adapter.addFragment(ProductsFragment(), "Products")
        adapter.addFragment(ManufacturersFragment(), "Manufactures")
        view_pager.adapter = adapter
        tabs.setupWithViewPager(view_pager)
    }


    override fun onBackPressed() {
        if (!searchView!!.isIconified){
            searchView!!.isIconified = true
            return
        }
        super.onBackPressed()
    }


}
