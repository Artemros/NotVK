package com.notvk.NotVK.homepage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WallTextRepository extends CrudRepository<WallText, String> {
}
