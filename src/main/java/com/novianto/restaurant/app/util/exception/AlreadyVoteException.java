package com.novianto.restaurant.app.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Pengguna saat ini sudah vote. Voting hanya dapat diubah hingga pukul 11:00")
public class AlreadyVoteException extends RuntimeException {

    public AlreadyVoteException(String message) {
        super(message);
    }
}
