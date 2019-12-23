package net.simplifiedcoding.ui.Admin

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.layout_admin.*
import net.simplifiedcoding.R
import net.simplifiedcoding.ui.Admin.TeknikInformatika.TeknikActivity
import net.simplifiedcoding.ui.Admin.SistemInformasi.SistemActivity
import net.simplifiedcoding.ui.auth.LoginActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class AdminActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_admin)

        val teknik = findViewById<LinearLayout>(R.id.btn_teknik)
        val sistem = findViewById<LinearLayout>(R.id.btn_sistem)

        //animation set
        val stb = AnimationUtils.loadAnimation(this, R.anim.stb)
        teknik.startAnimation(stb)
        sistem.startAnimation(stb)


        //perintah button
        btnlogoutadmin.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        teknik.setOnClickListener {
            val pindah = Intent(applicationContext, TeknikActivity::class.java)
            startActivity(pindah)
            finish()
        }
        sistem.setOnClickListener {
            val pindah2 = Intent(applicationContext, SistemActivity::class.java)
            startActivity(pindah2)
            finish()
        }
    }
}