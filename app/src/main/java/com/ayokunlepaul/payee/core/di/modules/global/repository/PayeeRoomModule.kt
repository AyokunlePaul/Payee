package com.ayokunlepaul.payee.core.di.modules.global.repository

import android.content.Context
import androidx.room.Room
import com.ayokunlepaul.payee.PayeeConstants
import com.ayokunlepaul.payee.core.di.scopes.PayeeAppScope
import com.ayokunlepaul.payee.core.repository.PayeeRoomDatabase
import dagger.Module
import dagger.Provides

/**
 * Created by ayokunlepaul on 2019-05-27.
 */
@Module
class PayeeRoomModule {

    @Provides
    @PayeeAppScope
    fun providePayeeDatabase(context: Context) = Room.databaseBuilder(
        context,
        PayeeRoomDatabase::class.java,
        PayeeConstants.PAYEE_DATABASE_NAME
    ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

    @Provides
    @PayeeAppScope
    fun provideBankDAO(payeeRoomDatabase: PayeeRoomDatabase) = payeeRoomDatabase.getBankDao()

    @Provides
    @PayeeAppScope
    fun provideTransactionDAO(payeeRoomDatabase: PayeeRoomDatabase) = payeeRoomDatabase.getTransactionDao()
}