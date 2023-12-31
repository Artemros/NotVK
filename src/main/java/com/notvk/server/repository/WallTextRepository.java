package com.notvk.server.repository;

import com.notvk.server.model.WallText;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WallTextRepository extends CrudRepository<WallText, String> {
}
