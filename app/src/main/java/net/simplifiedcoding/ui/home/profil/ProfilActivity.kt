package net.simplifiedcoding.ui.home.profil

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_profil.*
import kotlinx.android.synthetic.main.activity_profil.txt_nama
import kotlinx.android.synthetic.main.activity_profil.txt_npm
import kotlinx.android.synthetic.main.isi_biodata_layout.*
import net.simplifiedcoding.Biodata.Biodata
import net.simplifiedcoding.R

class ProfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        val auth = FirebaseAuth.getInstance().currentUser!!.uid

        val ref = FirebaseDatabase.getInstance().getReference("/mahasiswa").orderByChild("uid").equalTo(auth)

        val nama = findViewById<TextView>(R.id.nama)
        val npm = findViewById<TextView>(R.id.npm)
        val tgl = findViewById<TextView>(R.id.tgl)
        val img = findViewById<CircleImageView>(R.id.img)


        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    val setNama = p0.getValue(Biodata::class.java)
                    val setNpm = p0.getValue(Biodata::class.java)
                    val setTgl = p0.getValue(Biodata::class.java)
                    val setImg = p0.getValue(Biodata::class.java)

                    nama.text = setNama?.nama
                    npm.text = setNpm?.npm
                    tgl.text = setTgl?.lahir
//                    Glide.with(this@ProfilActivity).load(setImg).into(img)


//                    for (h in p0.children) {
//                        val nama = h.getValue(Biodata::class.java)?.nama
//                        val npm = h.getValue(Biodata::class.java)?.npm
//                        val lahir = h.getValue(Biodata::class.java)?.lahir
//                        val photo = h.getValue(Biodata::class.java)?.photo
//
//                        name = nama.toString()
//                        enpm = npm.toString()
//                        ttl = lahir.toString()
//                        foto = photo.toString()
//
//                    }
//
//                    txt_nama.text = name
//                    txt_npm.text = enpm
//                    txt_lahir.text = ttl
//                    Glide.with(this@ProfilActivity).load(foto).into(img_profil)

                }

            }

        })
    }
}