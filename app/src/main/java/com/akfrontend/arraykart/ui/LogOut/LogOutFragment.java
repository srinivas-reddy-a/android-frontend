package com.akfrontend.arraykart.ui.LogOut;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.akfrontend.arraykart.AllApiModels.LogOutRespones;
import com.akfrontend.arraykart.AllRetrofit.RetrofitClient;
import com.akfrontend.arraykart.AllRetrofit.SharedPrefManager;
import com.akfrontend.arraykart.databinding.FragmentLogOutBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogOutFragment extends Fragment {

    private FragmentLogOutBinding binding;

    SharedPrefManager sharedPrefManager;

    GoogleSignInClient mGoogleSignInClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =FragmentLogOutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferences user_token = getContext().getSharedPreferences("arraykartuser",MODE_PRIVATE);

        sharedPrefManager = new SharedPrefManager(getContext());
        String token = sharedPrefManager.getValue_string("token");

        if(user_token.contains("token")){

            AlertDialog alg = new AlertDialog.Builder(getContext())
                    .setTitle("Message")
                    .setMessage("Are You Sure You Want To LogOut ??")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            sharedPrefManager.clear();


                            Call<LogOutRespones> callOut = RetrofitClient.getInstance().getApi().logout(token);
                            callOut.enqueue(new Callback<LogOutRespones>() {
                                @Override
                                public void onResponse(Call<LogOutRespones> call, Response<LogOutRespones> response) {
                                    LogOutRespones logOutRespones = response.body();
                                    Toast.makeText(getContext(), logOutRespones.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<LogOutRespones> call, Throwable t) {
                                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                            //signOut();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .create();
            alg.show();
        }else {
            Toast.makeText(getContext(), "SignUp First", Toast.LENGTH_SHORT).show();
        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);

//        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getContext());
//        if (acct != null) {
//            String personName = acct.getDisplayName();
////            String personGivenName = acct.getGivenName();
////            String personFamilyName = acct.getFamilyName();
//            String personEmail = acct.getEmail();
////            String personId = acct.getId();
//            Uri personPhoto = acct.getPhotoUrl();
//
//            UserName.setText(personName);
//            UserEmail.setText(personEmail);
//            Glide.with(this).load(String.valueOf(personPhoto)).into(UserProfileImage);

        return  root;
    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }
}