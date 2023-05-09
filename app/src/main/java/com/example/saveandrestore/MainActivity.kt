package com.example.saveandrestore

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val discountButton: Button
        get() = findViewById(R.id.discount_button)

    private val firstName: EditText
        get() = findViewById(R.id.first_name)

    private val lastName: EditText
        get() = findViewById(R.id.last_name)

    private val email: EditText
        get() = findViewById(R.id.email)

    private val discountCodeConf: TextView
        get() = findViewById(R.id.discount_code_confirmation)

    private val discountCode: TextView
        get() = findViewById(R.id.discount_code)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate")

        discountButton.setOnClickListener {
            if (firstName.text.isNotEmpty() && lastName.text.isNotEmpty() && email.text.isNotEmpty()) {
                val fullName = "${firstName.text} ${lastName.text}"
                discountCodeConf.text = getString(R.string.discount_code_confirmation, fullName)
                discountCode.text = "FUHFWGYG"
            } else {
                Toast.makeText(this, "Please complete all fields", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "restore state")
        discountCodeConf.text = savedInstanceState.getString(DISCOUNT_CONF_MESSAGE, "")
        discountCode.text = savedInstanceState.getString(DISCOUNT_CODE, "")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "saved state")

        outState.putString(DISCOUNT_CONF_MESSAGE, discountCodeConf.text.toString())
        outState.putString(DISCOUNT_CODE, discountCode.text.toString())
    }



    companion object {
        private const val TAG = "Bem"
        private const val DISCOUNT_CODE = "DISCOUNT CODE"
        private const val DISCOUNT_CONF_MESSAGE = "DISCOUNT_CONF_MESSAGE"
    }
}
