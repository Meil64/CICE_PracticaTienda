package com.ccarrera.petshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class ActivityRegister : AppCompatActivity() {

    private lateinit var loadingFeedback: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        loadingFeedback = findViewById(R.id.register_loadingFeedbackLayout)

        setButtonCreate()
    }

    private fun setButtonCreate(){
        findViewById<Button>(R.id.register_Button).setOnClickListener {
            val inputEmail = findViewById<EditText>(R.id.register_usernameEditText).text.toString()
            val inputPwd1 = findViewById<EditText>(R.id.register_passwordEditText).text.toString()
            val inputPwd2 = findViewById<EditText>(R.id.register_confirmpwdEditText).text.toString()

            //Compruebo que haya datos introducidos
            if(inputEmail.isEmpty() || inputPwd1.isEmpty() || inputPwd2.isEmpty()){
                Snackbar.make(it, R.string.registerError_EmptyCredentials, Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }

            //Compruebo que los passwords coincidan
            if(inputPwd1 != inputPwd2){
                Snackbar.make(it, R.string.registerError_passwordMismatch, Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }

            //Muestro el mensaje de carga
            loadingFeedback.visibility = View.VISIBLE

            //Creo el usuario
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                inputEmail,
                inputPwd1)
                .addOnCompleteListener { task->

                    //Oculto el mensaje de carga
                    loadingFeedback.visibility = View.GONE

                    if(!task.isSuccessful){
                        Snackbar.make(it, task.exception?.message.toString(),
                            Snackbar.LENGTH_LONG).show()
                        return@addOnCompleteListener
                    }

                    Toast.makeText(this, R.string.register_UserCreated, Toast.LENGTH_LONG).show()
                    finish()
                }
        }
    }
}