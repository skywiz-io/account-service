package com.poalim.openshift.account;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by osher on 19/7/17.
 */

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findById(Integer id);
    Optional<List<Account>> findByFullNameIgnoreCaseContaining(String name);
}

