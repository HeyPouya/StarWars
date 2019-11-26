package ir.heydarii.starwars.features.searchname.adapter

import androidx.recyclerview.widget.DiffUtil
import ir.heydarii.starwars.pojo.CharacterSearchResult

class SearchNameDiffUtilsCallback : DiffUtil.ItemCallback<CharacterSearchResult>() {

    override fun areItemsTheSame(oldItem: CharacterSearchResult, newItem: CharacterSearchResult) = oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: CharacterSearchResult, newItem: CharacterSearchResult) = oldItem.name == newItem.name

}


