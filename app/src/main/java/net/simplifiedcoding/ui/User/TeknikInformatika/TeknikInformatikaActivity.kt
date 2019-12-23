package net.simplifiedcoding.ui.User.TeknikInformatika

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.teknik_user.*
import net.simplifiedcoding.R
import net.simplifiedcoding.ui.User.CetakKrsActivity
import net.simplifiedcoding.ui.User.MataKuliahUser
import net.simplifiedcoding.ui.User.SistemInformasi.SistemInformasiAdapter

class TeknikInformatikaActivity : AppCompatActivity() {

    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<MataKuliahUser>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teknik_user)

        fab_next1_TI.setOnClickListener {
            val pindah = Intent(applicationContext, CetakKrsActivity::class.java)
            startActivity(pindah)
        }

        ref = FirebaseDatabase.getInstance().getReference("jurusan").child("teknik")
        list = mutableListOf()
        listView = findViewById(R.id.lv_user_TI)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {
                    list.clear()

                    for (h in p0.children) {
                        val user = h.getValue(MataKuliahUser::class.java)
                        list.add(user!!)
                    }

                    val adapter = TeknikInformatikaAdapter(this@TeknikInformatikaActivity,R.layout.item_user_matkul, list)
                    listView.adapter = adapter

                }
            }
        })
    }
}