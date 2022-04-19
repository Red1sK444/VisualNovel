package com.r3d1r4ph.visualnovel.domain.usecases

interface UseCase<Input, Output> {
    operator fun invoke(input: Input): Output
}