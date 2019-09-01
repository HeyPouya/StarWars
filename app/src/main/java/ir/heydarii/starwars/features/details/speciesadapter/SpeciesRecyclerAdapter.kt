package ir.heydarii.starwars.features.details.speciesadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.heydarii.starwars.R
import ir.heydarii.starwars.pojo.SpeciesDetailsResponse
import kotlinx.android.synthetic.main.species_item.view.*

class SpeciesRecyclerAdapter(private val list: List<SpeciesDetailsResponse>) :
    RecyclerView.Adapter<SpeciesRecyclerAdapter.SpeciesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.species_item, parent, false)
        return SpeciesViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        holder.bind(list[position])
    }


    class SpeciesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(speciesDetailsResponse: SpeciesDetailsResponse) {
            view.txtName.text = view.context.getString(
                R.string.character_species_name_is,
                speciesDetailsResponse.name
            )
            view.txtLanguage.text =
                view.context.getString(R.string.planet_language_is, speciesDetailsResponse.language)
        }

    }
}