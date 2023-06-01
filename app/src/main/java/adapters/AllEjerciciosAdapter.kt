package adapters


import android.view.*
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessroutines.R
import crud.EjerciciosDiaCRUD
import crud.MusculoCRUD
import io.realm.Realm
import io.realm.RealmList
import models.EjercicioR


class AllEjerciciosAdapter(
    private var ejercicios: MutableList<EjercicioR>,
    var listener: OnItemClickListener
) :
    RecyclerView.Adapter<AllEjerciciosAdapter.ViewHolder>() {

    var selectedItems: RealmList<EjercicioR> = RealmList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_ejercicios_a_seleccionar, parent, false)
        return ViewHolder(v, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentItem = ejercicios[position]

        holder.tvTitulo.text = currentItem.nombre
        holder.ivEjercicio.setImageResource(currentItem.imagen)

        holder.checkBox.isChecked = currentItem.isSelected
        holder.checkBox.setOnClickListener {
            Realm.getDefaultInstance().executeTransaction { realm ->
                currentItem.isSelected = !currentItem.isSelected
                holder.checkBox.isChecked = currentItem.isSelected
                if(holder.checkBox.isChecked){
                    currentItem.isSelected = true
                    selectedItems.add(currentItem)
                }else{
                    currentItem.isSelected = false
                    selectedItems.remove(currentItem)
                }
                realm.copyToRealmOrUpdate(currentItem)
                realm.copyToRealmOrUpdate(selectedItems)  // Actualizar el objeto en Realm
            }
        }

        holder.itemView.setOnClickListener {
            Realm.getDefaultInstance().executeTransaction { realm ->
                currentItem.isSelected = !currentItem.isSelected
                holder.checkBox.isChecked = currentItem.isSelected
                if(holder.checkBox.isChecked){
                    currentItem.isSelected = true
                    selectedItems.add(currentItem)
                }else{
                    currentItem.isSelected = false
                    selectedItems.remove(currentItem)
                }
                realm.copyToRealmOrUpdate(currentItem)
                realm.copyToRealmOrUpdate(selectedItems)  // Actualizar el objeto en Realm
            }
        }
    }


    fun getItemsSelected(): RealmList<EjercicioR> {
        return selectedItems
    }

    override fun getItemCount(): Int {
        return ejercicios.size
    }

    class ViewHolder(v: View, var listener: OnItemClickListener) : RecyclerView.ViewHolder(v),
        View.OnClickListener {
        var tvTitulo: TextView = v.findViewById(R.id.tvTitulo1)
        var ivEjercicio: ImageView = v.findViewById(R.id.ivEjercicio)
        var checkBox: CheckBox = v.findViewById(R.id.checkBox)

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            this.listener.OnItemClick(p0!!, layoutPosition)
        }
    }

    fun actualizarListaEjercicios(nuevaListaEjercicios: MutableList<EjercicioR>) {
        ejercicios.clear()
        ejercicios.addAll(nuevaListaEjercicios)
        notifyDataSetChanged()
    }

}
