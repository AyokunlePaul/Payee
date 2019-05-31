package com.ayokunlepaul.payee.core.repository.daos

import androidx.room.*
import com.ayokunlepaul.payee.core.repository.entities.Bank
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by ayokunlepaul on 2019-05-27.
 */
@Dao
interface BankDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun saveBank(banks: List<Bank>): Completable
    @Delete fun deleteBank(bank: Bank): Completable
    @Query("SELECT CoUNT(*) FROM Bank") fun getBanksCount(): Single<Int>
    @Query("SELECT * FROM BANK") fun getAllBanks(): Single<List<Bank>>
}