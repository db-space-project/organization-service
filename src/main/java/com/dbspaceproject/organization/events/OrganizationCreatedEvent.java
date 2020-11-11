package com.dbspaceproject.organization.events;

import com.dbspaceproject.organization.models.OrganizationModel;
import org.springframework.context.ApplicationEvent;

public class OrganizationCreatedEvent extends ApplicationEvent {

    public OrganizationCreatedEvent(OrganizationModel organization) {
        super(organization);
    }
}
