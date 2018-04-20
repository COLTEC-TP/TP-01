package br.ufmg.coltec.trabalhotp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by a2016951782 on 19/04/18.
 */

public class BancoController {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context){
        banco = new CriaBanco(context);
    }

    public String insereDado(String nome, String diretor, String ano, String genero, String faixa){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.NOME_FILME, nome);
        valores.put(CriaBanco.DIRETOR, diretor);
        valores.put(CriaBanco.ANO, ano);
        valores.put(CriaBanco.GENERO, genero);
        valores.put(CriaBanco.FAIXA,faixa);

        resultado = db.insert(CriaBanco.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";

    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {banco.NOME_FILME,banco.DIRETOR,banco.ANO,banco.GENERO,banco.FAIXA};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

}
