package net.simplifiedcoding.Biodata

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.isi_biodata_layout.*
import net.simplifiedcoding.R
import net.simplifiedcoding.ui.home.HomeActivity
import net.simplifiedcoding.ui.home.jurusan.JurusanActivity
import java.io.IOException

class IsiBiodataActivity : AppCompatActivity(), View.OnClickListener {
    private var biodataPath: Uri? = null
    override fun onClick(v: View?) {

        when (v) {
            fab_next -> {
                val firebaseAuth = FirebaseAuth.getInstance().currentUser!!.uid
                val nama = edt_nama.text.toString()
                val npm = edt_npm.text.toString()
                val lahir = edt_lahir.text.toString()

                val storageRef = FirebaseStorage.getInstance().getReference("mahasiswa")
                val databaseRef = FirebaseDatabase.getInstance().getReference("mahasiswa")


                storageRef.putFile(biodataPath!!)
                    .addOnSuccessListener {
                        storageRef.downloadUrl.addOnSuccessListener {
                            val url = it!!.toString()
//                            val mahasiswaId = firebaseAuth
                            val isi = Biodata(firebaseAuth, nama, npm, lahir, url)


                            databaseRef.child(firebaseAuth).setValue(isi).addOnCompleteListener{
                                Toast.makeText(this, "Berhasil Menambahkan Biodata", Toast.LENGTH_SHORT).show()

                            }


                            val pindah = Intent(this,JurusanActivity::class.java)
                            startActivity(pindah)
                            finish()
                        }
                    }
                    .addOnFailureListener {
                        println("Info file : ${it.message}")
                    }
            }

            btn_image -> {
                val iPot = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(iPot,0)
            }

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.isi_biodata_layout)

        fab_next.setOnClickListener(this)
        btn_image.setOnClickListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            biodataPath = data!!.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, biodataPath)
                imgProfil.setImageBitmap(bitmap)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}