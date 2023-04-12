package com.example.pet.domain

import android.content.Context
import android.content.ContextWrapper
import java.io.File
import java.io.FileInputStream

class FileUtil(
    private val context: Context
) {

    private val sizeLimit = 1024 * 1024 * 10

    private val storageDir = File(ContextWrapper(context).applicationInfo.dataDir)

    private val storageName = "my_storage"

    private val storage =
        File("${ContextWrapper(context).applicationInfo.dataDir}${File.separator}$storageName")


    fun createStorage() {
//        File(/* parent = */ storageDir, /* child = */ storageName)
        if (!storage.isFile){
            storage.createNewFile()
        }
    }


    fun isStorageExist() = storage.isFile


    fun getSize() = storage.length()


    fun readStorage(): ByteArray = storage.readBytes()


    fun readBigStorage(): FileInputStream = FileInputStream(storage)

//        FileInputStream(storage).use { fis->
//            val buffer = ByteArray(sizeLimit)
//            while (fis.available() > 0) {
//                fis.read(buffer)
//                handleData(buffer)
//            }
//        }

    private fun handleData(buffer: ByteArray) {
        // do smth with data
    }


    fun writeData(data: ByteArray, append: Boolean = true){
        if(append){
            storage.appendBytes(data)
        } else storage.writeBytes(data)
    }



    fun showDirContent(): Array<File>? {
        return if (storageDir.isDirectory) {
            storageDir.listFiles()
        } else null
    }

}