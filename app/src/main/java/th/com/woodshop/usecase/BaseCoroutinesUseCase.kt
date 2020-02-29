package th.com.woodshop.usecase

import th.com.woodshop.model.Result

abstract class BaseCoroutinesUseCase<in P, R> {
    abstract suspend fun execute(parameter: P): Result<R>
}