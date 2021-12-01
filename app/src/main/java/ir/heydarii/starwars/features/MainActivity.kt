package ir.heydarii.starwars.features

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import ir.heydarii.starwars.R
import ir.heydarii.starwars.base.BaseActivity

/**
 * MainActivity that behaves as a fragment holder
 */
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    /**
     * setting the content view
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
