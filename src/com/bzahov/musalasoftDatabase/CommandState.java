package com.bzahov.musalasoftDatabase;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Command_State", schema = "dbo", catalog = "MusalaSoft")
@IdClass(CommandStatePK.class)
public class CommandState {
    private int commandId;
    private int stateId;

    @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)    @Column(name = "CommandID")
    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)    @Column(name = "StateID")
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
        CommandState that = (CommandState) o;
        return commandId == that.commandId &&
                stateId == that.stateId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandId, stateId);
    }
}
