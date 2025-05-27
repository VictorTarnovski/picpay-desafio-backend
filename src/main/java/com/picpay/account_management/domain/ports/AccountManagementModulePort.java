package com.picpay.account_management.domain.ports;

import com.picpay.shared.domain.entities.AccountId;
import com.picpay.shared.domain.enums.AccountType;

public interface AccountManagementModulePort {
    AccountType type(AccountId id);
}
