package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random

class DieFragment : Fragment() {

    private val ROLL_KEY = "current_roll"
    private lateinit var dieTextView: TextView

    private var currentRoll = 1
    private var dieSides: Int = 6

    companion object {
        const val DIESIDE = "sidenumber"

        fun newInstance(sides: Int = 6): DieFragment {
            return DieFragment().apply {
                arguments = Bundle().apply {
                    putInt(DIESIDE, sides)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            dieSides = it.getInt(DIESIDE)
        }

        savedInstanceState?.let {
            currentRoll = it.getInt(ROLL_KEY, 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dieTextView.text = currentRoll.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ROLL_KEY, currentRoll)
    }

    fun throwDie() {
        currentRoll = Random.nextInt(1, dieSides + 1)
        dieTextView.text = currentRoll.toString()
    }
}