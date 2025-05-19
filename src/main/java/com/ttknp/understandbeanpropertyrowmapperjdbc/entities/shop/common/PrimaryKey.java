package com.ttknp.understandbeanpropertyrowmapperjdbc.entities.shop.common;

import java.util.UUID;

public class PrimaryKey {

    private UUID uuid;

    public PrimaryKey(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
