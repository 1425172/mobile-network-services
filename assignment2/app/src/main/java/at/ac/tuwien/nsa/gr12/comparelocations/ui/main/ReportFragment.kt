package at.ac.tuwien.nsa.gr12.comparelocations.ui.main

import androidx.fragment.app.Fragment
import at.ac.tuwien.nsa.gr12.comparelocations.core.model.Report

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [ReportFragment.OnListFragmentInteractionListener] interface.
 */
class ReportFragment : Fragment() {

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Report?)
    }
}
