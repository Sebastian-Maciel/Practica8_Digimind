package garciamaciel.sebastian.mydigimind

import java.io.Serializable
import java.util.concurrent.locks.ReentrantLock

class Carrito: Serializable {
    var recordatorios = ArrayList<Recordatorio>()

    fun agregar(p: Recordatorio): Boolean{
        return recordatorios.add(p)
    }
}