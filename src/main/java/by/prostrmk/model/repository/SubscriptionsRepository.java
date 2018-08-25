package by.prostrmk.model.repository;

import by.prostrmk.model.entity.Subscription;
import by.prostrmk.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriptionsRepository extends CrudRepository<Subscription, Long> {

    List<Subscription> findByUserWhoSubscribed(User user);

}
