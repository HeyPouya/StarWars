package ir.heydarii.starwars.features.details.filmsadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.heydarii.starwars.R
import ir.heydarii.starwars.pojo.FilmsDetailsResponse
import kotlinx.android.synthetic.main.films_item.view.*

class FilmsRecyclerAdapter(private val list: List<FilmsDetailsResponse>) :
    RecyclerView.Adapter<FilmsRecyclerAdapter.SpeciesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.films_item, parent, false)
        return SpeciesViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        holder.bind(list[position])
    }


    class SpeciesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(speciesDetailsResponse: FilmsDetailsResponse) {
            view.txtTitle.text =
                view.context.getString(R.string.film_title_is, speciesDetailsResponse.title)
            view.txtReleaseDate.text = view.context.getString(
                R.string.film_release_date_is,
                speciesDetailsResponse.release_date
            )
            view.txtOpening.text = view.context.getString(
                R.string.film_opening_is,
                speciesDetailsResponse.opening_crawl
            )
        }

    }
}