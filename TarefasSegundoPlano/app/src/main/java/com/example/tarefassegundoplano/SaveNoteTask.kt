package com.example.tarefassegundoplano

import android.os.AsyncTask

class SaveNoteTask(
    private val activity: MainActivity,
    private val note: String
): AsyncTask<Void, Void, Boolean>() {

    override fun onPreExecute() {
        super.onPreExecute()
        activity.txtStatus.text = "Salvando nota..."
    }

    override fun doInBackground(vararg params: Void?): Boolean? {
        return try{
            val note = Note(text = note)
            Thread.sleep(1000)
            true
        }catch (e: Exception){
            return false
        }
    }

    override fun onPostExecute(result: Boolean) {
        super.onPostExecute(result)

        if(result){
            activity.txtStatus.text = "Nota Salva com sucesso!"
        } else{
            activity.txtStatus.text = "Erro ao salvar"
        }
    }


}