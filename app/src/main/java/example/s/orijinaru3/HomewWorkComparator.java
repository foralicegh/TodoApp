package example.s.orijinaru3;

/**
 * Created by s on 2017/02/05.
 */

import java.util.Comparator;


public class HomewWorkComparator implements Comparator<Homework> {

    @Override
    public int compare(Homework p1, Homework p2) {
        return p1.getDiffday() < p2.getDiffday() ? -1 : 1;
    }

}
