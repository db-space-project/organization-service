package com.dbspaceproject.organization.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "organizations")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrganizationModel {
    @Id
    private String id;

    @NonNull
    private String name;
}
