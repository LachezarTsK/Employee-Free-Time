
using System;
using System.Collections.Generic;

public class Solution
{
    public IList<Interval> EmployeeFreeTime(IList<IList<Interval>> schedule)
    {
        Interval[] intervalsSortedByStart = CreateArrayIntervalsSortedByStart(schedule);
        return FindAllCommonFreeTimeIntervals(intervalsSortedByStart);
    }

    private Interval[] CreateArrayIntervalsSortedByStart(IList<IList<Interval>> schedule)
    {
        Interval[] intervals = schedule.SelectMany(n => n).ToArray();
        Array.Sort(intervals, (x, y) => x.start - y.start);
        return intervals;
    }

    private IList<Interval> FindAllCommonFreeTimeIntervals(Interval[] intervals)
    {
        IList<Interval> commonFreeTimeIntervals = new List<Interval>();
        int previousEnd = intervals[0].end;

        for (int i = 1; i < intervals.Length; ++i)
        {
            if (previousEnd < intervals[i].start)
            {
                commonFreeTimeIntervals.Add(new Interval(previousEnd, intervals[i].start));
            }
            previousEnd = Math.Max(previousEnd, intervals[i].end);
        }

        return commonFreeTimeIntervals;
    }
}

// class Interval is defined by leetcode.com
// do not include the class when running the code on the website
public class Interval
{
    public int start;
    public int end;

    public Interval() { }
    public Interval(int _start, int _end)
    {
        start = _start;
        end = _end;
    }
}
