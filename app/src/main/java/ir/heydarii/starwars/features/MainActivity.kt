package ir.heydarii.starwars.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ir.heydarii.starwars.R

/**
 * MainActivity that behaves as a fragment holder
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /**
     * setting the content view
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
