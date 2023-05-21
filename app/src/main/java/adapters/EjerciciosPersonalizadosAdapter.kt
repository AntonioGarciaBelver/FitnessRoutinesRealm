package adapters

import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessroutines.R
import models.EjercicioR


class EjerciciosPersonalizadosAdapter(private var ejercicios:MutableList<EjercicioR>, var listener: OnItemClickListener):
    RecyclerView.Adapter<EjerciciosPersonalizadosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_ejercicios, parent, false)
        return ViewHolder(v,listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=ejercicios.get(position)
        holder.tvTitulo.text= item.nombre
        holder.ivEjercicio.setImageResource(item.imagen)
    }

    override fun getItemCount(): Int {
        return ejercicios.size
    }

    class ViewHolder(v: View, var listener: OnItemClickListener) : RecyclerView.ViewHolder(v), View.OnClickListener {
        var tvTitulo:TextView=v.findViewById(R.id.tvTitulo1)
        var ivEjercicio:ImageView=v.findViewById(R.id.ivEjercicio)

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            this.listener.OnItemClick(p0!!,layoutPosition)
        }
    }
}
