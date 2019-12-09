package ir.heydarii.starwars.base

import android.view.View
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import ir.heydarii.starwars.R

/**
 * All activities are child of this class
 */
open class BaseActivity : DaggerAppCompatActivity() {

    protected fun showError(view: View, errorMessage: String, handleErrors: () -> Unit) {
        Snackbar.make(view, errorMessage, Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) { handleErrors() }.show()
    }
}
