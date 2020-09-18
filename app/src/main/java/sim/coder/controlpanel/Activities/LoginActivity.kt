package sim.coder.controlpanel.Activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import sim.coder.controlpanel.R

class LoginActivity : AppCompatActivity() {



    // onCreate Function //////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        btLogin.setOnClickListener {

            val email = adminEmail.text.toString()

            val shared= getSharedPreferences("idcheck",0)
            val edit=shared.edit()
            edit.putString("email",email)
            edit.commit()


            Login()
        }


    }

///////////////////////////////////////////////////



    // Login implemention ///////////////////


    fun Login(){


        var adminemail = adminEmail.text.toString()
        var adminpass = adminPassword.text.toString().trim()
        //val checked:Boolean=checkBox.isChecked

        if (TextUtils.isEmpty(adminemail)){
            adminEmail.setError("please fill the field")
        }
        if (TextUtils.isEmpty(adminpass)){
            adminPassword.setError("please fill the field")
        }
        else{

            var url="http://192.168.1.107/RetriveDataJava/getlogin.php"
            var stringRequset=object : StringRequest(Method.POST,url, Response.Listener {



                    response ->

                var jsonobject= JSONObject(response)

                if (jsonobject.getString("msg")=="Welcome"){


                    var intent = Intent(this@LoginActivity , MainActivity::class.java)
                    startActivity(intent)
                    finish()

                    Toast.makeText(this,"welcome admin", Toast.LENGTH_SHORT).show()

                } else{

                    Toast.makeText(this,"you are not an admin", Toast.LENGTH_SHORT).show()}



            }, Response.ErrorListener {



            }){


                override fun getParams(): MutableMap<String, String> {

                    var paramas=HashMap<String,String>()

                    var em=adminEmail.text.toString()
                    var pass=adminPassword.text.toString()

                    paramas.put("email",em)
                    paramas.put("pass",pass)



                    return paramas


                }





            }

            var requestQueue= Volley.newRequestQueue(this)

            requestQueue.add(stringRequset)


        }
        }





    }




