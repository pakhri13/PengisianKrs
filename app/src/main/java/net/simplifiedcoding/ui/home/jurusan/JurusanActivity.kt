package net.simplifiedcoding.ui.home.jurusan

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_jurusan.*
import net.simplifiedcoding.R
import net.simplifiedcoding.ui.User.SistemInformasi.SistemInformasiActivity
import net.simplifiedcoding.ui.User.TeknikInformatika.TeknikInformatikaActivity
import net.simplifiedcoding.ui.auth.LoginActivity

class JurusanActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jurusan)

        val signout = FirebaseAuth.getInstance()

        val stb = AnimationUtils.loadAnimation(this, R.anim.stb)
        linearSI.startAnimation(stb)
        linearTI.startAnimation(stb)

        linearSI.setOnClickListener {
            val pindah = Intent(applicationContext, SistemInformasiActivity::class.java)
            startActivity(pindah)

        }

        linearTI.setOnClickListener {
            val pindah = Intent(applicationContext, TeknikInformatikaActivity::class.java)
            startActivity(pindah)

        }

        btnlogoutuser.setOnClickListener {
            signout.signOut()
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }
}