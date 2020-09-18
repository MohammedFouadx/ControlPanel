package sim.coder.controlpanel.Activities


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_setting.*
import org.json.JSONObject
import android.content.SharedPreferences
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



class SettingActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(sim.coder.controlpanel.R.layout.activity_setting)


        // get Data from SharedPreferences

        val myPrefs = this.getSharedPreferences("idcheck", Context.MODE_WORLD_READABLE)
        val prefName = myPrefs.getString("email", "0")
        adminAccount.text=prefName


//        val extras = intent.extras
//        val account = extras?.getString("e")
//        adminAccount.text=""+account
//        Toast.makeText(this,""+account,Toast.LENGTH_LONG).show()





        addAdmin.setOnClickListener {

           etName.setVisibility(View.VISIBLE)
           etEmail.setVisibility(View.VISIBLE)
           etPassword.setVisibility(View.VISIBLE)
           btAddAdmin.setVisibility(View.VISIBLE)
           btAddAdmin.setVisibility(View.VISIBLE)
            signOut.setVisibility(View.GONE)
        }

        btAddAdmin.setOnClickListener {
            AddAdmin()
        }


        signOut.setOnClickListener {


            var shared= getSharedPreferences("idcheck",0)
            var edit=shared.edit()
            var email = shared.getString("email",null)

            edit.clear()
            edit.apply()

            var intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()

        }






    }



    ///// SignUp function to add an admin to the control panel

    fun AddAdmin() {

        var name = etName.text.toString().trim()
        var email = etEmail.text.toString().trim()
        var password = etPassword.text.toString().trim()


        if (TextUtils.isEmpty(name)) {
            etName.setError("Name is required")
        }

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email is required")
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("password is required")
        } else {


            var url = "http://192.168.1.107/RetriveDataJava/adduser.php"
            var stringRequest = object : StringRequest(Method.POST, url, Response.Listener {

                    response ->
                Toast.makeText(this, "" + response, Toast.LENGTH_SHORT).show()

                var jsonObject = JSONObject(response)

                if (jsonObject.getString("msg") == "done") {

                    var intent = Intent(Intent(this,MainActivity::class.java))
                    startActivity(intent)


                    Toast.makeText(this, "data inserted", Toast.LENGTH_SHORT).show()

                } else {


                    Toast.makeText(this, "data not inserted", Toast.LENGTH_SHORT).show()
                }


            }, Response.ErrorListener {


            }) {


                override fun getParams(): MutableMap<String, String> {


                    var params = HashMap<String, String>()

                    var n = etName.text.toString()
                    var e = etEmail.text.toString()
                    var p = etPassword.text.toString()
                    params.put("name", n)
                    params.put("email", e)
                    params.put("password", p)




                    return params
                }


            }


            var requestQueue = Volley.newRequestQueue(this)


            requestQueue.add(stringRequest)


        }

    }

    ///////////////////////////////////////////////
}

