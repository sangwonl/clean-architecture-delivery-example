package com.delivery.core.usecases.store;

import com.delivery.core.domain.Store;
import com.delivery.core.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@RequiredArgsConstructor
public class SearchStoresByNameUseCase extends UseCase<SearchStoresByNameUseCase.InputValues, SearchStoresByNameUseCase.OutputValues> {
    private final StoreRepository repository;

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(repository.searchByName(input.getSearchText()));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final String searchText;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final List<Store> stores;
    }
}

