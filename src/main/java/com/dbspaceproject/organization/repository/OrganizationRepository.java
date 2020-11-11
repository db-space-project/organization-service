package com.dbspaceproject.organization.repository;

import com.dbspaceproject.organization.models.OrganizationModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OrganizationRepository extends ReactiveMongoRepository<OrganizationModel, String> {
}
