package com.delivery.core.usecases.order;

import com.delivery.core.domain.Identity;
import com.delivery.core.domain.NotFoundException;
import com.delivery.core.domain.Order;
import com.delivery.core.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class GetOrderUseCase extends UseCase<GetOrderUseCase.InputValues, GetOrderUseCase.OutputValues> {
    private final OrderRepository repository;

    @Override
    public OutputValues execute(InputValues input) {
        final Identity id = input.getId();

        return repository.getById(id)
                .map(OutputValues::new)
                .orElseThrow(() -> new NotFoundException("Order %s not found", id.getNumber()));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final Identity id;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final Order order;
    }
}
