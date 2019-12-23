package net.simplifiedcoding.ui.User

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.layout_semester.*
import net.simplifiedcoding.R
import net.simplifiedcoding.ui.User.TeknikInformatika.TeknikInformatikaAdapter
import net.simplifiedcoding.ui.auth.LoginActivity
import net.simplifiedcoding.ui.home.HomeActivity

class CetakKrsActivity : AppCompatActivity() {

    val fbAuth = FirebaseAuth.getInstance()

    lateinit var list : MutableList<CetaKrs>
    lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_semester)

        fab_cetak.setOnClickListener {

        }

        val akunPesan = fbAuth.currentUser!!.uid

        val ref = FirebaseDatabase.getInstance().getReference("cetakkrs").orderByChild("uid").equalTo(akunPesan)
        list = mutableListOf()
        listView = findViewById(R.id.lv_semster)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {
                    list.clear()

                    for (h in p0.children) {
                        val user = h.getValue(CetaKrs::class.java)
                        list.add(user!!)
                    }

                    val adapter = CetaKrsAdapter(this@CetakKrsActivity,R.layout.item_semester, list)
                    listView.adapter = adapter

                }
            }
        })

        radio_group.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                Toast.makeText(applicationContext," On checked change : ${radio.text}",
                    Toast.LENGTH_SHORT).show()
            })

        // Get radio group selected status and text using button click event
        fab_cetak.setOnClickListener{
            // Get the checked radio button id from radio group
            var id: Int = radio_group.checkedRadioButtonId
            if (id!=-1){ // If any radio button checked from radio group
                // Get the instance of radio button using id
                val radio:RadioButton = findViewById(id)
                Toast.makeText(applicationContext,"Anda Semester : ${radio.text}",
                    Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("semester", radio.text)
                startActivity(intent)
                finish()
            }else{
                // If no radio button checked in this radio group
                Toast.makeText(applicationContext,"On button click : Silahkan Pilih Semesternya",
                    Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun radio_button_click(view: View){
        // Get the clicked radio button instance
        val radio: RadioButton = findViewById(radio_group.checkedRadioButtonId)
        Toast.makeText(applicationContext,"On click : ${radio.text}",
            Toast.LENGTH_SHORT).show()
    }
}