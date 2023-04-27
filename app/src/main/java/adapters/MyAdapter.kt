package adapters

import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessroutines.R
import models.Musculo


class MyAdapter(private var musculos:MutableList<Musculo>, var listener: OnItemClickListener):
                                            RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(v,listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=musculos.get(position)
        holder.tvTitulo.text=item.nombre
        holder.ivMusculo.setImageResource(item.imagen)
    }

    override fun getItemCount(): Int {
        return musculos.size
    }

    class ViewHolder(v: View, var listener: OnItemClickListener) : RecyclerView.ViewHolder(v), View.OnClickListener {
        var tvTitulo:TextView=v.findViewById(R.id.tvTitulo)
        var ivMusculo:ImageView=v.findViewById(R.id.ivMusculo)

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            this.listener.OnItemClick(p0!!,adapterPosition)
        }
    }
}
