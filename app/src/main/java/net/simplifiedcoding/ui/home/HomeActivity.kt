package net.simplifiedcoding.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_user.linearprofile
import net.simplifiedcoding.R
import net.simplifiedcoding.databinding.ActivityHomeBinding
import net.simplifiedcoding.ui.home.jurusan.JurusanActivity
import net.simplifiedcoding.ui.home.profil.ProfilActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class HomeActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory : HomeViewModelFactory by instance()

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
        binding.viewmodel = viewModel


        //animation set
        val stb = AnimationUtils.loadAnimation(this, R.anim.stb)
        linearprofile.startAnimation(stb)
        linearjurusan.startAnimation(stb)

        linearprofile.setOnClickListener {
            val pindah = Intent(applicationContext, ProfilActivity::class.java)
            startActivity(pindah)

        }

        linearjurusan.setOnClickListener {
            val pindah = Intent(applicationContext, JurusanActivity::class.java)
            startActivity(pindah)

        }

    }

}
