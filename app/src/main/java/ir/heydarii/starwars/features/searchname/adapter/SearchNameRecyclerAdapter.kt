package ir.heydarii.starwars.features.searchname.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.heydarii.starwars.R
import ir.heydarii.starwars.pojo.CharacterSearchResult
import kotlinx.android.synthetic.main.character_search_item.view.*

/**
 * Displays a list of characters with the letters that user entered
 */
class SearchNameRecyclerAdapter(
    nameDiffUtils: SearchCharacterDiffUtilsCallback,
    private val clickListener: (String) -> Unit
) : ListAdapter<CharacterSearchResult,
        SearchNameRecyclerAdapter.SearchNameViewHolder>(nameDiffUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchNameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_search_item, parent, false)
        return SearchNameViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchNameViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * ViewHolder of the RecyclerView
     */
    inner class SearchNameViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        /**
         * Sets TextViews and clickListeners
         */
        fun bind(characterSearchResult: CharacterSearchResult) {
            view.txtName.text = characterSearchResult.name
            view.txtBirthDate.text = view.context.getString(
                R.string.character_birth_date_is, characterSearchResult.birth_year
            )
            view.setOnClickListener { clickListener(characterSearchResult.url) }
        }
    }
}
