package com.delivery.core.usecases.store;

import com.delivery.core.domain.Store;
import com.delivery.core.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@RequiredArgsConstructor
public class GetAllStoresUseCase extends UseCase<GetAllStoresUseCase.InputValues, GetAllStoresUseCase.OutputValues> {
    private final StoreRepository repository;

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(repository.getAll());
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final List<Store> stores;
    }
}
