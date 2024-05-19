package br.net.alexdev.dashboard.controllers;

import br.net.alexdev.dashboard.dtos.responses.ErrorMessageResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorMessageResponse handleBadCredentialsException(BadCredentialsException e, WebRequest req) {
        return new ErrorMessageResponse("Não Autorizado", "Username e/ou senha inválidos", "BadCredentialsException", HttpStatus.UNAUTHORIZED.value());
    }
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorMessageResponse handleExpiredJwtException(ExpiredJwtException e, WebRequest req) {
        return new ErrorMessageResponse("Não Autorizado", "Username e/ou senha inválidos", "BadCredentialsException", HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(DisabledException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessageResponse handleDisabledException(DisabledException e, WebRequest req) {
        return new ErrorMessageResponse("Conta desativada", "A conta está desativada. Por favor, entre em contato com o suporte.", "DisabledException", HttpStatus.FORBIDDEN.value());
    }

    @ExceptionHandler(LockedException.class)
    @ResponseStatus(HttpStatus.LOCKED)
    public ErrorMessageResponse handleLockedException(LockedException e, WebRequest req) {
        return new ErrorMessageResponse("Conta bloqueada", "A conta está bloqueada. Por favor, entre em contato com o suporte.", "LockedException", HttpStatus.LOCKED.value());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorMessageResponse handleRuntimeException(RuntimeException e, WebRequest req) {
        return new ErrorMessageResponse("Erro de autenticação", "Ocorreu um erro ao tentar autenticar. Por favor, tente novamente mais tarde.", "RuntimeException", HttpStatus.UNAUTHORIZED.value());
    }
}
