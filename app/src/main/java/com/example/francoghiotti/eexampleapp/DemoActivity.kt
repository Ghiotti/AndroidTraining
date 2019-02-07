package com.example.francoghiotti.eexampleapp

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AlertDialog
import android.text.TextWatcher
import android.widget.Button
import android.widget.TextView
import com.example.francoghiotti.eexampleapp.R.*
import com.example.francoghiotti.eexampleapp.R.layout.*
import kotlinx.android.synthetic.main.activity_demo.view.*
import java.nio.file.Files

class DemoActivity : AppCompatActivity() {

    var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        val button = findViewById<Button>(R.id.exampleButton)
        val textField = findViewById<TextInputEditText>(R.id.exampleInput)
        textView = findViewById(R.id.responseTextView)
        val takePhotoButton = findViewById<Button>(R.id.takePhotoButton)

        takePhotoButton.setOnClickListener {
            dispatchTakePictureIntent()
        }

        button.setOnClickListener {
            if (textField.text.toString().isNullOrEmpty().not()) {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("text", textField.text.toString())
                startActivityForResult(intent, 1)
            } else {
                genericPopUp("Debe ingresar su nombre!", "aceptar")
            }
        }
    }

    private fun genericPopUp(mensaje: String, boton: String) {
        val errorAlert = AlertDialog.Builder(this)
        errorAlert.setMessage(mensaje)
        errorAlert.setPositiveButton(boton, null)
        errorAlert.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
       when (requestCode) {
           1 ->
               when (resultCode) {
                   1001 ->
                       if (data != null) {
                           textView!!.text = data.getStringExtra("responseText").toString()
                       }

                   1001 ->
                       textView!!.text = "El usuario ha cancelado la operación"
               }
           2 ->
               if (resultCode == RESULT_OK) {
                   genericPopUp("La foto se ha tomado exitosamente", "Aceptar")
               }
       }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, 2)
            }
        }
    }
}
