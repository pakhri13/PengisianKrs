package net.simplifiedcoding.ui.User.SistemInformasi

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import net.simplifiedcoding.R
import net.simplifiedcoding.ui.Admin.MataKuliah
import net.simplifiedcoding.ui.User.MataKuliahUser

class SistemInformasiAdapter(val mCtex : Context, val layoutResId : Int, val matkulList : List<MataKuliahUser>)
    : ArrayAdapter<MataKuliahUser>(mCtex, layoutResId, matkulList) {

    val fbAuth = FirebaseAuth.getInstance()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtex)
        val view: View = layoutInflater.inflate(layoutResId, null)
        val textNamamatkul = view.findViewById<TextView>(R.id.txt_namamatkul2)
        val textnamadosen = view.findViewById<TextView>(R.id.txt_namadosen2)
        val textsks = view.findViewById<TextView>(R.id.txt_sks2)
        val pilihmatkul = view.findViewById<ImageView>(R.id.pilihmatkul)

        val Matkul = matkulList[position]


        textNamamatkul.text = Matkul.matakuliah
        textnamadosen.text = Matkul.namadosen
        textsks.text = Matkul.sks
        pilihmatkul.setOnClickListener {
            matkulPilihan(Matkul)
        }

        return view
    }

    private fun matkulPilihan(matakuliahuser : MataKuliahUser) {
        val namamatkul = matakuliahuser.matakuliah
        val namadosen = matakuliahuser.namadosen
        val skes = matakuliahuser.sks

        val mDatabase = FirebaseDatabase.getInstance().getReference("cetakkrs")
        val authUser = fbAuth.currentUser!!.uid
        val resId = mDatabase.push().key.toString()

        val cetakKrs = MataKuliahUser(authUser,resId, namamatkul, namadosen, skes)

        mDatabase.child(resId).setValue(cetakKrs).addOnSuccessListener {
            Toast.makeText(mCtex,"Add Mata Kuliah Sukses", Toast.LENGTH_SHORT).show()
        }
    }

//    private fun deletePilihan(matakuliahuser : MataKuliahUser) {
//        val progressDialog = ProgressDialog(context,R.style.Theme_MaterialComponents_Light_Dialog)
//        progressDialog.isIndeterminate = true
//        progressDialog.setMessage("Checking Matkul...")
//        progressDialog.show()
//        val mDatabase = FirebaseDatabase.getInstance().getReference("cetakkrs").child("sisteminformasi").child(matakuliahuser.id)
//        mDatabase.child(matakuliahuser.id).removeValue()
//        Toast.makeText(mCtex,"UnChecked !!", Toast.LENGTH_SHORT).show()
//        val habisdelete = Intent(context, SistemInformasiActivity::class.java)
//        context.startActivity(habisdelete)
//    }
}