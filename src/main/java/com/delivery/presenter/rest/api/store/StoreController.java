package com.delivery.presenter.rest.api.store;

import com.delivery.core.domain.Identity;
import com.delivery.core.usecases.UseCaseExecutor;
import com.delivery.core.usecases.store.GetAllStoresUseCase;
import com.delivery.core.usecases.store.GetProductsByStoreUseCase;
import com.delivery.core.usecases.store.GetStoreUseCase;
import com.delivery.core.usecases.store.SearchStoresByNameUseCase;
import com.delivery.presenter.rest.api.entities.ProductResponse;
import com.delivery.presenter.rest.api.entities.StoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class StoreController implements StoreResource {

    private final UseCaseExecutor useCaseExecutor;
    private final GetAllStoresUseCase getAllStoresUseCase;
    private final SearchStoresByNameUseCase searchStoresByNameUseCase;
    private final GetStoreUseCase getStoreUseCase;
    private final GetProductsByStoreUseCase getProductsByStoreUseCase;

    @Override
    public CompletableFuture<List<StoreResponse>> getAll() {
        return useCaseExecutor.execute(
                getAllStoresUseCase,
                new GetAllStoresUseCase.InputValues(),
                (outputValues) -> StoreResponse.from(outputValues.getStores()));
    }

    @Override
    public CompletableFuture<List<StoreResponse>> getAllStoresByNameMatching(@PathVariable String text) {
        return useCaseExecutor.execute(
                searchStoresByNameUseCase,
                new SearchStoresByNameUseCase.InputValues(text),
                (outputValues) -> StoreResponse.from(outputValues.getStores()));
    }

    @Override
    public CompletableFuture<StoreResponse> getStoreByIdentity(@PathVariable Long id) {
        return useCaseExecutor.execute(
                getStoreUseCase,
                new GetStoreUseCase.InputValues(new Identity(id)),
                (outputValues) -> StoreResponse.from(outputValues.getStore()));
    }

    @Override
    public CompletableFuture<List<ProductResponse>> getProductsBy(@PathVariable Long id) {
        return useCaseExecutor.execute(
                getProductsByStoreUseCase,
                new GetProductsByStoreUseCase.InputValues(new Identity(id)),
                (outputValues) -> ProductResponse.from(outputValues.getProducts()));
    }
}
