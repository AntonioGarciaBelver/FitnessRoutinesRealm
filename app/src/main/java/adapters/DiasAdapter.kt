package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessroutines.R
import models.EjerciciosDia

class DiasAdapter(private var dias:MutableList<EjerciciosDia>, var listener: OnItemClickListener):
    RecyclerView.Adapter<DiasAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_dias, parent, false)
        return ViewHolder(v,listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=dias.get(position)
        holder.tvTituloDia.text= item.nombre

    }

    override fun getItemCount(): Int {
        return dias.size
    }

    class ViewHolder(v: View, var listener: OnItemClickListener) : RecyclerView.ViewHolder(v), View.OnClickListener {
        var tvTituloDia: TextView =v.findViewById(R.id.tvNombreDia)

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            this.listener.OnItemClick(p0!!,layoutPosition)
        }
    }

}