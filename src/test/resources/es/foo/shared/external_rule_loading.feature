Feature: External rule loading

  Scenario Outline: Winning Points are Scored Correctly
    Given the score is "<score>"
    But remove the rule of winning by two
    When the "<player>" wins a point
    Then the "<winner>" should win
    Examples:
      | score | player   | winner   |
      | 40:40 | server   | server   |
      | 40:40  | receiver | receiver |

