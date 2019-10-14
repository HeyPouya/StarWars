package ir.heydarii.starwars.features.details.moviesadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.heydarii.starwars.R
import ir.heydarii.starwars.pojo.MoviesDetailsResponse
import kotlinx.android.synthetic.main.movies_item.view.*

/**
 * Displays all movies of a character in details view
 */
class MoviesRecyclerAdapter(private val list: List<MoviesDetailsResponse>) :
    RecyclerView.Adapter<MoviesRecyclerAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class MoviesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        /**
         * Sets TextViews
         */
        fun bind(speciesDetailsResponse: MoviesDetailsResponse) {
            view.txtTitle.text =
                view.context.getString(R.string.movie_title_is, speciesDetailsResponse.title)
            view.txtReleaseDate.text = view.context.getString(
                R.string.movie_release_date_is,
                speciesDetailsResponse.release_date
            )
            view.txtOpening.text = view.context.getString(
                R.string.movie_opening_is,
                speciesDetailsResponse.opening_crawl
            )
        }

    }
}