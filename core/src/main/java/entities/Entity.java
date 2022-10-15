package entities;

import java.util.UUID;

public abstract class Entity {

    protected UUID id = UUID.randomUUID();

    public UUID getId(){
        return this.id;
    }
}
