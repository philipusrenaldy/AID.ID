package com.aidid.firebase.selectitem.fragment

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.aidid.firebase.selectitem.fragment.ProfileFragmentDirections
import com.aidid.firebase.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_profile1.*
import java.io.ByteArrayOutputStream

class ProfileFragment : Fragment() {
    private lateinit var imageUri: Uri
    private lateinit var auth: FirebaseAuth

    companion object {
        const val REQUEST_CAMERA = 100
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        if (user != null) {
            if (user.photoUrl != null) {
                Picasso.get().load(user.photoUrl).into(ivProfile1)
            } else {
                Picasso.get().load("https://picsum.photos/316/200").into(ivProfile1)
            }
            etName.setText(user.displayName)
            etEmail.setText(user.email)
            if (user.isEmailVerified) {
                icVerified.visibility = View.VISIBLE
            } else {
                icUnverified.visibility = View.VISIBLE
            }
        }

        ivProfile1.setOnClickListener {
            intentCamera()
        }
        btnUpdate.setOnClickListener {
            val image = when { //kondisi jika sudah diupload yang lelalui icon camera
                ::imageUri.isInitialized -> imageUri
                //kondisi 2 bila tidak mengupload apa apa maka akan diambil photo yang di link
                user?.photoUrl == null -> Uri.parse("https://picsum.photos/316/200")
                //kondisi 3 bila photo uda di akun maka akan dipanggil user photo url
                else -> user.photoUrl
            }
            //validation if name is empty
            val name = etName.text.toString().trim()
            if (name.isEmpty()) {
                etName.error = "Please Enter Your Name"
                etName.requestFocus()
                return@setOnClickListener
            }
            //user profile yang akan diupload ke firebase
            UserProfileChangeRequest.Builder()
                    .setDisplayName(name) //nama yang diimput
                    .setPhotoUri(image)//uri image yang ditentukan
            //foto yang disimpan hanya url saja, jadi foto bisa diupload ke  firebase storage
                    .build().also{
                        user?.updateProfile(it)?.addOnCompleteListener{
                            if (it.isSuccessful) {
                                Toast.makeText(activity, "Profile Updated", Toast.LENGTH_SHORT).show()
                            }else{ //jika gagal
                                Toast.makeText(activity, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
        }//ketika email belum diverifikasi muncul icon unverified
        icUnverified.setOnClickListener{
            user?.sendEmailVerification()?.addOnCompleteListener{
                if (it.isSuccessful){
                Toast.makeText(activity, "Email Verfication has been Sent", Toast.LENGTH_SHORT).show()
                }//jika gagal ditampilkan pesan error lewat toast
                else{
                Toast.makeText(activity, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
        //when you click edit email it will redirect to edit email page
        etEmail.setOnClickListener{
            val actionUpdateEmail = ProfileFragmentDirections.actionUpdateEmail()
            Navigation.findNavController(it).navigate(actionUpdateEmail)
            //ketika di etemail diklik akan pindah ke update email fragment
        }
        //press change password to switch to change password page
        tvChangePassword.setOnClickListener{
            val  actionChangePassword = ProfileFragmentDirections.actionChangePassword()
            Navigation.findNavController(it).navigate(actionChangePassword)
        }
    }
    @SuppressLint("QueryPermissionsNeeded")
    private fun intentCamera(){
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {intent ->
            activity?.packageManager?.let {
                intent.resolveActivity(it).also {
                    startActivityForResult(intent, REQUEST_CAMERA)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK){
            val imgBitmap = data?.extras?.get("data") as Bitmap
            uploadImage(imgBitmap)
        }
    }

    private fun uploadImage(imgBitmap: Bitmap) {
        val baos = ByteArrayOutputStream()
        val ref = FirebaseStorage.getInstance().reference.child("img/${FirebaseAuth.getInstance().currentUser?.uid}")

        imgBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val image = baos.toByteArray()

        ref.putBytes(image)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        ref.downloadUrl.addOnCompleteListener {
                            it.result?.let {
                                imageUri = it
                                ivProfile1.setImageBitmap(imgBitmap)
                            }
                        }
                    }
                }
    }
}