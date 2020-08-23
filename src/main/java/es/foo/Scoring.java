package es.foo;

import java.util.Optional;

public interface Scoring {

    void update(PlayerRole pointWinner);

    Score getScore();

    Optional<PlayerRole> getGameWinner();

}
