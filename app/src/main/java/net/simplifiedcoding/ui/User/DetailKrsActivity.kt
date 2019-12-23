package net.simplifiedcoding.ui.User

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.detail_krs.*
import net.simplifiedcoding.R

class DetailKrsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_krs)


        val teserah = intent.extras.getString("semester")

        txt_semester.setText(teserah)
    }
}