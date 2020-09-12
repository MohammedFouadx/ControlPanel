package sim.coder.controlpanel.adpter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.list_layout.view.*
import org.json.JSONObject
import sim.coder.controlpanel.R
import sim.coder.controlpanel.Models.UsersData


class UsersAdapter:RecyclerView.Adapter<UsersAdapter.mViewHolder> {
    var context: Context?=null
    var postslist=ArrayList<UsersData>()

    constructor(context: Context,mNewslist:ArrayList<UsersData>){
        this.postslist=mNewslist
        this.context=context

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {

        return mViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.list_layout, parent, false
            )
        )
    }
    override fun getItemCount(): Int {
        return postslist.size
    }
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: mViewHolder, position: Int) {
        // holder?.idpost.text=postslist.get(position).id.toString()
        holder.idUser.text= postslist.get(position).id.toString()
        holder.nameUser.text=postslist.get(position).username
        holder.emailUser.text=postslist.get(position).email
       //holder.passwordUser.text=postslist.get(position).password

        holder.imageTrash.setOnClickListener {

            val urls = "http://192.168.1.105/RetriveDataJava/delete.php"

            var stringRequest= object : StringRequest(Method.POST,urls, Response.Listener {

                    response ->
                  Toast.makeText(context,""+response,Toast.LENGTH_SHORT).show()

                var jsonObject= JSONObject(response)

                if (jsonObject.getString("msg")=="done"){




                    Toast.makeText(context,"data deleted", Toast.LENGTH_SHORT).show()

                }
                else{


                    Toast.makeText(context,"data not deleted", Toast.LENGTH_SHORT).show()
                }






            }, Response.ErrorListener {



            }) {


                override fun getParams(): MutableMap<String, String> {


                    var params=HashMap<String,String>()

                    var id=holder.idUser.text.toString()
                    params.put("id",id)




                    return params
                }



            }


            var requestQueue= Volley.newRequestQueue(context)


            requestQueue.add(stringRequest)







        }


    }

    class mViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        var idUser = view.idUser
        var nameUser=view.tvUserName
        var emailUser=view.tvUserEmail
        //var passwordUser=view.tvUserPassword
        var imageTrash= view.ivTrash
        var imageDesableuser= view.ivDisable

    }





}
