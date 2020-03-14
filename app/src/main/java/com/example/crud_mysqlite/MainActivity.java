 package com.example.crud_mysqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;


 public class MainActivity extends AppCompatActivity {
    EditText etID, etNombre,etTelefono;
    Button btnConsultar,btnAgregar,btnEditar,btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //refencias con el layout xml

        etID = findViewById(R.id.idUsuario);
        etNombre = findViewById(R.id.etNombre);
        etTelefono = findViewById(R.id.etTelefono);

        btnConsultar = findViewById(R.id.btnConsultar);
        btnAgregar =findViewById(R.id.btnAgregar);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);

        btnConsultar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                consulta();
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Agregar();
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                E ditar();
            }


        });

        btnEliminar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Borrar();
            }

        });

    }
    //metodos para consultar usando el id del  formulario
    public void consulta(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this ,"administracion",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        //SE LEE EL CAMPO ID
        String id = etID.getText().toString();
        //se realiza la consulta
        Cursor fila = db.rawQuery("SELECT nombre,telefono FROM usuarios where idUsuario ="+id,null);
        //SI SE ENCONTRO UN REGISTRO MUESTRA EN LOS CAMPOS LA INFORMACION OBTENIDAEN CASO CONTRARIO MANDA UN MENSAJE
        if(fila.moveToFirst()){
            etNombre.setText(fila.getString(0));
            etTelefono.setText(fila.getString(1));

        }else{
            Toast.makeText(this,"No existe ningun usuario con esa cedula",Toast.LENGTH_LONG).show();
        }
    }

    public void Agregar(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this ,"administracion",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        //SE LEEN LOS CAMPOS
        String cedula,nombre,telefono;
        cedula = etID.getText().toString();
        nombre  = etNombre.getText().toString();
        telefono = etTelefono.getText().toString();
        // SE CREAN VARIABLES PARA EL REGISTRO
        ContentValues registro= new ContentValues();
        registro.put("idUsuario",cedula);
        registro.put("nombre",nombre);
        registro.put("telefono",telefono);
        db.insert("usuarios",null,registro);
        //SE CIERRA LA CONEXION
        db.close();
        //SE LIMPIAN LOS CAMPOS
        etID.setText("");
        etNombre.setText("");
        etTelefono.setText("");
    }

    public void Borrar(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this ,"administracion",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        //Se lee El id para Borrar
        String idUsuario = etID.getText().toString();
        //Se Borra el registro
        int cant= db.delete("usuarios","idUsuario="+idUsuario,null);
        //Se cierra la conexion
        db.close();

        //SE LIMPIAN LOS CAMPOS
        etID.setText("");
        etNombre.setText("");
        etTelefono.setText("");
        //si existe usuario o no muestra mensaje
        if (cant ==1){
            Toast.makeText(this,"usuario eliminado",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"No existe Usuario",Toast.LENGTH_LONG).show();
        }
    }

    public void Editar(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this ,"administracion",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        //SE LEEN LOS CAMPOS
        int cant;
        String cedula,nombre,telefono;
        cedula = etID.getText().toString();
        nombre  = etNombre.getText().toString();
        telefono = etTelefono.getText().toString();
        ContentValues registro= new ContentValues();

        registro.put("nombre",nombre);
        registro.put("telefono",telefono);
        cant = db.update("usuarios",registro,"idUsuario="+cedula,null);
        db.close();

        //SE LIMPIAN LOS CAMPOS
        etID.setText("");
        etNombre.setText("");
        etTelefono.setText("");

        //si existe usuario o no muestra mensaje
        if (cant ==1){
            Toast.makeText(this,"usuario editado exitosa mente",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"No existe Usuario",Toast.LENGTH_LONG).show();
        }
    }
}
