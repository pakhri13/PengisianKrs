package net.simplifiedcoding.ui.User

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.database.FirebaseDatabase
import net.simplifiedcoding.R

class CetaKrsAdapter(val mCtex : Context, val layoutResId : Int, val matkulList : List<CetaKrs>)
    : ArrayAdapter<CetaKrs>(mCtex, layoutResId, matkulList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtex)
        val view : View = layoutInflater.inflate(layoutResId, null)

        val matkul = view.findViewById<TextView>(R.id.txt_namamatkul3)
        val sks = view.findViewById<TextView>(R.id.txt_sks3)
        val deleted = view.findViewById<ImageView>(R.id.deletematkul)

        val listkrs = matkulList[position]

        matkul.text = listkrs.matakuliah
        sks.text = listkrs.sks

        deleted.setOnClickListener {
            deleteKrs(listkrs)
        }

        return  view
    }

    private fun deleteKrs(deleteKrs : CetaKrs) {
        val progressDialog = ProgressDialog(context,R.style.Theme_MaterialComponents_Light_Dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("Deleting...")
        progressDialog.show()
        val mydatabase = FirebaseDatabase.getInstance().getReference("cetakkrs")
        mydatabase.child(deleteKrs.id).removeValue()
        Toast.makeText(mCtex,"Deleted!!!", Toast.LENGTH_SHORT).show()
        val habisdelete = Intent(context, CetakKrsActivity::class.java)
        context.startActivity(habisdelete)
    }
}