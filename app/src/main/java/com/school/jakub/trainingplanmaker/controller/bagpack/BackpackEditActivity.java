package com.school.jakub.trainingplanmaker.controller.bagpack;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.school.jakub.trainingplanmaker.R;
import com.school.jakub.trainingplanmaker.adapters.BackpackItemEditAdapter;
import com.school.jakub.trainingplanmaker.model.Item;
import com.school.jakub.trainingplanmaker.services.BackpackService;

public class BackpackEditActivity extends AppCompatActivity {

    private EditText etName;
    private TextInputLayout inputLayoutName;
    final private BackpackService service = new BackpackService();
    String backpackName;
    ListAdapter listAdapter;
    ListView itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.backpack_edit_activity);

        Intent i = getIntent();
        etName = (EditText) findViewById(R.id.backpack_edit_activity_backpack_name);
        inputLayoutName = (TextInputLayout) findViewById(R.id.backpack_activity_edit_layoutInput);
        backpackName = i.getStringExtra("backpack_name");
        etName.setText(backpackName);
        etName.addTextChangedListener(new MyTextWatcher(etName));

        Toolbar toolbar = (Toolbar) findViewById(R.id.backpack_checklist_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Edycja plecaka");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listAdapter = new BackpackItemEditAdapter(this, service, backpackName);
        itemList = (ListView) findViewById(R.id.backpack_edit_listView);
        itemList.setAdapter(listAdapter);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(BackpackEditActivity.this);
                final View mView = getLayoutInflater().inflate(R.layout.backpack_edit_activity_new_popup, null);

                mBuilder.setView(mView);
                final AlertDialog dialog  = mBuilder.create();

                Button buttonOK = (Button) mView.findViewById(R.id.backpack_edit_activity_popup_OK);
                Button buttonCancel = (Button) mView.findViewById(R.id.backpack_edit_activity_popup_Cancel);
                final EditText etName = (EditText) mView.findViewById(R.id.backpack_edit_activity_popup_textInputLayout_editText);
//                final TextInputLayout textInputLayout = (TextInputLayout) mView.findViewById(R.id.backpack_activity_edit_layoutInput);
//                textInputLayout.setHint("sad");
                final Item item =(Item)adapterView.getItemAtPosition(i);
                etName.setText(item.getName());
                buttonOK.setText("Zapisz");

                buttonOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!service.checkIfBackpackContainsItem(backpackName, etName.getText().toString())) {
                            service.changeItemName(backpackName, item.getName(), etName.getText().toString());
                            updateListView();
                            dialog.cancel();
                        }else{
                            Snackbar snackbar = Snackbar
                                    .make(view, "Posiadasz w plecaku przedmiot o tej nazwie", Snackbar.LENGTH_SHORT);
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

        itemList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Item item = (Item)adapterView.getItemAtPosition(i);
                new AlertDialog.Builder(adapterView.getContext())
                        .setMessage("Czy na pewno chcesz usunąć przedmiot z plecaka?")
                        .setCancelable(false)
                        .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                service.deleteItemFromBackpack(backpackName, item.getName());
                                updateListView();
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("Nie", null)
                        .show();
                return true;
            }
        });

        Button btnAdd = (Button) findViewById(R.id.backpack_edit_activity_add_btn);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(BackpackEditActivity.this);
                final View mView = getLayoutInflater().inflate(R.layout.backpack_edit_activity_new_popup, null);

                mBuilder.setView(mView);
                final AlertDialog dialog  = mBuilder.create();

                Button buttonOK = (Button) mView.findViewById(R.id.backpack_edit_activity_popup_OK);
                Button buttonCancel = (Button) mView.findViewById(R.id.backpack_edit_activity_popup_Cancel);
                final EditText etName = (EditText) mView.findViewById(R.id.backpack_edit_activity_popup_textInputLayout_editText);
                final TextInputLayout wrapper = (TextInputLayout) findViewById(R.id.backpack_edit_activity_popup_textInputLayout);

                buttonOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!service.checkIfBackpackContainsItem(backpackName, etName.getText().toString())) {
                            service.addItemToBackpack(backpackName, etName.getText().toString());
                            updateListView();
                            dialog.cancel();
                        }else{
                            Snackbar snackbar = Snackbar
                                    .make(view, "Na liście jest już ten przedmiot", Snackbar.LENGTH_SHORT);
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

                dialog.show();
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.backpack_edit_activity_fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    private void updateListView() {
        listAdapter = new BackpackItemEditAdapter(this, service, backpackName);
        itemList.setAdapter(listAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean validateName() {
        if (etName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError("Nazwa plecaka nie może być pusta");
            requestFocus(etName);
            return false;
        } else if(!service.checkIfNameIsValid(etName.getText().toString()) && !etName.getText().toString().equals(getIntent().getStringExtra("backpack_name"))){
            inputLayoutName.setError("Posiadasz już plecak o tej nazwie");
            requestFocus(etName);
            return false;
        } else
            inputLayoutName.setErrorEnabled(false);
        service.setBackpackName(backpackName, etName.getText().toString());
        backpackName = etName.getText().toString();
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            if(view.getId() == R.id.backpack_edit_activity_backpack_name){
                validateName();
            }
        }
    }

}
