package at.ac.tuwien.nsa.gr12.comparelocations.adapter.key.store

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import at.ac.tuwien.nsa.gr12.comparelocations.core.interfaces.KeyStoreInterface
import java.nio.charset.Charset
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.GCMParameterSpec

class AndroidKeyStoreAdapter : KeyStoreInterface {

    private val androidKeyStore = "AndroidKeyStore"
    private val aesMode = "AES/GCM/NoPadding"
    private val databaseKey = "database"
    private val ivBytes = "TestTestTest"
    private val seed = "input"

    private var keyStore: KeyStore

    init {
        keyStore = KeyStore.getInstance(androidKeyStore)
        keyStore.load(null)

        if (!keyStore.containsAlias(databaseKey)) {
            val keyGenerator =
                KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, androidKeyStore);
            keyGenerator.init(
                KeyGenParameterSpec.Builder(
                    databaseKey,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM).setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .setRandomizedEncryptionRequired(false)
                    .build()
            )
            keyGenerator.generateKey()
        }
    }


    override fun databaseKey(): String {
        val key = keyStore.getKey(databaseKey, null)
        val c: Cipher = Cipher.getInstance(aesMode)
        c.init(
            Cipher.ENCRYPT_MODE,
            key,
            GCMParameterSpec(128, ivBytes.toByteArray())
        )
        val byteArray = c.doFinal(seed.toByteArray(Charset.defaultCharset()))
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    override fun mlsKey(): String {
        val obfuscated = arrayOf(0x74, 0x65, 0x73, 0x74)
        return obfuscated
            .map { it.toChar() }
            .joinToString("")
    }
}