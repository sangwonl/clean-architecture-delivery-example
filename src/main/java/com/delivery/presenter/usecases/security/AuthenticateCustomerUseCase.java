package com.delivery.presenter.usecases.security;

import com.delivery.core.usecases.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateCustomerUseCase extends UseCase<AuthenticateCustomerUseCase.InputValues, AuthenticateCustomerUseCase.OutputValues> {
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Override
    public OutputValues execute(InputValues input) {
        Authentication authentication = authenticationManager.authenticate(input.getAuthenticationToken());

        return new OutputValues(jwtProvider.generateToken(authentication));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final UsernamePasswordAuthenticationToken authenticationToken;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final String jwtToken;
    }
}
