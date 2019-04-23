package com.bzahov.musalasoftDatabase;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CommandStatePK implements Serializable {
    private int commandId;
    private int stateId;

    @Column(name = "CommandID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    @Column(name = "StateID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandStatePK that = (CommandStatePK) o;
        return commandId == that.commandId &&
                stateId == that.stateId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandId, stateId);
    }
}
