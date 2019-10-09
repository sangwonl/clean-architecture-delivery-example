package com.delivery.presenter.rest.api.cousine;

import com.delivery.core.domain.Identity;
import com.delivery.core.usecases.UseCaseExecutor;
import com.delivery.core.usecases.cousine.GetAllCousinesUseCase;
import com.delivery.core.usecases.cousine.GetStoresByCousineUseCase;
import com.delivery.core.usecases.cousine.SearchCousineByNameUseCase;
import com.delivery.presenter.rest.api.entities.CousineResponse;
import com.delivery.presenter.rest.api.entities.StoreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class CousineController implements CousineResource {
    private final UseCaseExecutor useCaseExecutor;
    private final GetAllCousinesUseCase getAllCousinesUseCase;
    private final GetStoresByCousineUseCase getStoresByCousineUseCase;
    private final SearchCousineByNameUseCase searchCousineByNameUseCase;

    @Override
    public CompletableFuture<List<StoreResponse>> getStoresByCousineId(@PathVariable Long id) {
        return useCaseExecutor.execute(
                getStoresByCousineUseCase,
                new GetStoresByCousineUseCase.InputValues(new Identity(id)),
                (outputValues) -> StoreResponse.from(outputValues.getStores()));
    }

    @Override
    public CompletableFuture<List<CousineResponse>> getAllCousines() {
        return useCaseExecutor.execute(
                getAllCousinesUseCase,
                new GetAllCousinesUseCase.InputValues(),
                (outputValues) -> CousineResponse.from(outputValues.getCousines()));
    }

    @Override
    public CompletableFuture<List<CousineResponse>> getAllCousinesByNameMatching(@PathVariable String text) {
        return useCaseExecutor.execute(
                searchCousineByNameUseCase,
                new SearchCousineByNameUseCase.InputValues(text),
                (outputValues) -> CousineResponse.from(outputValues.getCousines()));
    }
}
