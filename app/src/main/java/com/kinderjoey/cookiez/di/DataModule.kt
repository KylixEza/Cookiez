package com.kinderjoey.cookiez.di

import androidx.room.Room
import com.kinderjoey.cookiez.data.repository.CookiezRepository
import com.kinderjoey.cookiez.data.repository.ICookiezRepository
import com.kinderjoey.cookiez.data.sources.firestore.FirestoreDataSource
import com.kinderjoey.cookiez.data.sources.firestore.network.FirestoreClient
import com.kinderjoey.cookiez.data.sources.firestore.network.FirestoreClientImpl
import com.kinderjoey.cookiez.data.sources.local.room.CookiezDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory {
        get<CookiezDatabase>().cookiezDao()
    }

    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("Cookiez".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            CookiezDatabase::class.java, "Cookiez.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val firestoreModule = module {
    single<FirestoreClient> {
        FirestoreClientImpl()
    }

}

val repositoryModule = module {
    single { FirestoreDataSource(get()) }
    single<ICookiezRepository> {
        CookiezRepository(
            get()
        )
    }
}
