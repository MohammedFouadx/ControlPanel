package sim.coder.controlpanel.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import sim.coder.controlpanel.Models.UsersData
import sim.coder.controlpanel.R

class MainActivity : AppCompatActivity() {

    var posts = ArrayList<UsersData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        cvUsers.setOnClickListener {
            var intent = Intent(Intent(this, UsersActivity::class.java))
            startActivity(intent)
        }
        showPost.setOnClickListener {

            var intent = Intent(Intent(this, PostActivity::class.java))
            startActivity(intent)
        }




    }




}