package ir.heydarii.starwars.features.details.moviesadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.heydarii.starwars.R
import ir.heydarii.starwars.databinding.MoviesItemBinding
import ir.heydarii.starwars.pojo.MoviesDetailsResponse

/**
 * Displays all movies of a character in details view
 */
class MoviesRecyclerAdapter(private val list: List<MoviesDetailsResponse>) :
    RecyclerView.Adapter<MoviesRecyclerAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = MoviesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) =
        holder.bind(list[position])

    /**
     * ViewHolder of the RecyclerView
     */
    class MoviesViewHolder(private val binding: MoviesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Sets TextViews
         */
        fun bind(speciesDetailsResponse: MoviesDetailsResponse) = with(binding) {
            txtTitle.text =
                root.context.getString(
                    R.string.movie_title_is,
                    speciesDetailsResponse.title
                )
            txtReleaseDate.text = root.context.getString(
                R.string.movie_release_date_is,
                speciesDetailsResponse.release_date
            )
            txtOpening.text = root.context.getString(
                R.string.movie_opening_is,
                speciesDetailsResponse.opening_crawl
            )
        }
    }
}
