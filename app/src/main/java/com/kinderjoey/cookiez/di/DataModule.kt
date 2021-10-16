package com.kinderjoey.cookiez.di

import androidx.room.Room
import com.kinderjoey.cookiez.data.repository.*
import com.kinderjoey.cookiez.data.sources.firestore.FirestoreDataSource
import com.kinderjoey.cookiez.data.sources.firestore.RemoteDataSource
import com.kinderjoey.cookiez.data.sources.firestore.network.FirestoreClient
import com.kinderjoey.cookiez.data.sources.firestore.network.FirestoreClientImpl
import com.kinderjoey.cookiez.data.sources.firestore.network.service.AuthService
import com.kinderjoey.cookiez.data.sources.firestore.network.service.UserService
import com.kinderjoey.cookiez.data.sources.local.LocalDataSource
import com.kinderjoey.cookiez.data.sources.local.room.CookiezDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory {
        get<CookiezDatabase>().cookiezDao()
    }
    factory {
        get<CookiezDatabase>().userDao()
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
    single<IAuthRepository> {
        AuthRepository(
            get(), get()
        )
    }
    single<IProfileRepository> {
        ProfileRepository(
            get(), get()
        )
    }
}

val dataSourceModule = module{
    single {
        LocalDataSource(get())
    }
    single {
        RemoteDataSource(get(),get())
    }
}
val serviceModule = module {
    factory {
        AuthService()
    }
    factory {
        UserService()
    }
}
