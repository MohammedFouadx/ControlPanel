package sim.coder.controlpanel.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.android.synthetic.main.activity_users.*
import org.json.JSONArray
import org.json.JSONObject
import sim.coder.controlpanel.Models.PostData
import sim.coder.controlpanel.Models.UsersData
import sim.coder.controlpanel.R
import sim.coder.controlpanel.adpter.PostAdapter
import sim.coder.controlpanel.adpter.UsersAdapter

class PostActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        getPostData()

    }

    fun getPostData(){

        var posts=ArrayList<PostData>()
        //var searchnewslist=ArrayList<Post>()

        var url="http://192.168.1.105/RetriveDataJava/getPost.php"
        //var url="http://192.168.43.191/getElect.php"
        var stringRequset=object : StringRequest(Method.POST,url, Response.Listener {

                response ->

            Log.d("mresposnse",response)


            var data= JSONArray(response)

            for (i in 0..data.length()-1){
                var jsonObject=data.getJSONObject(i)

                var post2= PostData(

                    id=jsonObject.getInt("p_id"),
                    title = jsonObject.getString("title"),
                    description = jsonObject.getString("descirption"),
                    status = jsonObject.getString("status")

                )
                posts.add(post2)




                var costem = PostAdapter(this!!, posts)
                rvPost.layoutManager= LinearLayoutManager(this, RecyclerView.VERTICAL,false)
                rvPost.adapter=costem





            }

        }, Response.ErrorListener {

        }){

            override fun getParams(): MutableMap<String, String> {
                var paramas=HashMap<String,String>()
                //     var em=search.text.toString()
                //   paramas.put("email",em)

                return  paramas

            }


        }
        var requestQueue= Volley.newRequestQueue(this)
        requestQueue.add(stringRequset)

    }








}
