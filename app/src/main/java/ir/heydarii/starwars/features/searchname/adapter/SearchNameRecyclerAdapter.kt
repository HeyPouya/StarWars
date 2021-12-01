package ir.heydarii.starwars.features.searchname.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.heydarii.starwars.R
import ir.heydarii.starwars.databinding.CharacterSearchItemBinding
import ir.heydarii.starwars.pojo.CharacterSearchResult

/**
 * Displays a list of characters with the letters that user entered
 */
class SearchNameRecyclerAdapter(
    nameDiffUtils: SearchCharacterDiffUtilsCallback,
    private val clickListener: (String) -> Unit
) : ListAdapter<CharacterSearchResult,
        SearchNameRecyclerAdapter.SearchNameViewHolder>(nameDiffUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchNameViewHolder {
        val binding =
            CharacterSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchNameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchNameViewHolder, position: Int) =
        holder.bind(getItem(position))

    /**
     * ViewHolder of the RecyclerView
     */
    inner class SearchNameViewHolder(private val binding: CharacterSearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Sets TextViews and clickListeners
         */
        fun bind(characterSearchResult: CharacterSearchResult) = with(binding) {
            txtName.text = characterSearchResult.name
            txtBirthDate.text = root.context.getString(
                R.string.character_birth_date_is, characterSearchResult.birth_year
            )
            root.setOnClickListener { clickListener(characterSearchResult.url) }
        }
    }
}

/**
 * Determines if there is a difference between 2 items or not in the recyclerView
 */
class SearchCharacterDiffUtilsCallback : DiffUtil.ItemCallback<CharacterSearchResult>() {

    override fun areItemsTheSame(
        oldItem: CharacterSearchResult,
        newItem: CharacterSearchResult
    ) = oldItem.url == newItem.url

    override fun areContentsTheSame(
        oldItem: CharacterSearchResult,
        newItem: CharacterSearchResult
    ) = oldItem.name == newItem.name && oldItem.birth_year == newItem.birth_year
}

