package ir.heydarii.starwars.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ir.heydarii.starwars.R

/**
 * All fragments are child of this class
 */
open class BaseFragment : Fragment() {

   protected fun showError(view: View, errorMessage: String, handleErrors: () -> Unit) {
        Snackbar.make(view, errorMessage, Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) { handleErrors() }.show()
    }
}
