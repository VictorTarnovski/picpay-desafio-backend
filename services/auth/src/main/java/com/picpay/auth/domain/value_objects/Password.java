package com.picpay.auth.domain.value_objects;

import java.util.Objects;

public class Password {
    private final String hash;
    private final String salt;

    private Password(String input, String salt) {
        Objects.requireNonNull(input, "input must not be null");
        Objects.requireNonNull(salt, "salt mut not be null");
//        if (salt.isBlank())
//            throw new BlankPasswordSaltException();
        this.salt = salt;

        // TODO: Implement hashing
        this.hash = input;
    }

    public Password(String input) {
        this(input, "");
    }

    public Password from(String input) {
        return new Password(input, salt);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Password password = (Password) other;
        return Objects.equals(salt, password.salt) && Objects.equals(hash, password.hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salt, hash);
    }
}
