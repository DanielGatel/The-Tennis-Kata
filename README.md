# The-Tennis-Kata

### External rule loading
The rules consist of a set of methods (Consumers) that allow to vary the state of the score, taking into account:
 * The previous score
 * The winning player of the point
 * The resulting score
 
```java
scoring.setRules(Set.of(Rules.DEUCE));
```