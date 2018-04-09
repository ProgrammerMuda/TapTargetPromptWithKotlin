package ru.redsys.taptargetpromptsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt
import uk.co.samuelwall.materialtaptargetprompt.extras.backgrounds.RectanglePromptBackground
import uk.co.samuelwall.materialtaptargetprompt.extras.focals.RectanglePromptFocal

const val keyShowPrompt = "didShowPrompt"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFabPrompt()
    }

    private fun showFabPrompt() {
        val prefManager = PreferenceManager.getDefaultSharedPreferences(this)

        if (!prefManager.getBoolean(keyShowPrompt, false)) {

            MaterialTapTargetPrompt.Builder(this)
                    .setTarget(floatingActionButton)
                    .setPrimaryText("Click me")
                    .setSecondaryText("I'm fab")
                    .setBackButtonDismissEnabled(true)
                    .setPromptStateChangeListener { prompt, state ->
                        if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED ||
                                state == MaterialTapTargetPrompt.STATE_NON_FOCAL_PRESSED) {

                            prefManager.edit()
                                    .putBoolean(keyShowPrompt, true)
                                    .apply()

                            showButtonPrompt()
                        }
                    }
                    .show()
        }
    }

    private fun showButtonPrompt() {

        MaterialTapTargetPrompt.Builder(this)
                .setTarget(button)
                .setPrimaryText("Press me")
                .setBackButtonDismissEnabled(true)
                .setPromptBackground(RectanglePromptBackground())
                .setPromptFocal(RectanglePromptFocal())
                .show()
    }
}
