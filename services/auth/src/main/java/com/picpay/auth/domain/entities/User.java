package com.picpay.auth.domain.entities;

import com.picpay.auth.domain.events.UserCreated;
import com.picpay.auth.domain.ids.UserId;
import com.picpay.auth.domain.value_objects.Email;
import com.picpay.auth.domain.value_objects.Password;
import com.picpay.auth.domain.repositories.UserRepository;
import com.picpay.shared.domain.enums.AccountType;
import jakarta.persistence.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Objects;

@Entity
@Table(name = "users")
public class User extends AbstractAggregateRoot<User> {
    @EmbeddedId
    protected UserId id;
    protected String fullName;
    protected String document;
    @Embedded
    protected Email email;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "hash", column = @Column(name = "password_hash")),
        @AttributeOverride(name = "salt", column = @Column(name = "password_salt"))
    })
    protected Password password;

    User() {}

    public User(
        UserRepository repository,
        String fullName,
        String document,
        String email,
        String password,
        AccountType accountType
    ) {
        Objects.requireNonNull(repository, "repository must not be null");
        this.id = repository.nextId();

        Objects.requireNonNull(fullName, "fullName must not be null");
        this.fullName = fullName;

        Objects.requireNonNull(email, "email must not be null");
        this.email = new Email(email);

        Objects.requireNonNull(password, "password must not be null");
        this.password = new Password(password);

        Objects.requireNonNull(document, "document must not be null");
        this.document = document;

        Objects.requireNonNull(accountType, "accountType must not be null");
        this.registerEvent(new UserCreated(this.id, accountType));
    }

    public boolean hasPassword(String input) {
        var password = this.password.from(input);
        return this.password.equals(password);
    }
}
