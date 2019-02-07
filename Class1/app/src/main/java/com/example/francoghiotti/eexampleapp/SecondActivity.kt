package com.example.francoghiotti.eexampleapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.text.Editable
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    var inputText: TextInputEditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        inputText = findViewById<TextInputEditText>(R.id.textToSend)
        val aceptButton = findViewById<Button>(R.id.acceptButton)
        val textView =  findViewById<TextView>(R.id.secondTextView)
        val text = intent.getStringExtra("text")

        if (text != null) {
            textView.text = "Hola " + text.toString()
        }
        
        aceptButton.setOnClickListener {
            aceptButtonAction()
        }

        cancelButton.setOnClickListener {

        }
    }

    private fun aceptButtonAction() {
        if (inputText?.text.toString() != null) {
            val intent = Intent()
            intent.putExtra("responseText", inputText!!.text.toString())
            setResult(1001, intent)
        }
        finish()
    }

    private fun cancelButtonAction() {
        val intent = Intent()
        setResult(1002, intent)
        finish()
    }
}
