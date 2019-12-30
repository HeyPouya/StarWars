package ir.heydarii.starwars.features.searchname.adapter

import androidx.recyclerview.widget.DiffUtil
import ir.heydarii.starwars.pojo.CharacterSearchResult

/**
 * Determines if there is a difference between 2 items or not in the recyclerView
 */
class SearchCharacterDiffUtilsCallback : DiffUtil.ItemCallback<CharacterSearchResult>() {

    override fun areItemsTheSame(
        oldItem: CharacterSearchResult,
        newItem: CharacterSearchResult
    ) = oldItem.name == newItem.name

    override fun areContentsTheSame(
        oldItem: CharacterSearchResult,
        newItem: CharacterSearchResult
    ) = oldItem.name == newItem.name
}
