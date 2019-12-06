package at.ac.tuwien.nsa.gr12.comparelocations.ui.main

import androidx.fragment.app.Fragment
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [ReportFragment.OnListFragmentInteractionListener] interface.
 */
class ReportFragment : Fragment() {
    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Report?)
    }
}
