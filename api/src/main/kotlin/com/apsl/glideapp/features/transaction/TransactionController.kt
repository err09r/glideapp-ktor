package com.apsl.glideapp.features.transaction

import com.apsl.glideapp.common.dto.TransactionDto
import com.apsl.glideapp.common.dto.TransactionRequest
import com.apsl.glideapp.common.models.TransactionType
import com.apsl.glideapp.common.util.UUID
import com.apsl.glideapp.features.config.GlideConfig
import com.apsl.glideapp.utils.InvalidVoucherCodeException
import com.apsl.glideapp.utils.PaginationData
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay

class TransactionController(
    private val transactionDao: TransactionDao,
    private val glideConfig: GlideConfig
) {

    suspend fun getTransactionsByUserId(userId: String?, page: String?, limit: String?) = runCatching {
        requireNotNull(userId)
        val userUuid = UUID.fromString(userId)

        val (transactionLimit, offset) = PaginationData(page = page, limit = limit)

        transactionDao.getTransactionsByUserId(userId = userUuid, limit = transactionLimit, offset = offset)
            .mapIndexed { index, entity ->
                TransactionDto(
                    id = entity.id.toString(),
                    key = index + offset.toInt() + 1,
                    amount = entity.amount,
                    type = entity.type,
                    dateTime = entity.createdAt
                )
            }
    }

    suspend fun createTransaction(userId: String?, request: TransactionRequest?) = runCatching {
        requireNotNull(userId)
        requireNotNull(request)

        val userUuid = UUID.fromString(userId)

        if (request.amount == null && request.voucherCode != null && request.type == TransactionType.Voucher) {
            // Simulate delay
            delay(2.seconds)
            request.voucherCode?.let { voucherCode ->
                createVoucherTransaction(userId = userUuid, voucherCode = voucherCode)
            }
        } else {
            // Simulate delay of Payment System
            delay(4.seconds)
            request.amount?.let { amount ->
                transactionDao.insertTransaction(userId = userUuid, amount = amount, type = request.type)
            }
        }
    }

    private suspend fun createVoucherTransaction(userId: UUID, voucherCode: String) {
        val amount = glideConfig.voucherCodes[voucherCode.trim()] ?: throw InvalidVoucherCodeException()
        transactionDao.insertTransaction(userId = userId, type = TransactionType.Voucher, amount = amount)
    }
}
