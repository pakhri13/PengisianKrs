package net.simplifiedcoding.ui.Admin.TeknikInformatika

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import net.simplifiedcoding.R
import net.simplifiedcoding.ui.Admin.MataKuliah

class TeknikAdapter(val mCtex : Context, val layoutResId : Int, val matkulList : List<MataKuliah>)
    : ArrayAdapter<MataKuliah>(mCtex, layoutResId, matkulList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtex)
        val view : View  = layoutInflater.inflate(layoutResId, null)
        val textNamamatkul = view.findViewById<TextView>(R.id.txt_namamatkul1)
        val textnamadosen = view.findViewById<TextView>(R.id.txt_namadosen1)
        val textsks = view.findViewById<TextView>(R.id.txt_sks1)
        val hapus = view.findViewById<ImageView>(R.id.hapus)

        val matkul = matkulList[position]

        textNamamatkul.text = matkul.matakuliah
        textnamadosen.text = matkul.namadosen
        textsks.text = matkul.sks



        view.setOnClickListener {
            show_updateDialog(matkul)
        }

        hapus.setOnClickListener{
            show_deleteDialog(matkul)
        }

        return view
    }

    //ubah data
    fun show_updateDialog(matkul:MataKuliah){
        val builder = AlertDialog.Builder(mCtex)
        builder.setTitle("Update Mata kuliah")

        val inflater = LayoutInflater.from(mCtex)

        val view = inflater.inflate(R.layout.update_matakuliah, null)

        val edtMatkul = view.findViewById<EditText>(R.id.edt_matakuliah)
        val edtDosen = view.findViewById<EditText>(R.id.edt_nama_dosen)
        val edtSks = view.findViewById<EditText>(R.id.edt_sks)

        edtMatkul.setText(matkul.matakuliah)
        edtDosen.setText(matkul.namadosen)
        edtSks.setText(matkul.sks)

        builder.setView(view)

        //mengaktifkan perintah positif alert
        builder.setPositiveButton("Ubah") { p0, p1 ->
            val databaseRef = FirebaseDatabase.getInstance().getReference("jurusan").child("teknik")
            val matakul = edtMatkul.text.toString().trim()
            val dosen = edtDosen.text.toString().trim()
            val sks = edtSks.text.toString().trim()

            if (matakul.isEmpty()) {
                edtMatkul.error = "Isikan matakuliah dengan benar"
                edtMatkul.requestFocus()
                return@setPositiveButton
            }
            if (dosen.isEmpty()) {
                edtDosen.error = "Isikan nama dosen dengan benar"
                edtDosen.requestFocus()
                return@setPositiveButton
            }
            if (sks.isEmpty()) {
                edtSks.error = "Isikan jumlah sks dengan benar"
                edtSks.requestFocus()
                return@setPositiveButton
            }

            val matakuliah = MataKuliah(matkul.id, matakul, dosen, sks)

            databaseRef.child(matkul.id).setValue(matakuliah)

            Toast.makeText(mCtex,"Data berhasil diubah", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Batal") { p0, p1 ->

        }
        val alert = builder.create();
        alert.show()
    }


    //method hapus data matakuliah
    fun show_deleteDialog(matkul: MataKuliah){

        val progressDialog = ProgressDialog(context,R.style.Theme_MaterialComponents_Light_Dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Proses hapus data...")
        progressDialog.show()

        val databaseRef = FirebaseDatabase.getInstance().getReference("jurusan").child("teknik")

        databaseRef.child(matkul.id).removeValue()
        Toast.makeText(mCtex,"Hapus matakuliah berhsil",Toast.LENGTH_SHORT).show()
        val hapus = Intent(context,TeknikActivity::class.java)
        context.startActivity(hapus)
    }

}