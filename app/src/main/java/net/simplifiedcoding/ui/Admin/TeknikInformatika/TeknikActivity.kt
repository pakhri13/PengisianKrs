package net.simplifiedcoding.ui.Admin.TeknikInformatika

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.admin_toolbar.*
import kotlinx.android.synthetic.main.teknik_admin.*
import net.simplifiedcoding.R
import net.simplifiedcoding.ui.Admin.AdminActivity
import net.simplifiedcoding.ui.Admin.MataKuliah

class TeknikActivity : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    lateinit var list : MutableList<MataKuliah>
    lateinit var listView: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teknik_admin)

        logogundar.setOnClickListener {
            val home = Intent(this, AdminActivity::class.java)
            startActivity(home)
            finish()
        }


        fab_add_1.setOnClickListener {
            show_adddialog()
        }



        //referensi
        ref = FirebaseDatabase.getInstance().getReference("jurusan").child("teknik")
        list = mutableListOf()
        listView = findViewById(R.id.lv_semester1) as ListView


        //menampilkan data
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {
                    list.clear()
                    for (h in p0.children) {
                        val user = h.getValue(MataKuliah::class.java)
                        list.add(user!!)
                    }

                    val adapter = TeknikAdapter(this@TeknikActivity, R.layout.item_content_matkul, list)
                    listView.adapter = adapter
                }
            }
        })
    }

    //menambahkan data
    fun show_adddialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Tambah Mata Kuliah")
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.add_matakuliah, null)

        val edt_namamatkul = view.findViewById<EditText>(R.id.edt_matakuliah)
        val edt_namadosen = view.findViewById<EditText>(R.id.edt_nama_dosen)
        val edt_sks = view.findViewById<EditText>(R.id.edt_sks)

        builder.setView(view)

        builder.setPositiveButton("Tambah") { p0, p1 ->
            val dbmatkul1 = FirebaseDatabase.getInstance().getReference("jurusan").child("teknik")
            val nama = edt_namamatkul.text.toString().trim()
            val dosen = edt_namadosen.text.toString().trim()
            val sks = edt_sks.text.toString().trim()

            if (nama.isEmpty()) {
                edt_namamatkul.error = "Dimohon untuk mengisi form nama matakukliah"
                edt_namamatkul.requestFocus()
                return@setPositiveButton
            }
            if (dosen.isEmpty()) {
                edt_namadosen.error = "Dimohon untuk mengisi form nama dosen"
                edt_namadosen.requestFocus()
                return@setPositiveButton
            }
            if (sks.isEmpty()) {
                edt_sks.error = "Dimohon untuk mengisi form SKS"
                edt_sks.requestFocus()
                return@setPositiveButton
            }

            val matkulId = dbmatkul1.push().key.toString()
            val matakuliah = MataKuliah(matkulId,nama, dosen, sks)
            dbmatkul1.child(matakuliah.id).setValue(matakuliah)

            Toast.makeText(this,"Menambahkan Mata Kuliah Sukses", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("Batal") { p0, p1 ->

        }
        val alert = builder.create();
        alert.show()
    }
}