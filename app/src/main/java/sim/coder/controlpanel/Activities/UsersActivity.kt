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
import kotlinx.android.synthetic.main.activity_users.*
import org.json.JSONArray
import sim.coder.controlpanel.Models.UsersData
import sim.coder.controlpanel.adpter.UsersAdapter
import sim.coder.controlpanel.R


class UsersActivity : AppCompatActivity() {



    var posts = ArrayList<UsersData>()
    var searhUsersList=ArrayList<UsersData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        getDatamotherfucker()

    }

    //// Retrieve users from the database ///////////////////

    fun getDatamotherfucker() {


        var url = "http://192.168.1.107/RetriveDataJava/getUsers.php"
        var stringRequset = object : StringRequest(Method.POST, url, Response.Listener {

                response ->

            Log.d("mresposnse", response)


            var data = JSONArray(response)

            for (i in 0..data.length() - 1) {
                var jsonObject = data.getJSONObject(i)

                var post = UsersData(
                    //role = jsonObject.getInt("role"),
                    id = jsonObject.getInt("id"),
                    username = jsonObject.getString("username"),
                    email = jsonObject.getString("email")
                    //password = jsonObject.getString("password")

                )

                posts.add(post)


                var costem = UsersAdapter(this!!, posts)
                rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                rv.adapter = costem


            }

        }, Response.ErrorListener {

        }) {

            override fun getParams(): MutableMap<String, String> {
                var paramas = HashMap<String, String>()
                //     var em=search.text.toString()
                //   paramas.put("email",em)

                return paramas

            }


        }
        var requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequset)

    }

    /////////////////////////////////////////////


    ///// menu + searchView from the users list ////////////////////////////


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

                searhUsersList.clear()
                for (i in posts ){

                    if (i.username.contains(txtsearch.toString())){
                        searhUsersList.add(i)

                    }
                }

                var costem = UsersAdapter(this@UsersActivity, searhUsersList)
                rv.layoutManager = LinearLayoutManager(this@UsersActivity, RecyclerView.VERTICAL, false)
                rv.adapter = costem

                return false
            }

        })
        return super.onCreateOptionsMenu(menu)


    }
}



