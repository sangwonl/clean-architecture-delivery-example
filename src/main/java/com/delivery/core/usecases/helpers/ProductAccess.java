package com.delivery.core.usecases.helpers;

import com.delivery.core.domain.Identity;
import com.delivery.core.domain.NotFoundException;
import com.delivery.core.domain.Product;
import com.delivery.core.usecases.product.ProductRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ProductAccess {
    private final ProductRepository repository;

    public List<Product> getProducts(Identity storeId, List<Identity> productsId) {
        final List<Identity> distinctProductsId = distinctIds(productsId);

        List<Product> foundProducts = repository
                .searchProductsByStoreAndProductsId(storeId, distinctProductsId);

        throwIfAnyProductIsNotFound(distinctProductsId, foundProducts);

        return foundProducts;
    }

    private void throwIfAnyProductIsNotFound(List<Identity> distinctProductsId,
                                             List<Product> foundProducts) {
        if (distinctProductsId.size() != foundProducts.size()) {
            final String message = createErrorMessage(distinctProductsId, foundProducts);
            throw new NotFoundException(message);
        }
    }

    private String createErrorMessage(List<Identity> distinctProductsId, List<Product> foundProducts) {
        List<String> missingProductsId = getMissingProductsId(distinctProductsId, foundProducts);
        return String.format("Product(s) %s not found", String.join(", ", missingProductsId));
    }

    private List<String> getMissingProductsId(List<Identity> distinctProductsId, List<Product> foundProducts) {
        Set<Long> distinctProductsIdSet = createDistinctProductsIdSet(distinctProductsId);
        Set<Long> foundProductsId = createFoundProductsIdSet(foundProducts);
        distinctProductsIdSet.removeAll(foundProductsId);

        return distinctProductsIdSet
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    private Set<Long> createFoundProductsIdSet(List<Product> foundProducts) {
        return foundProducts
                .stream()
                .map(Product::getId)
                .map(Identity::getNumber)
                .collect(Collectors.toSet());
    }

    private Set<Long> createDistinctProductsIdSet(List<Identity> distinctProductsId) {
        return distinctProductsId
                .stream()
                .map(Identity::getNumber)
                .collect(Collectors.toSet());
    }

    private List<Identity> distinctIds(List<Identity> identities) {
        return identities
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
