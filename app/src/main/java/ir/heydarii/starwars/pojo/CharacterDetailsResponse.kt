package ir.heydarii.starwars.pojo

data class CharacterDetailsResponse(
        val birth_year: String,
        val created: String,
        val edited: String,
        val eye_color: String,
        val films: List<String>,
        val gender: String,
        val hair_color: String,
        val height: String,
        val homeworld: String,
        val mass: String,
        val name: String,
        val skin_color: String,
        val species: List<String>,
        val starships: List<Any>,
        val url: String,
        val vehicles: List<Any>
)