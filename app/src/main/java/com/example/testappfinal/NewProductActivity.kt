package com.example.testappfinal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_new_product.*

class NewProductActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_product)
        button_save.setOnClickListener {
            val replyIntent = Intent()

            if (TextUtils.isEmpty(edit_name_product.text) || TextUtils.isEmpty(edit_price_product.text) || TextUtils.isEmpty(
                    edit_weight_product.text
                )
            ) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val productsNameDialog: String = edit_name_product.text.toString()
                val productsPriceDialog: Double = edit_price_product.text.toString().toDouble()
                val productsWeightDialog: Double = edit_weight_product.text.toString().toDouble()

                replyIntent.putExtra("productName", productsNameDialog)
                replyIntent.putExtra("productPrice", productsPriceDialog)
                replyIntent.putExtra("productWeight", productsWeightDialog)
                setResult(Activity.RESULT_OK, replyIntent)
            }

            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}
