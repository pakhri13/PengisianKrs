package net.simplifiedcoding.utils

import android.content.Context
import android.content.Intent
import net.simplifiedcoding.Biodata.IsiBiodataActivity
import net.simplifiedcoding.ui.Admin.AdminActivity
import net.simplifiedcoding.ui.auth.LoginActivity
import net.simplifiedcoding.ui.home.HomeActivity
import net.simplifiedcoding.ui.home.jurusan.JurusanActivity
import net.simplifiedcoding.ui.home.profil.ProfilActivity


fun Context.startIsiBiodataActivity() =
    Intent(this, IsiBiodataActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
fun Context.startHomeActivity() =
    Intent(this, HomeActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.startLoginActivity() =
    Intent(this, LoginActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.startAdminActivity() =
    Intent(this, AdminActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.startProfilActivity() =
    Intent(this, ProfilActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }

fun Context.startJurusanActivity() =
    Intent(this, JurusanActivity::class.java).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }