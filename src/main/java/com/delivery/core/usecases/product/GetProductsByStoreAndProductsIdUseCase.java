package com.delivery.core.usecases.product;

import com.delivery.core.domain.Identity;
import com.delivery.core.domain.Product;
import com.delivery.core.usecases.UseCase;
import com.delivery.core.usecases.helpers.ProductAccess;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@RequiredArgsConstructor
public class GetProductsByStoreAndProductsIdUseCase extends UseCase<GetProductsByStoreAndProductsIdUseCase.InputValues, GetProductsByStoreAndProductsIdUseCase.OutputValues> {
    private final ProductAccess productAccess;

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(productAccess.getProducts(input.getStoreId(), input.getProductsId()));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final Identity storeId;
        private final List<Identity> productsId;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final List<Product> products;
    }
}
