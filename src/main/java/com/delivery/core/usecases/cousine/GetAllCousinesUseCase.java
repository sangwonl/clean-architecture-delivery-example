package com.delivery.core.usecases.cousine;

import com.delivery.core.domain.Cousine;
import com.delivery.core.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@RequiredArgsConstructor
public class GetAllCousinesUseCase extends UseCase<GetAllCousinesUseCase.InputValues, GetAllCousinesUseCase.OutputValues> {
    private final CousineRepository repository;

    @Override
    public OutputValues execute(InputValues input) {
        return new OutputValues(repository.getAll());
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final List<Cousine> cousines;
    }
}
