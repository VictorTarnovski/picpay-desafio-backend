package com.picpay;

import com.picpay.shared.domain.exceptions.PicPayException;
import jakarta.persistence.OptimisticLockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({PicPayException.class})
    public ProblemDetail handlePicPayException(PicPayException exception) {
        return exception.toProblemDetail();
    }

    @ExceptionHandler({OptimisticLockException.class})
    public ProblemDetail handleOptmisticLockException(OptimisticLockException ignored) {
        var pb = ProblemDetail.forStatus(HttpStatus.CONFLICT);

        pb.setTitle("The resource has been modified by another process. Please try again.");

        return pb;
    }
}
