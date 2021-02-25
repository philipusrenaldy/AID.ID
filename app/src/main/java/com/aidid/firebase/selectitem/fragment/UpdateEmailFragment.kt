package com.aidid.firebase.selectitem.fragment

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.aidid.firebase.R
import com.aidid.firebase.selectitem.fragment.UpdateEmailFragmentDirections
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.android.synthetic.main.fragment_update_email.*
import kotlinx.android.synthetic.main.fragment_update_email.etEmail
import kotlinx.android.synthetic.main.fragment_update_email.etPassword

class UpdateEmailFragment : Fragment() {
    private lateinit var  auth :FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_update_email, container, false)
    }
    override fun  onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        layoutPassword.visibility = View.VISIBLE
        layoutEmail.visibility = View.GONE

        btnAuth.setOnClickListener {
            val password = etPassword.text.toString().trim()
            if (password.isEmpty()) {
                etPassword.error = "Please Enter Your Password"
                etPassword.requestFocus()
                return@setOnClickListener
            }//!!  digunakan karena email sudah pasti ada
            user?.let {
                val userCredential = EmailAuthProvider.getCredential(it.email!!, password)
                it.reauthenticate(userCredential).addOnCompleteListener {
                    if (it.isSuccessful) {
                        layoutPassword.visibility = View.GONE
                        layoutEmail.visibility = View.VISIBLE
                    } else if (it.exception is FirebaseAuthInvalidCredentialsException) {
                        etPassword.error = "Wrong Password"
                        etPassword.requestFocus()
                    } else { //bila tidak ada koneksi dll
                        Toast.makeText(activity, "${it.exception?.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
            btnUpdate.setOnClickListener{view -> it
                val email = etEmail.text.toString().trim()
                if (email.isEmpty()) {
                    etEmail.error = "Please Fill Your Email Address"
                    etEmail.requestFocus()
                    return@setOnClickListener
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmail.error = "Your Email is Not Valid"
                    etEmail.requestFocus()
                    return@setOnClickListener
                }
                //check current user
                user?.let {
                    user.updateEmail(email).addOnCompleteListener {
                        if (it.isSuccessful){
                            val actionEmaillUpdated = UpdateEmailFragmentDirections.actionEmailUpdated()
                            Navigation.findNavController(view).navigate(actionEmaillUpdated)
                        }else{
                            Toast.makeText(activity, "${it.exception?.message}", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }
}