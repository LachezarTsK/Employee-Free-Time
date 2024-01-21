
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> intervalsSortedByStart = createListIntervalsSortedByStart(schedule);
        return findAllCommonFreeTimeIntervals(intervalsSortedByStart);
    }

    private List<Interval> createListIntervalsSortedByStart(List<List<Interval>> schedule) {
        List<Interval> intervals = new ArrayList<>();

        for (int i = 0; i < schedule.size(); ++i) {
            for (Interval interval : schedule.get(i)) {
                intervals.add(new Interval(interval.start, interval.end));
            }
        }

        Collections.sort(intervals, (x, y) -> x.start - y.start);
        return intervals;
    }

    private List<Interval> findAllCommonFreeTimeIntervals(List<Interval> intervals) {
        List<Interval> commonFreeTimeIntervals = new ArrayList<>();
        int previousEnd = intervals.get(0).end;

        for (int i = 1; i < intervals.size(); ++i) {
            if (previousEnd < intervals.get(i).start) {
                commonFreeTimeIntervals.add(new Interval(previousEnd, intervals.get(i).start));
            }
            previousEnd = Math.max(previousEnd, intervals.get(i).end);
        }

        return commonFreeTimeIntervals;
    }
}

// class Interval is defined by leetcode.com
// do not include the class when running the code on the website
class Interval {

    public int start;
    public int end;

    public Interval() {
    }

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
};
