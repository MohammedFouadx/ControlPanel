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
import kotlinx.android.synthetic.main.posts.view.*
import org.json.JSONObject
import sim.coder.controlpanel.Models.PostData
import sim.coder.controlpanel.R


class PostAdapter:RecyclerView.Adapter<PostAdapter.mViewHolder> {
    var context: Context?=null
    var postslist=ArrayList<PostData>()

    constructor(context: Context,mNewslist:ArrayList<PostData>){
        this.postslist=mNewslist
        this.context=context

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mViewHolder {

        return mViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.posts, parent, false
            )
        )
    }
    override fun getItemCount(): Int {
        return postslist.size
    }
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: mViewHolder, position: Int) {

        holder.idPost.text=postslist.get(position).id.toString()
        holder.title.text=postslist.get(position).title
        holder.desc.text=postslist.get(position).description
       holder.status.text=postslist.get(position).status

        holder.imageTrash.setOnClickListener {

            val urls = "http://192.168.1.105/RetriveDataJava/deletePost.php"

            var stringRequest= object : StringRequest(Method.POST,urls, Response.Listener {

                    response ->
                Toast.makeText(context,""+response, Toast.LENGTH_SHORT).show()

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

                    var id=holder.idPost.text.toString()
                    params.put("p_id",id)




                    return params
                }



            }


            var requestQueue= Volley.newRequestQueue(context)


            requestQueue.add(stringRequest)



        }




    }

    class mViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        var idPost=view.idPost
        var title=view.postTitle
        var desc=view.postDiscerption
        var status=view.postStatus
        var imageTrash=view.ivTrashPost
        var imageDesable=view.ivDisablePost
        //var image=view.postImage

    }

}
