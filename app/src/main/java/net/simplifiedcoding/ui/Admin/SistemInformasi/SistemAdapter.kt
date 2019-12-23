package net.simplifiedcoding.ui.Admin.SistemInformasi

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.FirebaseDatabase
import net.simplifiedcoding.R
import net.simplifiedcoding.ui.Admin.MataKuliah


class SistemAdapter(val mCtex: Context, val layoutResId: Int, val matkulList: MutableList<MataKuliah>)
    : ArrayAdapter<MataKuliah>(mCtex, layoutResId, matkulList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtex)
        val view : View = layoutInflater.inflate(layoutResId, null)
        val textNamamatkul = view.findViewById<TextView>(R.id.txt_namamatkul1)
        val textnamadosen = view.findViewById<TextView>(R.id.txt_namadosen1)
        val textsks = view.findViewById<TextView>(R.id.txt_sks1)
        val hapus = view.findViewById<ImageView>(R.id.hapus)

        val Matkul = matkulList[position]

        textNamamatkul.text = Matkul.matakuliah
        textnamadosen.text = Matkul.namadosen
        textsks.text = Matkul.sks


        view.setOnClickListener {
            show_updateDialog(Matkul)
        }

        hapus.setOnClickListener{
            show_deleteDialog(Matkul)
        }

        return view
    }


    //ubah data
    fun show_updateDialog(mataKuliah: MataKuliah){
        val builder = AlertDialog.Builder(mCtex)
        builder.setTitle("Update Mata kuliah")

        val inflater = LayoutInflater.from(mCtex)

        val view = inflater.inflate(R.layout.update_matakuliah, null)

        val edtMatkul = view.findViewById<EditText>(R.id.edt_matakuliah)
        val edtDosen = view.findViewById<EditText>(R.id.edt_nama_dosen)
        val edtSks = view.findViewById<EditText>(R.id.edt_sks)

        edtMatkul.setText(mataKuliah.matakuliah)
        edtDosen.setText(mataKuliah.namadosen)
        edtSks.setText(mataKuliah.sks)

        builder.setView(view)


        //mengaktifkan perintah positif alert
        builder.setPositiveButton("Ubah") { p0, p1 ->
            val databaseRef = FirebaseDatabase.getInstance().getReference("jurusan").child("sistem")
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

            val matakuliah = MataKuliah(mataKuliah.id, matakul, dosen, sks)

            databaseRef.child(mataKuliah.id).setValue(matakuliah)

            Toast.makeText(mCtex,"Data berhasil diubah", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("Batal") { p0, p1 ->

        }
        val alert = builder.create();
        alert.show()
    }

    //hapus data
    fun show_deleteDialog(mataKuliah: MataKuliah){

        val progressDialog = ProgressDialog(context,R.style.Theme_MaterialComponents_Light_Dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Proses hapus data...")
        progressDialog.show()

        val databaseRef = FirebaseDatabase.getInstance().getReference("jurusan").child("sistem")

        databaseRef.child(mataKuliah.id).removeValue()
        Toast.makeText(mCtex,"Hapus matakuliah berhsil",Toast.LENGTH_SHORT).show()
        val hapus = Intent(context, SistemActivity::class.java)
        context.startActivity(hapus)
    }

}