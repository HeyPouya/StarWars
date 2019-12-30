package ir.heydarii.starwars.features.details.speciesadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.heydarii.starwars.R
import ir.heydarii.starwars.pojo.SpeciesDetailsResponse
import kotlinx.android.synthetic.main.species_item.view.*

/**
 * Displays the species of a character in a recycler
 */
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

    /**
     * ViewHolder of the RecyclerView
     */
    class SpeciesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        /**
         * Sets TextViews
         */
        fun bind(speciesDetailsResponse: SpeciesDetailsResponse) {
            view.txtName.text = speciesDetailsResponse.name
            view.txtLanguage.text = speciesDetailsResponse.language
        }
    }
}
