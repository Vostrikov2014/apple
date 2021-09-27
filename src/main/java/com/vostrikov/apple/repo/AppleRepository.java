package com.vostrikov.apple.repo;

import com.vostrikov.apple.models.Apple;
import org.springframework.data.repository.CrudRepository;

public interface AppleRepository extends CrudRepository<Apple, Long> {
}
