package com.ayokunlepaul.payee.core.repository.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ayokunlepaul.payee.core.repository.entities.TransactionEntity
import io.reactivex.Observable

/**
 * Created by ayokunlepaul on 2019-05-28.
 */
@Dao
interface TransactionDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE) fun saveTransaction(transactionEntity: TransactionEntity)
    @Query("SELECT * FROM TransactionEntity") fun getAllTransaction(): Observable<List<TransactionEntity>>
}