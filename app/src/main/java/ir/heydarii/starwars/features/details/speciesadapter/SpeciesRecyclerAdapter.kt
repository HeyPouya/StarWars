package ir.heydarii.starwars.features.details.speciesadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.heydarii.starwars.databinding.SpeciesItemBinding
import ir.heydarii.starwars.pojo.SpeciesDetailsResponse

/**
 * Displays the species of a character in a recycler
 */
class SpeciesRecyclerAdapter(private val list: List<SpeciesDetailsResponse>) :
    RecyclerView.Adapter<SpeciesRecyclerAdapter.SpeciesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        val binding = SpeciesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpeciesViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) =
        holder.bind(list[position])

    /**
     * ViewHolder of the RecyclerView
     */
    class SpeciesViewHolder(private val binding: SpeciesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Sets TextViews
         */
        fun bind(speciesDetailsResponse: SpeciesDetailsResponse) = with(binding) {
            txtName.text = speciesDetailsResponse.name
            txtLanguage.text = speciesDetailsResponse.language
        }
    }
}
