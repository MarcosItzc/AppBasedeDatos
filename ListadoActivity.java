package net.n_21011018.basededatossqllite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ListadoActivity extends AppCompatActivity {

    private TextView textViewListado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        textViewListado = findViewById(R.id.textViewListado);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();

        Cursor fila = bd.rawQuery("SELECT codigo, descripcion, precio FROM articulos", null);

        StringBuilder builder = new StringBuilder();
        while (fila.moveToNext()) {
            builder.append("Código: ").append(fila.getInt(0)).append("\n")
                    .append("Descripción: ").append(fila.getString(1)).append("\n")
                    .append("Precio: ").append(fila.getDouble(2)).append("\n\n");
        }

        textViewListado.setText(builder.toString());
        fila.close();
        bd.close();
    }
}
