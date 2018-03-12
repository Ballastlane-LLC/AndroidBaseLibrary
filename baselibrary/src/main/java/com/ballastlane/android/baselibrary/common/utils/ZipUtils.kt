package com.ballastlane.android.baselibrary.common.utils

import android.util.Log
import com.ballastlane.android.baselibrary.common.ext.createDirAsFile
import java.io.*
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

/**
 * Created by Mariangela Salcedo (mariangelasalcedo@ballastlane.com) on 3/8/18.
 * Copyright (c) 2018 Ballast Lane Applications LLC. All rights reserved.
 */


class ZipUtils {

    /**
     *
     * This method receive a list of paths for compress in a simple .zip file
     * ths process can take time please try to use in a background threat.
     *
     * @param _files List of path for the file that you want to compress.
     * @param _zipFile path of the .zip file
     * @return String with the path of the final .zio file
     */
    fun zipFiles(_files: Array<String>, _zipFile: String): String? {
        try {
            val dest = FileOutputStream(_zipFile)
            val out = ZipOutputStream(BufferedOutputStream(dest))
            val data = ByteArray(BUFFER)
            for (i in _files.indices) {
                val fi = FileInputStream(_files[i])
                var origin = BufferedInputStream(fi, BUFFER)
                val entry = ZipEntry(_files[i].substring(_files[i].lastIndexOf("/") + 1))
                out.putNextEntry(entry)
                var count: Int
                do {
                    count = origin.read(data, 0, BUFFER)
                    out.write(data, 0, count)
                } while ((count) != -1)
                origin.close()
            }
            out.close()
            return _zipFile
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    companion object {

        private val MAC_OSX_FOLDER_NAME = "MACOSX"
        val TAG = ZipUtils::class.java.simpleName
        private val BUFFER = 2048

        @Throws(FileNotFoundException::class)
        fun unzipFromPath(targetLocation: String, sourceLocation: String) {
            try {
                Log.i(ZipUtils::class.java.simpleName, String.format("Unzipping file \"%s\"", sourceLocation))
                unzip(targetLocation, ZipInputStream(BufferedInputStream(FileInputStream(sourceLocation))))
                val file = File(sourceLocation)
                file.delete()
            } catch (e: Exception) {
                e.message
                e.printStackTrace()
            }

        }

        @Throws(IOException::class)
        fun unzip(targetLocation: String, zipInputStream: ZipInputStream) {
            var targetLocation = if (targetLocation.endsWith(File.separator)) targetLocation else targetLocation + File.separator
            targetLocation.createDirAsFile()
            do {
                var zipEntry = zipInputStream.nextEntry
                if (!zipEntry!!.name.contains(MAC_OSX_FOLDER_NAME)) {
                    if (zipEntry.isDirectory) {
                        (targetLocation + zipEntry.name).createDirAsFile()
                    } else {
                        val byteArrayOutputStream = ByteArrayOutputStream()
                        val buffer = ByteArray(1024)
                        var count: Int
                        do {
                            count = zipInputStream.read(buffer)
                            byteArrayOutputStream.write(buffer, 0, count)
                        } while ((count) != -1)
                        val bytes = byteArrayOutputStream.toByteArray()
                        val fileOutputStream = FileOutputStream(targetLocation + zipEntry.name)
                        val bufferedOutputStream = BufferedOutputStream(fileOutputStream)
                        bufferedOutputStream.write(bytes)
                        bufferedOutputStream.flush()
                        bufferedOutputStream.close()
                        fileOutputStream.close()
                        zipInputStream.closeEntry()
                        byteArrayOutputStream.close()
                    }
                }
            } while ((zipEntry) != null)
            zipInputStream.close()
        }
    }

}