package entities;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@AllArgsConstructor
public class Cell {

    @Getter @Setter private int x;
    @Getter @Setter private int y;


}
