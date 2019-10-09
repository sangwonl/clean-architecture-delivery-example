package com.delivery.core.usecases.product;

import com.delivery.core.domain.Product;
import com.delivery.core.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@RequiredArgsConstructor
public class GetAllProductsUseCase extends UseCase<GetAllProductsUseCase.InputValues, GetAllProductsUseCase.OutputValues> {
    private final ProductRepository repository;

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(repository.getAll());
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final List<Product> products;
    }
}
