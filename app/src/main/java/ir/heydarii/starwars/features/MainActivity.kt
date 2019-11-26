package ir.heydarii.starwars.features

import android.os.Bundle
import androidx.navigation.Navigation
import ir.heydarii.starwars.R
import ir.heydarii.starwars.base.BaseActivity

class MainActivity : BaseActivity() {

    /**
     * setting the content view
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * Providing onBackPressed strategy
     */
    override fun onBackPressed() {
        val isNavigatedUp = Navigation.findNavController(this, R.id.mainFragmentHolder).navigateUp()

        if (!isNavigatedUp)
            super.onBackPressed()
    }
}
