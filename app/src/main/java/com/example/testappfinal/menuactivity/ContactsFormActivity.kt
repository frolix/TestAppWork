package com.example.testappfinal.menuactivity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.testappfinal.R
import kotlinx.android.synthetic.main.activity_contacts_form.*

class ContactsFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts_form)
    }

    fun sendOnEmail(view: View) {
        var name = name_edit_txt.text.toString()
        var email = email_edit_txt.text.toString()
        var message = subject_edit_txt.text.toString()

        var onError = false
        if(name_edit_txt.text.isNullOrEmpty()){
            name_edit_txt.error = "Enter Your Name"
            name_edit_txt.isFocusable = true
        }
        if (subject_edit_txt.text.isNullOrEmpty()){
            subject_edit_txt.error = "Enter Your Subject"
            subject_edit_txt.isFocusable = true
        }
        if (email_edit_txt.text.isNullOrEmpty()){
            email_edit_txt.error = "Enter Your Email"
            email_edit_txt.isFocusable = true
            return
        }
        if (!isValidEmail(email)){
            email_edit_txt.error = "Invalid Email"
            email_edit_txt.isFocusable = true
            onError = true
        }


        if (!onError) {
            sendIntent(email, name, message)

        }
    }

    private fun sendIntent(email: String,name: String,message:String) {
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "text/plain"
        emailIntent.`package` = "com.google.android.gm"

        emailIntent.putExtra(Intent.EXTRA_EMAIL, Array(1){"kostya.lotysh@gmail.com"})
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Feedback")
        emailIntent.putExtra(Intent.EXTRA_TEXT, "name: $name\nEmail ID: $email\n$message")

        startActivity(Intent.createChooser(emailIntent,"Chose Email Client..."))

    }

    private fun isValidEmail(email: String):Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


}
