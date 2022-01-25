package com.ccarrera.petshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ActivityLogin : AppCompatActivity() {

    private lateinit var loadingFeedback: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loadingFeedback = findViewById(R.id.login_loadingFeedbackLayout)

        //Asigno la funcionalidad a los botones
        setCreateUser()
        setLogin()
    }

    private fun setCreateUser(){
        findViewById<Button>(R.id.login_RegisterButton).setOnClickListener {

            //Navega a la activity de registro de usuario
            startActivity(Intent(this, ActivityRegister().javaClass))
        }
    }

    private fun setLogin(){
        findViewById<Button>(R.id.login_loginButton).setOnClickListener {
            val usernameInput = findViewById<EditText>(R.id.login_usernameEditText).text.toString()
            val passwordInput = findViewById<EditText>(R.id.login_passwordEditText).text.toString()

            if(usernameInput.isEmpty() || passwordInput.isEmpty()){
                Snackbar.make(it, getString(R.string.loginError_EmptyCredentials),
                    Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }

            //Muestro el mensaje de carga
            loadingFeedback.visibility = View.VISIBLE

            FirebaseAuth.getInstance().signInWithEmailAndPassword(usernameInput, passwordInput)
                .addOnCompleteListener { task->
                    if(!task.isSuccessful){
                        //Muestro error
                        Snackbar.make(it, task.exception?.message.toString(),
                            Snackbar.LENGTH_LONG).show()
                        //Oculto el mensaje de carga
                        loadingFeedback.visibility = View.GONE
                        return@addOnCompleteListener
                    }

                    onLoginSuccessfull()
                }
        }
    }

    private fun onLoginSuccessfull(){
        val database = Firebase.database.reference

        database.child(Constants.DB_PRODUCTS_ROOTNAME).get()
            .addOnSuccessListener {
                //Oculto el feedback de carga
                loadingFeedback.visibility = View.GONE
                //Guardo los datos recibidos
                if(it.hasChildren()) ProductStorage.saveProducts(it)
                //Navego a la siguiente activity
                startActivity(Intent(this, ActivityMain().javaClass))}

            .addOnFailureListener{
                //Muestro el error
                Snackbar.make(findViewById(R.id.login_layoutButtons), it.message.toString(),
                    Snackbar.LENGTH_LONG).show()
                //Oculto el feedback de carga
                loadingFeedback.visibility = View.GONE
        }
    }
}