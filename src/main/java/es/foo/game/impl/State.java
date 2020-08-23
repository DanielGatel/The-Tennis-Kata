package es.foo.game.impl;

import es.foo.game.PlayerRole;
import es.foo.game.Score;
import lombok.Data;

@Data
public class State {

    private Score scoreOld;
    private Score scoreNew;
    private PlayerRole winner;

}
