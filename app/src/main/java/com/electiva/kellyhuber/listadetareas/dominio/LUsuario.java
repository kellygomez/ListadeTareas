package com.electiva.kellyhuber.listadetareas.dominio;

import android.support.annotation.NonNull;

import com.electiva.kellyhuber.listadetareas.Modelos.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by kelly on 30/10/2017.
 */

public class LUsuario implements ILUsuario {

    private FirebaseAuth firebaseAutenticar;
    private DatabaseReference referenciaBD;

    public LUsuario() {
        firebaseAutenticar = FirebaseAuth.getInstance();
        referenciaBD = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void crearUsuario(String password, final Usuario usuario, final CallbackInteractor<String> interactor) {
        firebaseAutenticar.createUserWithEmailAndPassword(usuario.getEmail(),password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //se crea el usuario
                            usuario.setUid(firebaseAutenticar.getCurrentUser().getUid());
                            referenciaBD.child(usuario.getUid()).setValue(usuario);
                            interactor.success(usuario.getNombres());
                        }else{
                            interactor.error(task.getException().getMessage());
                        }
                    }
                });
    }

    @Override
    public void autenticaUsuario(String password, String email, final CallbackInteractor<String> interactor) {

        firebaseAutenticar.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    referenciaBD.child(task.getResult().getUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Usuario usuario = dataSnapshot.getValue(Usuario.class);
                            interactor.success(usuario.getNombres());
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            interactor.error(databaseError.getMessage());
                        }
                    });
                }else{
                    interactor.error(task.getException().getMessage());
                }
            }
        });

    }

    @Override
    public void recuperarPassword(String email, final CallbackInteractor<String> interactor) {
        firebaseAutenticar.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    interactor.success("correo enviado");
                }else{
                    interactor.error(task.getException().getMessage());
                }
            }
        });
    }
}
