package com.school.jakub.trainingplanmaker.controller.atlas;

import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.adapters.ExerciseAdapter;
import com.school.jakub.trainingplanmaker.controller.utils.NavDrawer;
import com.school.jakub.trainingplanmaker.model.Exercise;
import com.school.jakub.trainingplanmaker.services.TrainingService;

public class AtlasActivity extends NavDrawer {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.atlas_activity);


        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.atlas_activity, null, false);
        mDrawerLayout.addView(contentView, 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.atlas_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Nogi");


        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();



        //setSupportActionBar(toolbar);



        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_atlas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.atlas_action_legs) {
            mViewPager.setCurrentItem(0);
            getSupportActionBar().setTitle("Nogi");
        }
        if (id == R.id.atlas_action_abs) {
            mViewPager.setCurrentItem(1);
            getSupportActionBar().setTitle("Brzuch");
        }

        if (id == R.id.atlas_action_chest) {
            mViewPager.setCurrentItem(2);
            getSupportActionBar().setTitle("Klatka piersiowa");
        }
        if (id == R.id.atlas_action_back) {
            mViewPager.setCurrentItem(3);
            getSupportActionBar().setTitle("Plecy");
        }
        if (id == R.id.atlas_action_biceps) {
            mViewPager.setCurrentItem(4);
            getSupportActionBar().setTitle("Biceps");
        }
        if (id == R.id.atlas_action_triceps) {
            mViewPager.setCurrentItem(5);
            getSupportActionBar().setTitle("Triceps");
        }
        if (id == R.id.atlas_action_shoulders) {
            mViewPager.setCurrentItem(6);
            getSupportActionBar().setTitle("Barki");
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        final TrainingService service = new TrainingService();
        ListAdapter adapter;
        ListView exercisesList;

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        private void updateListView(){
            adapter = new ExerciseAdapter(getContext(), service, getArguments().getInt(ARG_SECTION_NUMBER));
            exercisesList.setAdapter(adapter);
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final LayoutInflater inflater1 = inflater;
            View rootView = inflater.inflate(R.layout.atlas_fragment, container, false);
            final Context context = getContext();

            adapter = new ExerciseAdapter(getContext(), service, getArguments().getInt(ARG_SECTION_NUMBER));
            
            
            //ListAdapter listAdapter = new ExerciseAdapter(getContext(), service);

            exercisesList = (ListView) rootView.findViewById(R.id.atlas_fragment_exercises);
            exercisesList.setAdapter(adapter);

            exercisesList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    final Exercise exercise = (Exercise) adapterView.getItemAtPosition(i);
                    new AlertDialog.Builder(adapterView.getContext())
                            .setMessage("Czy na pewno chcesz usunąć ćwiczenie?")
                            .setCancelable(false)
                            .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    service.removeExercise(exercise);
                                    updateListView();
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("Nie", null)
                            .show();
                    return true;
                }
            });

            Button addBtn = (Button) rootView.findViewById(R.id.atlas_fragment_addBtn);
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
                    final View mView = inflater1.inflate(R.layout.backpack_activity_new_popup, null);

                    mBuilder.setView(mView);
                    final AlertDialog dialog  = mBuilder.create();

                    Button buttonOK = (Button) mView.findViewById(R.id.backpack_edit_activity_popup_OK);
                    Button buttonCancel = (Button) mView.findViewById(R.id.backpack_edit_activity_popup_Cancel);
                    final EditText etName = (EditText) mView.findViewById(R.id.backpack_edit_activity_popup_textInputLayout_editText);
                    etName.setHint("Nazwa ćwiczenia");

                    buttonOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(!service.checkIfExerciseExists(etName.getText().toString())) {
                                service.addNewExercise(etName.getText().toString(), service.getMuscleGroupName(getArguments().getInt(ARG_SECTION_NUMBER)));
                                updateListView();
                                dialog.cancel();
                            }else{
                                Snackbar snackbar = Snackbar
                                        .make(view, "Nazwa jest zajęta", Snackbar.LENGTH_SHORT);
                                snackbar.show();
                            }
                        }
                    });

                    buttonCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });

//                mBuilder.setView(mView);
//                AlertDialog dialog  = mBuilder.create();
                    dialog.show();
                }
            });

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 7;
        }
    }
}
