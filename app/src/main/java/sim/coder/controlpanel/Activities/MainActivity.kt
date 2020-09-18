package sim.coder.controlpanel.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_setting.*
import sim.coder.controlpanel.Models.UsersData
import sim.coder.controlpanel.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val shared= getSharedPreferences("idcheck",0)
        val edit=shared.edit()
        val email = shared.getString("email",null)


        if (email==null){

            var intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }



        cvUsers.setOnClickListener {
            val intent = Intent(Intent(this, UsersActivity::class.java))
            startActivity(intent)
        }
        showPost.setOnClickListener {

            val intent = Intent(Intent(this, PostActivity::class.java))
            startActivity(intent)
        }

        cvSetting.setOnClickListener {

            val toSetting = Intent(Intent(this, SettingActivity::class.java))
            startActivity(toSetting)

        }


    }






}