package com.example.patriots;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.patriots.dummy.PatriotsPlayerContent;

import static com.example.patriots.PlayerListActivity.dbHandler;

/**
 * A fragment representing a single Item detail screen.
 */
public class PlayerDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private PatriotsPlayerContent.PatriotsPlayer mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PlayerDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments.

            String temp = getArguments().getString((PlayerDetailFragment.ARG_ITEM_ID));

            System.out.println("");

            mItem = dbHandler.getPlayer(getArguments().getString(PlayerDetailFragment.ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        // Show the patriots player as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText("Number: " + mItem.getNumber()
                    + '\n' + "Position: " + mItem.getPosition()
                    + '\n' + "Age: " + mItem.getAge()
                    + '\n' + "College: " + mItem.getCollege());
        }

        return rootView;
    }
}
