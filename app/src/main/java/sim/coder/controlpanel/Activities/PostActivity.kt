package sim.coder.controlpanel.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_post.*
import org.json.JSONArray
import sim.coder.controlpanel.Models.PostData
import sim.coder.controlpanel.R
import sim.coder.controlpanel.adpter.PostAdapter

class PostActivity : AppCompatActivity() {


    var posts = ArrayList<PostData>()
    var searhPostList=ArrayList<PostData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        getPostData()

    }


    /// Retrieve posts from database //////

    fun getPostData(){


        var url="http://192.168.1.107/RetriveDataJava/getPost.php"
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
                    description = jsonObject.getString("description"),
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


    ////////////////////////////////////////////////////




    ///// menu + searchView from the post list ////////////////////////////

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        val search = menu?.findItem(R.id.search)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Search"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(txtsearch: String?): Boolean {

                searhPostList.clear()
                for (i in posts ){

                    if (i.title.contains(txtsearch.toString())){
                        searhPostList.add(i)

                    }
                }

                var costem = PostAdapter(this@PostActivity, searhPostList)
                rvPost.layoutManager = LinearLayoutManager(this@PostActivity, RecyclerView.VERTICAL, false)
                rvPost.adapter = costem

                return false
            }

        })
        return super.onCreateOptionsMenu(menu)


    }








}
