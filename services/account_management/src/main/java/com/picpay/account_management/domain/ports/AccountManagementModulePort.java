package com.picpay.account_management.domain.ports;

import com.picpay.shared.domain.ids.AccountId;
import com.picpay.shared.domain.enums.AccountType;

import java.util.Optional;

public interface AccountManagementModulePort {
    Optional<AccountType> type(AccountId id);
}
