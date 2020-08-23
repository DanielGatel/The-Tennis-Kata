package es.foo.shared;

import es.foo.shared.model.PlayerRole;
import es.foo.shared.model.Score;

import java.util.Optional;

/**
 * Score a game in real time.
 */
public interface Scoring {

    /**
     * Update the score when a player wins a point.
     *
     * @param pointWinner
     *         the point winner
     */
    void update(PlayerRole pointWinner);

    /**
     * See what the current score is after each service.
     *
     * @return the score
     */
    Score getScore();

    /**
     * See if there is a winner based on the current score and the rules
     *
     * @return the game winner
     */
    Optional<PlayerRole> getGameWinner();

}
