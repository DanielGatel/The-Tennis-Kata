package es.foo.shared.impl;

import es.foo.shared.model.PlayerRole;
import es.foo.shared.model.Score;
import lombok.Data;

@Data
public class State {

    private Score scoreOld;
    private Score scoreNew;
    private PlayerRole winner;

}
