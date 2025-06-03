package net.n_21011018.basededatossqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    private EditText editText1, editText2, editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1=findViewById(R.id.editText1);
        editText2=findViewById(R.id.editText2);
        editText3=findViewById(R.id.editText3);
    }

    public void consultaporcodigo(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = editText1.getText().toString();
        Cursor fila = bd.rawQuery(
                "select descripcion, precio from articulos where codigo="
                + cod, null);
        if (fila.moveToFirst()){
            editText2.setText(fila.getString(0));
            editText3.setText(fila.getString(1));
        } else
            Toast.makeText(this,
                    "No existe un articulo con dicho codigo",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void alta(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(
                this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = editText1.getText().toString();
        String descri = editText2.getText().toString();
        String pre = editText3.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigo", cod);
        registro.put("descripcion", descri);
        registro.put("precio", pre);
        bd.insert("articulos", null, registro);
        bd.close();
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        Toast.makeText(this, "Se cargaron los datos del articulo",
                Toast.LENGTH_SHORT).show();
    }

    public void consultapordescripcion(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper( this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String descri = editText2.getText().toString();
        Cursor fila = bd.rawQuery(
                "select codigo,precio from articulos where descripcion='"
                + descri + "'", null);
        if (fila.moveToFirst()){
            editText1.setText(fila.getString(0));
            editText3.setText(fila.getString(1));
        }else
            Toast.makeText(this,
                    "No existe un articulo con dicha descripcion",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void bajaporcodigo(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(
                this, "administracion", null,
                1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = editText1.getText().toString();
        int cant = bd.delete("articulos",
                "codigo=" + cod, null);
        bd.close();
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        if (cant == 1)
            Toast.makeText(this,
                    "Se borró el articulo con dicho codigo",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,
                    "No existe un articulo con dicho codigo",
                    Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(
                this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = editText1.getText().toString();
        String descri = editText2.getText().toString();
        String pre = editText3.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("codigo", cod);
        registro.put("descripcion", descri);
        registro.put("precio", pre);
        int cant = bd.update("articulos", registro,
                "codigo=" + cod, null);
        bd.close();

        if(cant == 1)
            Toast.makeText(this, "Se modificaron los datos",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,
                    "No existe un articulo con el codigo ingresado",
                    Toast.LENGTH_SHORT).show();
    }

    public void salir(View view){ System.exit(0);}

    public void limpiar(View view){
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
    }

    public void listar(View view){
        Intent intent = new Intent(this, ListadoActivity.class);
        startActivity(intent);
    }

}